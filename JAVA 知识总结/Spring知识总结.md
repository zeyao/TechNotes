# Spring 知识总结



### AOP

- AOP的核心思想就是面向切面，什么是切面呢，就是可以把一些非主流程的代码(比如:验证、事务管理、缓存、日志记录等), 横向抽取出来放在一个公共的类中, 也就是切面. 可以通过Spring框架来配置该切面.



 <img src="https://raw.githubusercontent.com/zeyao/TechNotes/master/Document/AOP1.png" style="height:300px" />
 
- 比如这个验证用户就是一个可公用的，在很多流程上都需要用的， 那么可以这个验证做成一个切面


<img src="https://raw.githubusercontent.com/zeyao/TechNotes/master/Document/AOP2.png" style="height:300px" />

- 例子： 比如写Log， 可以做一个@Aspect， 写进入Method和输出Method的时间， 这样可以一个annotaion解决问题

- **实际项目中的例子， MSC 的publish to DHR， 可以用切面代替，这样就不需要每一次调用scan DB publish的方法，在每一个method上加上@Aspect annotation 就可以了**               


### 通知（Advice）

- 在AOP术语中，切面的工作被成为通知。通知定义了切面是什么以及何时使用。

- @Pointcut 定义介入切面
- BeforeAdvice
- AfterAdvice

- @Aspect 定义切面


```

@Aspect
public class AspectJAnnotationAdvisingObj {
	private static final Logger logger = LoggerFactory.getLogger(AspectJAnnotationAdvisingObj.class);
	
    @Pointcut("execution(* com.springtest.AspectJAnnotationAdvisedObj.perform(..))")
	public void performance() {
		
	}
	
    @Before("performance()")
	public void beforeExecute(){
		logger.info("AspectJAnnotationAdvisingObj beforeExcecute().");
	}
	
    @AfterReturning("performance()")
	public void afterExecute(){
		logger.info("AspectJAnnotationAdvisingObj afterExecute().");
	}


```