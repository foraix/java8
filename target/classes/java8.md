# Java8新特性

## 新特性

> 速度更快
>
> 代码更少（ lambda表达式  核心）
>
> 强大的Stream Api （核心）
>
> 便于并行
>
> 最大化减少空指针异常

## lambda表达式 

### 概念

+ lambda是一个匿名的函数，可以理解为一段可以传递的代码。
+ 引入了箭头操作符( -> )：左侧，lambda的参数列表 右侧，lambda中所需要执行的功能。
+ 实际上是一语法糖只是写起来更加简单。
+ 参数类型可以不写，因为JVM可以根据上下文推断，即类型推断，也是语法糖。
+ 需要函数式接口的支持（只有一个抽象方法的接口）

```java
package net.tf.java8.lambda;

import lombok.*;

/**
 * @author hy
 * @version 1.00
 * @time 2019/3/13 16:05
 * @desc 员工pojo
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private String name;
    private int age;
    private double salary;
}
```

```java
package net.tf.java8.lambda;

/**
 * @author hy
 * @version 1.00
 * @time 2019/3/13 16:14
 * @desc
 */
public interface
MyPredicate<T> {
    boolean test(T t);
}
```

```java
package net.tf.java8.lambda;

/**
 * @author hy
 * @version 1.00
 * @time 2019/3/13 16:14
 * @desc
 */
public class MyPredicateByAge implements MyPredicate<Employee> {

    @Override
    public boolean test(Employee employee) {
        return employee.getAge() > 35;
    }
}
```

### 方式一：接口

```java
@org.junit.Test
public void test2() {
    List<Employee> list = Arrays.asList(
            new Employee("xx", 59, 666.33),
            new Employee("ss", 36, 62.33),
            new Employee("zz", 18, 6699.33),
            new Employee("gg", 65, 6689.33)
    );

    //按照年龄分类
    List<Employee> employees = filterEmployee(list, new MyPredicateByAge());
    for (Employee e : employees
    ) {
        System.out.println(e);
    }
}




 private static List<Employee> filterEmployee(List<Employee> employeeList, MyPredicate<Employee> me) {
        List<Employee> employeeList1 = new ArrayList<>();
        for (Employee e : employeeList
        ) {
            if (me.test(e)) {
                employeeList1.add(e);
            }
        }
        return employeeList1;
    }
```

### 方式二：匿名内部类

``` java
@org.junit.Test
public void test6() {
    List<Employee> list = Arrays.asList(
            new Employee("xx", 59, 666.33),
            new Employee("ss", 36, 62.33),
            new Employee("zz", 18, 6699.33),
            new Employee("gg", 65, 6689.33)
    );

    //按照薪水分类
    List<Employee> employees = filterEmployeeA(list, new MyPredicate<Employee>() {
        @Override
        public boolean test(Employee employee) {
            return employee.getSalary() >= 800.00;
        }
    });
    employees.forEach(System.out::println);
}
```

### 方式三：lambda表达式

```java
@org.junit.Test
public void test3() {
    List<Employee> list = Arrays.asList(
            new Employee("xx", 59, 666.33),
            new Employee("ss", 36, 62.33),
            new Employee("zz", 18, 6699.33),
            new Employee("gg", 65, 6689.33)
    );

    //按照薪水分类
    List<Employee> employees = filterEmployeeA(list, employee -> employee.getSalary() >= 5000.00);
    employees.forEach(System.out::println);
}
```

### 方式四：Steram Api

```java
@org.junit.Test
public void test4() {
    List<Employee> list = Arrays.asList(
            new Employee("xx", 59, 666.33),
            new Employee("ss", 36, 62.33),
            new Employee("zz", 18, 6699.33),
            new Employee("gg", 65, 6689.33),
            new Employee("hh", 85, 6989.33)
    );
    list.stream()
            .filter((employee -> employee.getSalary() >= 50.00))
            .forEach(System.out::println);

    System.out.println("-----------------------------------");
    //提取名字
    list.stream()
            .map(Employee::getName)
            .forEach(System.out::println);
}
```



### 基本使用

