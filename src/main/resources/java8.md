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

### 其他

> @FunctionalInterface 函数式接口，只有一个抽象方法的接口