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

