
# SOLID 原则

##  S - Single Responsibility Principle

- 此原则意味着一个类应该只做且只能做一件事情, 否则拆分

## Open-Closed Principle

- 常言道，我们的类应该对扩展开放，对修改关闭
- 我们应该以插件式的方式来编写类和模块。如果我们需要额外的功能，我们不应该修改类，而是能够嵌入一个提供这个额外功能的不同类

```
class Calculator {
  public float add(float a, float b) {
    return a + b
  }
  public float subtract(float a, float b) {
    return a — b
  }
}

```

- 如果要再加上 * / 就要修改类



```
interface Operation {
  float compute(float a, float b)
}

class Addition implements Operation {
  public float compute(float a, float b) {
    return a + b
  }
}
class Subtraction implements Operation {
  public float compute(float a, float b) {
    return a — b
  }
}

class Calculator {
  public float calculate(float a, float b, Operation operation) {
    return operation.compute(a, b)
  }
}

```
- 可以这样使用，把加减乘除抽象成类来完成，以后只用新加这个Operation接口的实现，比如Multiply这样减少了耦合

```
Calculator calculator = new Calculator()

Addition addition = new Addition()
Subtraction subtraction = new Subtraction()

float sum = calculator.calculate(10, 2, addition) //the value of sum is 12
float diff = calculator.calculate(10, 2, subtraction) //the value of diff is 8

```

## L - Liskov Substitution Principle

- 如果对象 A 是对象 B 的子类，或者对象 A 实现了接口 B（本质上讲，A 就是 B 的一个实例），那么我们应该能够在不做任何特殊处理的情况下，像使用一个对象 B 或者 B 的一个实例那样使用对象 A

## I 接口隔离原则（Interface Segregation Principle

- 我们应该保持接口短小，在实现时选择实现多个小接口而不是庞大的单个接口

- 如有下面这个bike interface and MountainBike 类
- 对于 ClassicBike handBrakeFront 就不适用了
- 还有例子就是比如 动物 可以归类为飞行动物，也可以归类为划水，哺乳等
- 接口隔离原则的优势之一就是我们可以在多个对象上组合匹配接口，这提高了我们代码的灵活性和模块化

```
interface Bike {
  void pedal()
  void steer()
  void handBrakeFront()
  void handBrakeBack()
}

class MountainBike implements Bike {
  override void pedal() {
    // pedal implementation
  }
  
  override void steer() {
    // steer implementation
  }
  
  override void handBrakeFront() {
    // front hand brake implementation
  }
  
  override void handBrakeBack() {
    // back hand brake implementation
  }
  
  void changeGear() {
    // change gear code
  }
}

class ClassicBike implements Bike {
  override pedal() {
    // pedal implementation
  }
  
  override steer() {
    // steer implementation
  }
  
  override handBrakeFront() {
    // no code or throw an exception
  }
  
  override handBrakeBack() {
    // no code or throw an exception
  }
  
  void brake() {
    // foot brake code
  }
}
```

- 因为有手刹车，脚刹车两个属性分类，可以分别设计成接口 （类似动物的飞行和游泳）

```
interface Bike() {
  void pedal()
  void steer()
}

interface HandBrakeBike {
  void handBrakeFront()
  void handBrakeBack()
}

interface FootBrakeBike {
  void footBrake()
}

```
- 对于 MountainBike implement Bike 和 HandBrakeBike

```

class MountainBike implements Bike, HandBrakeBike {
  override pedal() {

  }
  override handBrakeFront() {
  
  }
  
  override handBrakeBack() {
    
  }
}

class ClassicBike implements Bike, FootBrakeBike {
  override pedal() {

  }
  override steer() {
  
  }
  
  override footBrake() {
    
  }
}

```


