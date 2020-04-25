# Hash一致性

## Redis集群的使用

- 在使用redis的时候，为了保证高可用性，利用master slave模式，搭建redis集群
- 当容量很大的时候redis会进行分分库（分节点），这个时候就引入的hash
- 如果不使用Hash，需要遍历每一个节点才知道请求的储存在哪一个redis节点，这样性能开销大

> 假设，我们有一个社交网站，需要使用Redis存储图片资源，存储的格式为键值对，key值为图片名称，value为该图片所在文件服务器的路径，我们需要根据文件名查找该文件所在文件服务器上的路径，数据量大概有2000W左右，按照我们约定的规则进行分库，规则就是随机分配，我们可以部署8台缓存服务器，每台服务器大概含有500W条数据，并且进行主从复制，示意图如下


 <img src="https://raw.githubusercontent.com/zeyao/TechNotes/master/Document/redis%20cluster.jpg" style="height:400px" />
 
#### Redis 使用Hash

- 如果我们使用Hash的方式，在每一张图片进行分库的时候都可以定位到特定的服务器
- 这样当我们要查询a.png, 由于有四台服务器，就可以通过哈希定位到服务器，提升性能
 <img src="https://raw.githubusercontent.com/zeyao/TechNotes/master/Document/Redis%20%E5%93%88%E5%B8%8C.jpg" style="height:400px" />

#### 一致性问题
- 当服务器数量进行变动（扩容 故障）的时候，所有缓存的位置都会变动，当我们增加一台服务器的时候，哈希会变动，就会因为缓存无法命中

#### Hash一致性算法
- Hash算法将整个哈希空间组织成一个虚拟的圆环，0 ～ 2^32-1

 <img src="https://raw.githubusercontent.com/zeyao/TechNotes/master/Document/hash%20circle.jpg" style="height:400px" />
 
- 按照顺时针防线，将每个服务器进行一个哈希，确定在哈希环上的位置

  <img src="https://raw.githubusercontent.com/zeyao/TechNotes/master/Document/hash%20circle 2.jpg" style="height:400px" />
  
- 当访问服务器的时候，根据Object 计算出哈希值，确定到在环上的位置，按照顺时针，第一个遇到的服务器就是定位的服务器

- 如图 Object A经过计算，定位到了NodeA

  <img src="https://raw.githubusercontent.com/zeyao/TechNotes/master/Document/hash%20circle 3.jpg" style="height:400px" />
  
- 这样如果Node C挂了，ABD不会受到影响，只有原本C的对象会被重新定位到NodeD。这样只会影响到挂掉的那一台服务器

  <img src="https://raw.githubusercontent.com/zeyao/TechNotes/master/Document/hash%20cicle%204.jpg" style="height:400px" />
  
- 同理当扩容增加节点Node X 的时候，ABD都不受影响，只有ObjectC的区域被重新定向到NodeX

<img src="https://raw.githubusercontent.com/zeyao/TechNotes/master/Document/hash%20cicle%205.jpg" style="height:400px" />

- 这样保证了任何的节点的增减只需要重新定位一下部分数据，可扩展性和容错性都比较好

#### Hash环的数据倾斜的问题

- 当节点比较少的时候，节点分布不均匀可能会导致数据倾斜，被缓存的对象大部分集中在了一套机器

<img src="https://raw.githubusercontent.com/zeyao/TechNotes/master/Document/hash%E5%80%BE%E6%96%9C.jpg" style="height:400px" />

- 引入了虚拟节点机制，可以为每台服务器计算三个虚拟节点，于是可以分别计算 “Node A#1”、“Node A#2”、“Node A#3”、“Node B#1”、“Node B#2”、“Node B#3”的哈希值，于是形成六个虚拟节点

<img src="https://raw.githubusercontent.com/zeyao/TechNotes/master/Document/hash%E5%80%BE%E6%96%9C2.jpg" style="height:400px" />

- 实际中，经常把虚拟节点设置的很大，所以保证了均匀分布



  
  
  

