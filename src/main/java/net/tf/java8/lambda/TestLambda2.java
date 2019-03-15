package net.tf.java8.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author hy
 * @version 1.00
 * @time 2019/3/15 13:53
 * @desc 四大核心函数式接口
 */
public class TestLambda2 {
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

}
