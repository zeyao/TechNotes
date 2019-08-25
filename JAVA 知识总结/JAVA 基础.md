# JAVA 和 CS 基础



### 线程和进程

  - 进程是程序在计算机上的一次执行活动。当你运行一个程序，你就启动了一个进程。进程是操作系统分配资源的单位
  -
  - 线程是属于进程的，线程运行在进程空间内，同一进程所产生的线程共享同一内存空间，是CPU调度和分派的基本单位
  - 多线程的意义在于一个应用程序中，有多个执行部分可以同时执行。但操作系统并没有将多个线程看做多个独立的应用

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



### JAVA Collections and Maps:
  ![avatar](https://upload-images.jianshu.io/upload_images/3110311-9f59b74d0239843f.jpg)
  
  - Stack 是 Vector 的一个子类， verctor 类似于 arraylist, 通过 syncronized 实现了线程安全 
  - LinkedList 通过实现Deque实现了Queue ：LinkedList implements List<E>, Deque<E> ；(Deque extends Queue) 所以要使用Queue的特性，需要Queue q = new LinkList<>(); 但这时不能直接访问LinkedList的非Queue的方法
  - Queue, Deque, List, Set, Map 都只是interface
  
  ![avatar](https://ask.qcloudimg.com/http-save/yehe-3170721/7wo9hg4rer.png)
  

- ArrayList 和 linkedList 区别， Arraylist 是基于数组实现的，定位搜索某一位快 O(1) list.get(2);  LinkedList基于指针的node list是遍历来寻找的。 LinkedList在删除增加元素的时候快, 因为 Arraylist需要把位置重新计算,在某一位插入或者删除，之后的所有元素都要位移。





``` 

