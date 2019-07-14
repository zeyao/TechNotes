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


### JAVA Collections and Maps:
  ![avatar](https://upload-images.jianshu.io/upload_images/3110311-9f59b74d0239843f.jpg)
  
  - Stack 是 Vector 的一个子类， verctor 类似于 arraylist, 通过 syncronized 实现了线程安全 
  - LinkedList 通过实现Deque实现了Queue ：LinkedList implements List<E>, Deque<E> ；(Deque extends Queue) 所以要使用Queue的特性，需要Queue q = new LinkList<>(); 但这时不能直接访问LinkedList的非Queue的方法
  - Queue, Deque, List, Set, Map 都只是interface
  
  ![avatar](https://ask.qcloudimg.com/http-save/yehe-3170721/7wo9hg4rer.png)
  







``` 

