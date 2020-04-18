# MQ和分布式 

### MQ的作用 ：
- 1: 中间件当broker解耦
- 2: 削峰， 可以相当于门卡流量
- 3: 提供异步的解决方式

## 高可用性
- 高可用性（英语：high availability，缩写为 HA），IT术语，指系统无中断地执行其功能的能力，代表系统的可用性程度。是进行系统设计时的准则之一。高可用性系统与构成该系统的各个组件相比可以更长时间运行

### Active MQ 如何保证高可用性 

- Master-slave，我们通过broker节点“消息互备”来达成设计要求。Slave不开启，所以客户端只能与master通讯，客户端无法与slave建立连接。
-  Slave 相当于一个备份节点

#### Share nothing storage master/slave

  <img src="https://raw.githubusercontent.com/zeyao/TechNotes/master/Document/M-S.png" style="height:300px" />
  
>  Share nothing storage master/slave master与slave有各自的存储系统，不共享任何数据。master接收到的所有指令(消息生产，消费，确认，事务，订阅等)都会同步发送给slave。在slave启动之前，首先启动master，在master有效时，salve将不会创建任何transportConnector，即Client不能与slave建立链接；如果master失效，slave 可以进行接管master。在master与slave之间将会建立TCP链接用来数据同步，如果链接失效，那么master认为slave离线。

- Share nothing storage master/slave 的局限性

> Shared nothing”模式下，有很多局限性。master只能有一个slave，而且slave不能继续挂载slave。如果slave较晚的接入master，那么master上已有的消息不会同步给slave，只会同步那些slave接入之后的新消息，那也意味着slave并没有完全持有全局的所有消息；所以如果此时master失效，slave接入之前的消息有丢失的风险。如果一个新的slave接入master，或者一个失效时间较长的旧master接入新master，通常会首先关闭master，然后把master上所有的数据Copy给slave(或旧master)，然后依次启动它们。事实上，ActiveMQ没有提供任何有效的手段，能够让master与slave在故障恢复期间，自动进行数据同步。

- 感知失败

> Master与slave之间通过Tcp链接感知对方的状态，基于链接感知状态的“三节点网络”(Client,Master,slave)，结果总是不可靠的；如果master与slave实例都有效，只是master与slave之间的网络“阻断”，此时slave也会认为master失效，如果slave提升为master，对于Client而言将会出现2个master的“幻觉”。

#### Shared storage master/slave 
- 允许集群中有多个slave共存，因为存储数据在salve与master之间共享(物理共享，例如数据库 或者File System)，所以当master失效后，slave自动接管服务，而无需手动进行数据的Copy与同步，也无需master与slave之间进行任何额外的数据交互，
- master与slave之间，通过共享文件的“排他锁” (exclusive lock)或者分布式排他锁(zookeeper)来决定broker的状态与角色，获取锁权限的broker作为master，如果master失效，它必将失去锁权限，那么slaves将通过锁竞争来选举新master

  <img src="https://raw.githubusercontent.com/zeyao/TechNotes/master/Document/MS-2.png" style="height:400px" />


### MQ 消息的可靠性
- 发送端的可靠性 ：**在本地数据建一张消息表，将消息数据与业务数据保存在同一数据库实例里，这样就可以利用本地数据库的事务机制**。事务提交成功后，将消息表中的消息转移到消息中间件，若转移消息成功则删除消息表中的数据，否则继续重传
- 保证接收端处理消息的业务逻辑具有幂等性：只要具有幂等性，那么消费多少次消息，最后处理的结果都是一样的
- 幂等性：就是用户对于同一操作发起的一次请求或者多次请求的结果是一致的，不会因为多次点击而产生了副作用。举个最简单的例子，那就是支付，用户购买商品使用约支付，支付扣款成功，但是返回结果的时候网络异常，此时钱已经扣了，用户再次点击按钮，此时会进行第二次扣款，返回结果成功，用户查询余额返发现多扣钱了，流水记录也变成了两条 : **解决方案 1:状态 + 乐观锁**

#### ActiveMQ 如何解决宕机和数据丢包 ： kahadb持久化

- 持久化，把数据写在磁盘上
- kahadb Data logs： **日志形式的储存消息**，数据被写入kahadb目录下一系列的.log文件，每一条需要持久化的数据会有一次写入，当一个文件大小超标，会建立一个新的，当文件内所有的消息都被消费，文件会被删除。
- 消息index用B-Tree形式储存，可以快速更新，消息index存储在内存中， 但是定期将内存中的消息索引保存到metadata store中，避免大量消息未发送时，消息索引占用过多内存空间
- Metadata cache : 用于存放在线消费者的消息，如果快速消费完成，就不需要写入磁盘了，根据messageID 创建索引
- MetaData Store: 定时从cache更新数据