```java
/**
     * 语法格式一
     * 无参数，无返回值
     */
    @Test
    public void test1() {
        //JDK1.7以前必须为final修饰
//        final int num = 0;
        //JDK1.8默认加上了final修饰
        int num = 0;
        Runnable r = () -> System.out.println("xxx" + num);
        r.run();
    }

    /**
     * 语法格式二
     * 一个参数，无返回值
     */
    @Test
    public void test2() {
        Consumer<String> consumer = (x) -> System.out.println(x);

//        Consumer<String> consumer = System.out::println;
        consumer.accept("xxx");
    }

    /**
     * 语法格式三
     * 一个参数（小括号可以省略不写，一般建议写上），无返回值
     */
    @Test
    public void test3() {
        Consumer<String> consumer = x -> System.out.println(x);
    }

    /**
     * 语法格式四
     * 多个参数，有返回值,lambda中有多个语句
     */
    @Test
    public void test4() {
        Comparator<Integer> comparator = (x, y) -> {
            return Integer.compare(x, y);
        };
//        Comparator<Integer> comparator = Integer::compare;
        int compare = comparator.compare(6, 6);
        System.out.println(compare);

    }

    /**
     * 语法格式五
     * 多个参数，有返回值,lambda中有一个语句 return和大括号可以省略不写
     */
    @Test
    public void test5() {
       Comparator<Integer> comparator = (x, y) -> Integer.compare(x,y);
    }

    /**
     * 语法格式六
     * 参数类型可以不写，因为JVM可以根据上下文推断，即类型推断
     */
    @Test
    public void test6() {
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x,y);
    }
```

```java
@FunctionalInterface
public interface MyFun<T> {

    T getValue(T value);
}
```

```java
@Test
public void test7() {
    Integer operation = operationInteger(200, (x) -> x * x);
    System.out.println(operation);
}

public Integer operationInteger(Integer num, MyFun<Integer> myFun) {
    return myFun.getValue(num);
}

@Test
public void test8() {
   String value = operationString("xxx", String::toString);
    System.out.println(value);
}

public String operationString(String string,MyFun<String> myFun) {
    return myFun.getValue(string);
}
```

### 使用lambda实现排序

``` java
List<Employee> employees = Arrays.asList(
        new Employee("xx", 59, 666.33),
        new Employee("ss", 36, 62.33),
        new Employee("zz", 18, 6699.33),
        new Employee("gg", 65, 6689.33)
);
@Test
public void test1() {
    Collections.sort(employees,(e1,e2) -> {
        if (e1.getAge() == e2.getAge()) {
            return e1.getName().compareTo(e2.getName());
        } else {
            return Integer.compare(e1.getAge(),e2.getAge());
        }
    });
    employees.forEach(System.out::println);
}
```

### 其他

> @FunctionalInterface 函数式接口，只有一个抽象方法的接口



### Java8四大内置核心函数式接口

要用Lambda表达式的时候总要自己写函数式接口的弥补

+ Consumer<T>: 消费型接口void accept(T t)

~~~java
/**
 * Consumer<T>: 消费型接口void accept(T t)
 */
@Test
public void test1() {
    happy(100.00,(money) -> System.out.println("你总共花了" + money));
}

public void happy(double money, Consumer<Double> consumer){
    consumer.accept(money);
}
~~~

+ Supplier<T>:供给型接口T get()

```java
/**
 * Supplier<T>:供给型接口T get()
 */
@Test
public void test2() {
    //Math.random() 对象产生的[0,1)无论放大多少倍都有可能为0 所以应该注意除零异常
    List<Integer> numList = getNumList(100000, () -> (int) (Math.random() * 100));
    numList.forEach((x) -> System.out.println("xxx"+x));
}

/**
 * 产生指定个数的整数集合
 */
public List<Integer> getNumList(int num, Supplier<Integer> supplier) {
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < num; i++) {
        Integer integer = supplier.get();
        list.add(integer);
    }
    return list;
}
```

+ Function<T,R> :函数型接口R apply(T t)

```java
/**
 * Function<T,R> :函数型接口R apply(T t)
 */
@Test
public void test3() {
    Integer xxxx = strHandler("xxxx", (String::length));
    System.out.println(xxxx);
}

public Integer strHandler(String s, Function<String,Integer> function) {
    return function.apply(s);
}
```

+ Predicate<T>:断言型接口Boolean test(T t) 

