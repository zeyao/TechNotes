
# Load Balancer


### 如何控制分布式的一致性：
- load balance： 一个访问请求被一个node拿到；

> 问题（如果一个节点拿到了data但是却坏了，这个时候已经把message consume了 怎么办）？

- DB加锁。（秒杀一类的）；
- 乐观锁，用version number来保证；  


### 如果在不同的DB，我们有需要保持事务的一致性，我们怎么办呢？ 
- 两阶段提交算法 :

>  比如我发请求给ABCD四个NODE。AB成功，CD挂掉了。 我需要AB也roll back。
	这个情况我需要两步，第一步发给ABCD，ABCD存储上一次的结果。ABCD return给发送者回应是否成功。
	只有当发送者接到成功的消息，发送者发第二次请求进行commit。 
	任何收不到或者受到错误，滚回。


```		
	阶段1：请求阶段（commit-request phase，或称表决阶段，voting phase）。
	在请求阶段，协调者将通知事务参与者准备提交或取消事务，
	然后进入表决过程。在表决过程中，参与者将告知协调者自己的决策：同意（事务参与者本地作业执行成功）或取消（本地作业执行故障）。
	
```	

<img src="https://raw.githubusercontent.com/zeyao/TechNotes/master/Document/%20%E4%B8%A4%E6%AE%B5%E6%8F%90%E4%BA%A4.jpg" style="height:190px" />

```	
	阶段2：提交阶段（commit phase）。
	在该阶段，协调者将基于第一个阶段的投票结果进行决策：提交或取消。
	当且仅当所有的参与者同意提交事务协调者才通知所有的参与者提交事务，否则协调者将通知所有的参与者取消事务。
	参与者在接收到协调者发来的消息后将执行响应的操作

```
<img src="https://raw.githubusercontent.com/zeyao/TechNotes/master/Document/%E4%B8%A4%E6%AE%B5%E6%8F%90%E4%BA%A42.jpg" style="height:190px" />



## Load balance 的算法：

#### 轮询（Round Robin）
- 轮询算法把每个请求轮流发送到每个服务器上。下图中，一共有 6 个客户端产生了 6 个请求，这 6 个请求  按 (1, 2, 3, 4, 5, 6) 的顺序发送。 最后，(1, 3, 5) 的请求会被发送到服务器 1，(2, 4, 6) 的请求会被发送到服务器 


<img src="https://raw.githubusercontent.com/zeyao/TechNotes/master/Document/roundRobin.jpg" style="height:300px" />

- 这样存在一个问题，只能适用于性能差不多的服务器, 如果另一个服务器的性能较差，就不可以了

<img src="https://raw.githubusercontent.com/zeyao/TechNotes/master/Document/badRoundRobin.jpg" style="height:300px" />

#### 加权轮询（Weighted Round Robbin）； 根据服务器效率的配比；

- 加权轮询是在轮询的基础上，根据服务器的性能差异，为服务器赋予一定的权值。例如下图中，服务器 1 被赋予的权值为 5，服务器 2 被赋予的权值为 1，那么 (1, 2, 3, 4, 5) 请求会被发送到服务器 1，(6) 请求会被发送到服务器 2

<img src="https://raw.githubusercontent.com/zeyao/TechNotes/master/Document/加权轮询.jpg" style="height:300px" />


#### 最少连接（least Connections） 将请求发给现在有最少连接的；
- 由于每个请求的链接时间不一样，使用加权轮询或者轮询可能会让一台服务器的链接过大而另一台很少，造成不均衡
- 最少链接法就是将请求发送给最少链接的服务器上，如下图，服务器1当前连接数最少，新来的请求6就会发送到服务器1上面

<img src="https://raw.githubusercontent.com/zeyao/TechNotes/master/Document/最小链接.jpg" style="height:300px" />



#### 最少加权连接（Weighted Least Connection） 将请求发给现在有最少连接的；

- 在最小连接的基础上，根据服务器的性能为每台服务器分配权重，根据权重计算出每台服务器能处理的连接数

#### 随机链接


#### 实现 1：使用 DNS 作为负载均衡器
- DNS服务器上配置多个域名对应IP的记录， 根据负载情况返回不同服务器的 IP 地址
- 是进行域名(domain name)和与之相对应的IP地址(IP address)转换的服务器。 DNS中保存了一张域名(domain name)和与之相对应的IP地址(IP address)的表，以解析消息的域名
- 这样可以通过映射关系，把请求的地址进行对相应服务器的分配
- 例如一个域名www.baidu.com对应一组web服务器IP地址，域名解析时经过DNS服务器的算法将一个域名请求分配到合适的真实服务器上。
- 因为是映射关系，不能够按服务器的处理能力来分配负载。DNS负载均衡采用的是简单的轮询算法，不能区分服务器之间的差异，不能反映服务器当前运行状态，所以其的负载均衡效果并不是太好。

#### 实现 2：http 重新定向
- 根据用户的http请求计算出一个真实的web服务器地址，并将该web服务器地址写入http重定向响应中返回给浏览器，由浏览器重新进行访问
- 例如访问 114.100.110.200 load balancer  -> 然后我们会根据服务度端的情况，redirect 去114.100.110.210
- 浏览器需要每次请求服务器才能完成一次访问，性能较差, 优点是简单

<img src="https://raw.githubusercontent.com/zeyao/TechNotes/master/Document/httprediect.jpg" style="height:300px" />


- 类似的做法是修改访问IP地址

#### 实现3 ： 反向代理 ？？？
- jianshu.com/p/208c02c9dd1d
- https://juejin.im/entry/5af135416fb9a07ab77427b5
- https://cloud.tencent.com/developer/article/1595214


