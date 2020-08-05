
# 设计模式 Design Pattern

## 单例模式 Singleton
- 简单点说，就是一个应用程序中，某个类的实例对象只有一个，你没有办法去new，因为构造器是被private修饰的，一般通过getInstance()的方法来获取它们的实例

#### Lazy Mode

```
public class Singleton {  
   private static Singleton instance;  
   private Singleton (){}  
   public static synchronized Singleton getInstance() {  
   if (instance == null) {  
       instance = new Singleton();  
   }  
       return instance;  
   }  
}

```
#### Eager Mode

```
public class Singleton {  
   private static Singleton instance = new Singleton();  
   private Singleton (){}  
   public static Singleton getInstance() {  
       return instance;  
   }  
}
```

## 观察者模式 Observer pattern
- do not call me I will call you;
- 对象间一对多的依赖关系，当一个对象的状态发生改变时，所有依赖于它的对象都得到通知并被自动更新

```
public interface Person {
   void getMessage(String s);
}


public class LaoWang implements Person {

   private String name = "小王";

   public LaoWang() {
   
   }

   @Override
   public void getMessage(String s) {
       System.out.println(name + "接到老板消息：" + s);
   }

}

public class LaoLi implements Person {

   private String name = "小李";

   public LaoLi() {
   }

   @Override
   public void getMessage(String s) {
       System.out.println(name + "接到老板消息：->" + s);
   }

}

public class Master {
    List<Person> list = new ArrayList<Person>();
    
    public Master(){
    
    }

    public void addPerson(Person person){
        list.add(person);
    }

    //遍历list，把自己的通知发送给所有需要通知的人
    public void notifyPerson() {
        for(Person person:list){
            person.getMessage("明天放假!");
        }
    }
}

```

- 测试类


```
public class Test {
   public static void main(String[] args) {

       Master master = new Master();
       LaoWang lw = new LaoWang();
       LaoLi ll = new LaoLi();

       //小王和小李在小美那里都注册了一下
       master(lw);
       master(ll);

       //小美向小王和小李发送通知
       xiao_mei.notifyPerson();
   }
}
```

## 装饰者模式 Decorator Pattern

#### 装饰者模式具有的一些特征
- 装饰者（decorator）和被装饰（扩展）的对象有着相同的超类（supertype
- 用于很多类做叠加，但是有共通的父亲类
- 比如星巴克卖咖啡，沙拉拼配
- 好处是简化代码

```
public class Food {

   private String name;

   public Food() {
   }

   public Food(String name) {
       this.name = name;
   }

   public String make() {
       return name;
   }
   
}

/面包类
public class Bread extends Food {

   private Food food;

   public Bread(Food food) {
       this.food = food;
   }

   public String make() {
       return food.make()+"+面包";
   }
   
   public String cost() {
       return 0.45 + food.cost();
   }
}

//奶油类
public class Cream extends Food {

   private Food food;

   public Cream(Food food) {
       this.food = food;
   }

   public String make() {
       return food.make()+"cream";
   }
   
   public String cost() {
       return 1.2 + food.cost();
   }
}

//蔬菜类
public class Vegetable extends Food {

   private Food food;

   public Vegetable(Food food) {
       this.food = food;
   }

   public String make() {
       return food.make()+"vege";
   }
   
   public String cost() {
       return 0.6 + food.cost();
   }

}
```



```
public class Test {

   public static void main(String[] args) {
       Food food = new Bread(new Vegetable(new Cream(new Food("香肠"))));
       System.out.println(food.make());
   }
   
}
```