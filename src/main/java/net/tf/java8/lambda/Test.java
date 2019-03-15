package net.tf.java8.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author hy
 * @version 1.00
 * @time 2019/3/13 15:49
 * @desc lambda测试类
 */
public class Test {

    @org.junit.Test
    public void test1() {
        //匿名内部类写法
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };

        //lambda表达式
        Comparator<Integer> comparator1 = Integer::compare;
        Comparator<String> comparator2 = (x, y) -> {
            if (x.length() >= y.length()) {
                return 0;
            } else {
                return 1;
            }
        };
    }

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

        System.out.println("---------------------------------------------------");

        //按照薪水分类
        List<Employee> employees1 = filterEmployee(list, new MyPredicateBySalary());
        for (Employee e : employees1
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


    private static List<Employee> filterEmployeeA(List<Employee> employeeList, MyPredicate<Employee> me) {
        List<Employee> employeeList1 = new ArrayList<>();

        for (Employee e : employeeList
        ) {
            if (me.test(e)) {
                employeeList1.add(e);
            }
        }
        return employeeList1;
    }

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
}