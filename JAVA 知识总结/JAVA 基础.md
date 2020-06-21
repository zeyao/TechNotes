# JAVA 和 CS 基础



### 线程和进程

  - 进程是程序在计算机上的一次执行活动。当你运行一个程序，你就启动了一个进程。进程是操作系统分配资源的单位
  -
  - 线程是属于进程的，线程运行在进程空间内，同一进程所产生的线程共享同一内存空间，是CPU调度和分派的基本单位
  - 多线程的意义在于一个应用程序中，有多个执行部分可以同时执行。但操作系统并没有将多个线程看做多个独立的应用
  - 线程的状态： New, Runnable, Waitting, Blocked, Terminated.

### 进程间的通信方式和对应的同步方式

  - 使用 message queue
  - 使用socket
  - HTTP call
  - 管道（pipe）

### 线程间的通信方式和对应的同步方式
  - Notify wait
  - lock
  - volatile


### JAVA 主内存和工作内存

Java内存模型规定了所有的变量都存储在主内存（MainMemory）中；线程的工作内存中保存了被该线程使用到的变量的主内存副本拷贝；线程对变量的所有操作（读取、赋值等）都必须在工作内存中进行，而不能直接读写主内存中的变量

不同的线程之间也无法直接访问对方工作内存中的变量，线程间变量值的传递均需要通过主内存来完成，线程、主内存、工作内存三者的交互关系如图所示

![avatar](https://raw.githubusercontent.com/zeyao/TechNotes/master/Document/509261-20190119235012605-1926250043.jpg)

### Volatile 关键字
Volatile 保证了线程之间的可见性
Volitaile变量保证了一个线程修改了共享的变量的值，其他线程能够立即得到这个修改
任何新值会立即同步到主内存，每次使用前都会从主内存刷新
普通变量无法保证到这一点ß

### CAS是什么
- CAS（Compare and swap）即比较并交换，也是实现我们平时所说的自旋锁或乐观锁的核心操作
- 如何实现， 内存地址A储存着value = 10
- 线程1 要把value +1， 对于线程A来说，旧的预期值A= 10 新修改值 B= 11
- 在线程1提交之前，线程2抢先一步把value 修改成了11
- 线程1 提交的时候，首先比较预期值A = 10 和 实际值 （11）， 不一样提交失败，重新获得A = 11 提交+1；
- DB乐观锁的version number用的就是这个机制
- CAS适用于并发情况不是很严重的情况, 如果并发比较高，许多线程反复尝试更新一个变量，却一直不成功。会给CPU带来很大的开销


### 自旋锁
- 如果持有锁的线程能在很短时间内释放锁资源，那么那些等待竞争锁的线程就不需要做内核态和用户态之间的切换进入阻塞挂起状态，它们只需要等一等（自旋），等持有锁的线程释放锁后即可立即获取锁，这样就避免用户线程和内核的切换的消耗
- 线程自旋需要消耗CPU，也就是要CPU做无用功，如果一直获得不到锁，那么不能一直自旋，所以自旋需要设定一个最大等待时间，
- 若超过自旋等待时间还没有释放锁，那么线程就进入阻塞状态
- 优点： 减少线程阻塞
- 缺点： 锁竞争激烈，造成浪费

### Sycronized 的锁机制
- 锁的本质是看哪一个线程获得了锁
- Synchronized关键字会让没有得到锁资源的线程进入BLOCKED（线程阻塞）状态，而后在争夺到锁资源后恢复为RUNNABLE状态，这个过程中涉及到操作系统用户模式和内核模式的转换，代价比较高。
- 为了换取性能，JVM在内置锁上做了非常多的优化，膨胀式的锁分配策略就是其一

#### 重量级锁
- 适用于多个线程竞争锁
- 利用互斥量（mutex）判断， 成本高，就是让争抢锁的线程从用户状态转化成内核，用CPU进行协调

#### 轻量级锁
- 如果完全没有实际的锁竞争，那么申请重量级锁就是浪费，轻量级锁就是减少在不同线程，没有实际竞争锁的情况下，使用重量级锁进行的性能消耗
- 使用轻量级锁不需要互斥量 （mutex）， 仅仅需要把Mark Word 中的CAS重新指向lock record, 如果更新成功，就说明轻量级锁获取成功， 
- 如果CAS更新失败，说明有并发，膨胀为重量级锁


#### 偏向锁
- 偏向锁适用于，至始至终使用锁的线程只有一个，那么维护轻量级锁都是浪费的
- 偏向锁可以减少没有竞争且只有一个锁的情况下的消耗
- 轻量级锁每次申请、释放锁都至少需要一次CAS，但偏向锁只有初始化时需要一次CAS
- 偏向锁假定将来只有第一个申请锁的线程会使用锁， 那么只需要在Mark Word中CAS记录owner，（从 0 -> owner）如果不能完成此项操作，说明不是偏向锁，需要膨胀为轻量级锁

> 偏向锁：无实际竞争，且将来只有第一个申请锁的线程会使用锁。

> 轻量级锁：无实际竞争，多个线程交替使用锁；允许短时间的锁竞争。

> 重量级锁：有实际竞争，且锁竞争时间长。

- 这个帖子讲的很好：https://blog.csdn.net/zqz_zqz/article/details/70233767

### JAVA Collections and Maps:
  ![avatar](https://upload-images.jianshu.io/upload_images/3110311-9f59b74d0239843f.jpg)
  
  - Stack 是 Vector 的一个子类， verctor 类似于 arraylist, 通过 syncronized 实现了线程安全 
  - LinkedList 通过实现Deque实现了Queue ：LinkedList implements List<E>, Deque<E> ；(Deque extends Queue) 所以要使用Queue的特性，需要Queue q = new LinkList<>(); 但这时不能直接访问LinkedList的非Queue的方法
  - Queue, Deque, List, Set, Map 都只是interface
  
  ![avatar](https://ask.qcloudimg.com/http-save/yehe-3170721/7wo9hg4rer.png)
  

- ArrayList 和 linkedList 区别， Arraylist 是基于数组实现的，定位搜索某一位快 O(1) list.get(2);  LinkedList基于指针的node list是遍历来寻找的。 LinkedList在删除增加元素的时候快, 因为 Arraylist需要把位置重新计算,在某一位插入或者删除，之后的所有元素都要位移。





``` 
### ArrayBlockingQueue、LinkedBlockingQueue 区别
- 都是线程安全
- 都用了锁的机制
- ArrayBlocking queue 使用了一个锁
- LinkedBlockingQueue 基于linkedlist 的机制， 头尾使用不同的锁， 更加适合生产消费者模式， 其添加采用的是putLock，移除采用的则是takeLock，这样能大大提高队列的吞吐量，也意味着在高并发的情况下生产者和消费者可以并行地操作队列中的数据，以此来提高整个队列的并发性能。
- ArrayBlockingQueue是对BlockingQueue的一个数组实现，它使用一把全局的锁并行对queue的读写操作

- ConcurrentLinkedQueue 高并发下性能更好 基于CAS算法
- ArrayBlocking queue基于数组实现，所以需要定义长度大小
- 