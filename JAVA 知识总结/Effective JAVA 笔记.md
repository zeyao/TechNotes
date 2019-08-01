# Effective JAVA 
##Chapter 1 Creating and Destroying Object



### 1. Static Factory Method
- 什么是static factroy method 观察下面的例子 BigInteger 类: 

  
``` 
public static BigInteger probablePrime(int bitLength, Random rnd) {
        if (bitLength < 2)
            throw new ArithmeticException("bitLength < 2");

        return (bitLength < SMALL_PRIME_THRESHOLD ?
                smallPrime(bitLength, DEFAULT_PRIME_CERTAINTY, rnd) :
                largePrime(bitLength, DEFAULT_PRIME_CERTAINTY, rnd));
}
// BigInteger 类的 static factory 用例

public class Test {

	BigInteger b = BigInteger.probablePrime(1, new Random(10));
	
}    
```

- 使用 static factroy method 好处在于更像更适合工具类的使用方式，命名代表了使用的意义；
- 调用过程简单，不需要new, new object封装在了method里，使用者的code更加的clean

> 如果上面Test class例子里，probablePrime 不是一个static method, 就需要new 的操作，但是使用了static factory method, 调用起来更加的简洁


- 返回可以是一个子类
s