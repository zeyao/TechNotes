# JVM：从入门到放弃
```
关于JVM的一些知识总结
```

## JVM 基础概念： 

### JVM 栈
 - 线程私有， 生命周期和线程相同
 - 储存局部变量，基本数据类型，对象的引用 （对象的引用是指向对象储存的指针）
### 本地方法栈
 - 为了native方法服务
 - 有时候和JVM栈合二为一
### 堆
 - 是被线程共享的一块区域
 - 存放对象实例，所有的对象实例都在这里分配
 - GC主要注意的区域
 - 新生代（yong genernation）和老生代 (old generation)
 
### 方法区 (Method Area) 
 - JDK7 位于 Perm gern JDK8 移入 Metaspaces
 - 储存被 加载的类的信息，常量，静态变量

> 曾经在项目里jerkins build 跑Junit的时候 经常抛出 perm gern out of memory 的问题，后来发现是因为每一个junit class 都在 load 一个 context, 这些类会被加载很多遍，实际上是可以去重用的

### 常量池 (Constant Pool)
- 是方法区的一部分，用于存放编译期生成的各种字面量和符号引用，这部分内容将在类加载后进入方法区的运行时常量池中存放
- JAVA 8 中 常量池移入了 JAVA HEAP

##### 字符串常量池 （String contant pool）:
 - String 的value会在 字符串常量池 有一个 hashtable, 应用：Strings.intern()会在常量池里找，同一个value的String在常量池里里只有一个值（value as hash key）
 
##### 为什么要用常量池呢？
 - 字符串常量池有助于为Java运行时节省大量空间，避免频繁的创建和销毁对象而影响系统性能
 - 当我们使用双引号创建一个字符串时，首先在字符串常量池中查找是否有相同值的字符串，如果发现则返回其引用，否则它会在池中创建一个新的字符串，然后返回新字符串的引用。

- 如果使用new运算符创建字符串，则会强制String类在堆空间中创建一个新的String对象。

![avatar](https://images2018.cnblogs.com/blog/1418466/201808/1418466-20180810232840874-1190453861.png)

### MetaSpaces
 Java 8 中 PermGen 移出 HotSpot JVM, PermGen 最终被移除，方法区移至 Metaspace，字符串常量移至 Java Heap。

- 由于 PermGen 内存经常会溢出，引发java.lang.OutOfMemoryError: PermGen，因此 JVM 的开发者希望这一块内存可以更灵活地被管理

- Metaspace并不在虚拟机内存中而是使用本地内存， 最直接的表现就是java.lang.OutOfMemoryError: PermGen 问题将不复存在，因为默认的Metaspace分配只受本地内存大小的限制，也就是说本地内存剩余多少，理论上Metaspace就可以有多大，这解决了空间不足的问题。我们使用 -XX:MaxMetaspaceSize 参数来指定 Metaspace 区域的大小

## GC






License
----


