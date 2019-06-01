# Spring实用教程

--------------------

声明：这一套教程为现在火热的javaEE框架Spring的教程，其中Spring版本为5.x，教程所用的开发工具为IDEA2018版本，代码实例都会上传到github仓库，喜欢的可以star一下。

## 1.Spring概述

### 1.1 spring的简介

Spring 是分层的 Java SE/EE 应用 full-stack 轻量级开源框架，以 **IoC（Inverse Of Control：** 

**反转控制）和 AOP（Aspect Oriented Programming：面向切面编程）为内核**，提供了展现层 Spring  

MVC 和持久层 Spring JDBC 以及业务层事务管理等众多的企业级应用技术，还能整合开源世界众多 

著名的第三方框架和类库，逐渐成为使用最多的 Java EE 企业应用开源框架。 

### 1.2 spring的结构图

![](https://github.com/wlwqq/Spring-.git/raw/master/images/1.png)

对图片做一个解释，从下往上看

**Test**为Spring整合junit单元测试；

**Core Container**为spring框架的第一个特点，它的核心容器，包括beans，core，context，spel

**AOP和Aspect**两个一起是spring的第二个特点，面向切面编程

**Data Access/Integration**表示spring中与数据库连接的部分

**Web**表示spring中关于web前端的相关部分

**总结**：对于spring的学习，请抓住两点，第一，spring中的核心容器的学习，即IOC和DI，第二，面向切面编程的学习，即AOP，这一套spring教程的重点也在这两方面。

## 2.Spring中的IOC和DI

### 2.1 什么是IOC和DI

**IOC**反转控制，什么是反转控制呢？一开始学java的时候都听说过一句话，缺什么对象就new一个对象，但是在软件工程的中，有一个原则叫**低耦合高内聚**，当我们new一个对象的时候，我们就开始依赖这个对象了，这样就不符合低耦合高内聚的原则。

这里我们的思想来一个小的转变，以前，我们主动new对象，这种方式称为主动控制，因为我们new的对象，我们控制这个对象。那么什么叫反转控制呢？就是说现在我们不new对象了，让别人去new，那么这个控制权就给别人了，这种方式就是反转控制。那么这个别人是谁呢？就是大名鼎鼎的spring的IOC。这就是IOC的由来。

说完了IOC，DI又是什么呢？**DI全名依赖注入**，试想一下，new的对象里面的属性参数谁来给呢？我们自己new对象的话可以用set方法给对不对，我们交给Spring的IOC去new之后，谁来给呢？肯定是Spring来给，这种给的过程就叫**依赖注入**。

总结：Spring的**IOC**就是一个容器，这个容器是key-value形式的，就是一个map，我们需要对象的时候就用key从IOC容器中拿对象，对象在创建过程中，属性的声明就叫依赖注入。下面两张图就更加形象了，第二张图中的**工厂**就相当于Spring中的IOC容器。不多说了，这应该是最简单易懂的IOC和DI的介绍了。

![](D:\githubinbendi\spring\images\2.png)

![](D:\githubinbendi\spring\images\3.png)

### 2.2 IDEA实现IOC和DI（xml版本代码在springIOCxml中）

**（1）创建maven工程，并且导入spring5.x版本**

···

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>5.1.5.RELEASE</version>
    </dependency>
</dependencies>
```

···

**（2）编写service层和dao层**

具体代码请看仓库中springIOCxml项目

**（3）设置IOC容器（重点）**

1.在resource目录下新建bean.xml文件，编写如下

···

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd">
    
    
</beans>
```

···

2.以上步骤我们就有了一个IOC容器了，那么如何在容器中存储对象呢

···

```xml
<bean id="accountService" class="com.wanglei.service.impl.AccountServiceImpl"></bean>
<bean id="accountDao" class="com.wanglei.dao.impl.AccountDaoimpl"></bean>
```

···

**bean**标签用来存储对象，有三个属性：

**id**指定bean的名称，唯一，用来从容器中获取该对象

**class**给定全类名，spring用反射给你new对象

**scope**指定对象的作用范围，值有singleton（单例）、prototype（多例）、request、response、global session

其实有5个，但是另外两个我觉得不是特别重要，以后源码阶段再提

**（4）DI依赖注入（重点）**

有了对象还不行，如果对象中要注入属性怎么办？主要有2种注入方式

1.构造函数注入

```xml
<bean id="accountService" class="com.itheima.service.impl.AccountServiceImpl">
	<constructor-arg name="name" value="张三"></constructor-arg>
</bean>
```

顾名思义，必须这个类中有构造函数才可以用

**constructor-arg**标签用来注入数据，有2类属性，第一类：index、type、name、第二类：value、ref、

第一类用来找谁赋值

第二类用来赋什么值

比方说上面的代码，给name赋值为张三

2.set方法注入

```xml
<bean id="accountService" class="com.wanglei.service.impl.AccountServiceImpl">
        <property name="accountDao" ref="accountDao"></property>
</bean>
```

顾名思义，set方法注入，必须在这个类中有set方法

**property**标签用来注入数据，同样是2类属性，第一类name，第二类 value、ref

这里name指的是set方法后的内容，这一块可以查看springIOCxml项目中AccountServiceImpl中的set方法，一看就懂，ref用来指向本容器中的对象。

**（5）使用IOC容器**

在main函数中使用一下我们的IOC容器

```java
public static void main(String[] args) {
        //1.获取核心容器
        ApplicationContext ioc = new ClassPathXmlApplicationContext("bean.xml");
        //2.获取accountService对象
        AccountService accountService = (AccountService) ioc.getBean("accountService");
        //3.调用findAccount()方法
        accountService.findAccount();
    }
```

效果如下

![](D:\githubinbendi\spring\images\4.png)

至此Spring的IOC和DI的xml配置版本就完成了

### 2.3 IDEA实现IOC和DI（注解版，代码在springiocAnnotation中）

上面对于springIOC和DI的xml配置也可以完全使用注解来代替，注解和xml只是两种不同的形式而已，但是他们的功能是完全一样的。

那么如何学习springIOC的注解呢？首先要清楚Spring的IOC在干两件事，第一，把对象注入到容器中；第二，给对象注入依赖。在xml中**bean**标签表示把对象注入到容器中；**constract-arg**和**property**表示将依赖注入到对象中，一个是利用构造函数，一个是利用set方法。

那么注解版也一样咯，肯定有某个注解将对象注入到容器中，又有某个注解将依赖注入。下面带着这样的思路配置springIOC的注解版

**（1）创建项目导入maven依赖**

```xml
    <dependencies>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>5.1.5.RELEASE</version>
        </dependency>
    </dependencies>
```

**（2）书写service层和dao层**

在本仓库中查看springiocAnnotation项目，程序运行的逻辑与之前一样，service调用dao。

**（3）调整bean.xml**

第一：导入context命名空间

```xml
xmlns:context="http://www.springframework.org/schema/context"
```

第二：开启注解扫描功能

```xml
<context:component-scan base-package="com.wanglei"></context:component-scan>
```

**（4）使用注解**

开头分析过，无非是两种注解，一个注入对象的，一个依赖注入的，这里先看第一个

**@Component**

```java
@Component(value = "accountService")
public class AccountServiceImpl implements AccountService {
```

表示把这个类产生的对象放到容器中，value=“accountService”表示这个对象的id是accountService

,还有三个衍生的注解：**@Controller、@Service、@Repository**：意思很明显，三层对应的对象，这三个只是给看的，其实没什么区别。那么我们的项目就应该这样注解了

```java
@Service(value = "accountService")
public class AccountServiceImpl implements AccountService {

@Repository(value = "accountDao")
public class AccountDaoimpl implements AccountDao {
```

关于依赖注入的种类较多，这里先分析再解释，一个对象中也许有基本属性，比如name、age等，也许有对象树属性，比如这个例子的service中有dao对象，那么属性的类型不同，注入方式就不一样

**@Value**

用来给基本属性注入的，value=“王磊”表示给这个属性注入王磊

**@Autowired、@Qualifier、@Resource**

这三个分别用来注入对象的，用法如下

```java
@Autowired
private AccountDao accountDao;
    
@Autowired
@Qualifier(value = "accountDao")
private AccountDao accountDao;

@Resource(name = "accountDao")
private AccountDao accountDao;
```

解释一下，@Autowired表示根据类型自动注入、@Qualifier表示在@Autowired基础上根据对象id注入、@Resource表示根据对象id注入。

**@Scope**

改变作用范围，使用如下：

```java
@Service(value = "accountService")
@Scope(value = "singleton")
public class AccountServiceImpl implements AccountService {
```

**（5）彻底告别bean.xml文件**

**@Configuration**

这个注解标记在一个类上，表示这个类是一个bean.xml的替身，有了这个注解，就可以删除bean.xml文件了

```java
@Configuration
public class SpringConfiguration {
```

**@ComponentScan**

这个注解与bean.xml中<context:component-scan base-package="com.wanglei">意思相同

用法如下

```java
@Configuration
@ComponentScan(value = "com.wanglei")
public class SpringConfiguration {
```

**@Bean**

写在方法上，表示将这个方法的返回值注入到容器中，这里写一个新的配置类，就是关于jdbc的配置类，当我们的dao需要一个QueryRunner，而QueryRunner需要一个连接池的时候，依赖关系就变成了这样：service调用dao，dao调用QueryRunner，QueryRunner调用c3p0连接池。

（1）导入maven设置如下

```xml
        <dependency>
            <groupId>commons-dbutils</groupId>
            <artifactId>commons-dbutils</artifactId>
            <version>1.6</version>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.47</version>
        </dependency>

        <dependency>
            <groupId>c3p0</groupId>
            <artifactId>c3p0</artifactId>
            <version>0.9.1.2</version>
        </dependency>
```

（2）@Bean注入QueryRunner

```java
    @Bean(name = "queryRunner")
    @Scope(value = "prototype")
    public QueryRunner getQueryRunner(DataSource dataSource){
        return new QueryRunner(dataSource);
    }

```

（3）@PropertySource("classpath:jdbcConfig.properties")和@Bean注入datasource，因为QueryRunner需要它

**@PropertySource("classpath:jdbcConfig.properties")**

表示引入jdbcConfig.properties文件进来可以i读取属性

```java
    @Value("${jdbc.driver}")
    String driver;
    @Value("${jdbc.username}")
    String username;
    @Value("${jdbc.url}")
    String url;
    @Value("${jdbc.password}")
    String password;

    @Bean(name = "queryRunner")
    @Scope(value = "prototype")
    public QueryRunner getQueryRunner(@Autowired DataSource dataSource){
        return new QueryRunner(dataSource);
    }

    @Bean(name = "dataSource")
    public DataSource getDataSource(){
        try {
            ComboPooledDataSource ds = new ComboPooledDataSource();
            ds.setDriverClass(driver);
            ds.setJdbcUrl(url);
            ds.setUser(username);
            ds.setPassword(password);
            return ds;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
```

**@Import**

导入其他配置类到主配置类，用法如下

```java
@Configuration
@ComponentScan(value = "com.wanglei")
@PropertySource("classpath:jdbcConfig.properties")
@Import(JdbcConfiguration.class)
public class SpringConfiguration {

}

```

（6）测试我们的注解版IOC

```java
public class Main {
    public static void main(String[] args) {
        //1.获取核心容器
        ApplicationContext ioc = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        //2.获取accountService对象
        AccountService accountService = (AccountService) ioc.getBean("accountService");
        //3.调用findAccount()方法
        accountService.findAccount();
    }
}
```

![](D:\githubinbendi\spring\images\5.png)



## 3.Spring中的单元测试

未完待续