```java
/**
 * Predicate<T>:断言型接口Boolean test(T t)
 */
@Test
public void test4() {
    List<String> strings = new ArrayList<>();
    strings.add("xxxxx");
    strings.add("xxx");
    strings.add("xxxxxxx");
    strings.add("xxxxxxxxxxx");
    strings.add("xx");
    List<String> stringList = strings(strings, (x) -> x.length() >= 4);
    stringList.forEach(System.out::println);
}

/**
 * 将满足条件的字符串加入集合
 */
public List<String> strings(List<String> list, Predicate<String> predicate) {
    List<String> strings = new ArrayList<>();
    for (String s : list
         ) {
        if (predicate.test(s)) {
            strings.add(s);
        }
    }
    return strings;
}

@Test
public void test5() {
    List<Employee> employees = Arrays.asList(
            new Employee("xx", 59, 666.33),
            new Employee("ss", 36, 62.33),
            new Employee("zz", 18, 6699.33),
            new Employee("gg", 65, 6689.33)
    );

    List<Employee> employeeList = empHandler(employees, (employee) -> employee.getAge() >= 35);
    employeeList.forEach(System.out::println);
}

public List<Employee> empHandler(List<Employee> employees,Predicate<Employee> predicate) {
    List<Employee> employees1 = new ArrayList<>();
    for (Employee e : employees
    ) {
        if (predicate.test(e)) {
            employees1.add(e);
        }
    }
    return employees1;
}
```

## 方法引用于构造器引用

### 方法引用概念

+ 若lambda重的内容已经实现了，我们可以使用"方法引用"

+ 方法引用是lambda另外一种表现形式

+ lambda体中方法的参数列表和返回值要和函数式接口中的方法的参数列表和返回值相同

+ 主要有三种语法格式

  + 对象::实例方法名

    ```java
    @Test
    public void test1() {
    
        Consumer<String> consumer1 = (x) -> System.out.println(x);
    
        //对象::实例方法名
        PrintStream ps = System.out;
        Consumer<String> consumer2 = ps::println;
    
        //使用时，当前方法参数列表和返回值需要一致
        Consumer<String> consumer3 = System.out::println;
    
    }
    ```

  + 类::静态方法名

    ```java
    @Test
    public void test3() {
        //类::静态方法名
        Comparator<Integer> comparator = Integer::compareTo;
    
    }
    ```

  + 类::实例方法名

    ``` java
    @Test
    public void test4() {
        //类::实例方法名   前提：第一个参数x,在方法中是调用者，第二个参数y是方法中的参数
        BiPredicate<String,String> biPredicate1 = (x, y) -> x.equals(y);
    
        BiPredicate<String,String> biPredicate2 = String::equals;
    }
    ```

    ### 构造器引用

    + 构造引用

      ```java
      @Test
      public void test5() {
          //构造引用
          Supplier<Employee> supplier1 = () -> new Employee();
          //使用的无参数构造器
          Supplier<Employee> supplier2 = Employee::new;
      
          Function<Integer,Employee> function2 = (x) -> new Employee(x);
          Function<Integer,Employee> function1 = Employee::new;
      
          Employee apply = function1.apply(34);
          System.out.println(apply.getAge());
      
          BiFunction<String, Integer, Employee> biFunction1 = (x, y) -> new Employee(x, y);
          BiFunction<String, Integer, Employee> biFunction2 = Employee::new;
          Employee xxx = biFunction2.apply("xxx", 45);
          System.out.println(xxx);
      }
      ```

    + 数组引用

      ```java
      @Test
      public void test6() {
          //数组引用
          Function<Integer,String[]> function1 = (x) -> new String[x];
          Function<Integer,String[]> function2 = String[]::new;
          String[] apply = function2.apply(20);
          System.out.println(apply.length);
      }
      ```

      ## Stream API

      ### Stream 概念

      + 流(Stream) 是数据渠道，用于操作数据源（集合，数组等）生成的元素序列

      + 集合讲的是数据，流讲的是运算

      + Stream本身不会存储任何元素

      + Stream不会改变原有数据源

      + Stream的操作是延迟执行的，这意味着他们会等到需要结果的时候才执行

      + 步骤：创建 -> 处理 -> 结果 

        ### 创建

        ```java
        /**
         * 创建Stream
         */
        @Test
        public void test1() {
            //可以通过Collection 系列集合提供的stream() (串行流) parallelStream() (并行流)
            List<String> list = new ArrayList<>();
            Stream<String> stream = list.stream();
        
            //通过Arrays的stream() 方法获取流
            Employee[] employees = new Employee[10];
            Stream<Employee> stream1 = Arrays.stream(employees);
        
            //通过Stream 类的of()方法获取
            Stream<String> stream2 = Stream.of("xx", "bb");
        
            //创建无限流
            //迭代
            Stream<Integer> stream3 = Stream.iterate(2, (x) -> x + 2);
            stream3
                    //限制为20个
                    .limit(20)
                    .forEach(System.out::println);
        
            //生成
            Stream<Double> stream4 = Stream.generate(Math::random);
            stream4
                    .limit(10)
                    .forEach(System.out::println);
        }
        ```

        ### 中间操作


