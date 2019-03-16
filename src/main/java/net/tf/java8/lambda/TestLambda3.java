package net.tf.java8.lambda;

import org.junit.Test;
import org.omg.PortableInterceptor.INACTIVE;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.*;

/**
 * @author hy
 * @version 1.00
 * @time 2019/3/15 14:51
 * @desc
 */
public class TestLambda3 {
    @Test
    public void test1() {

        Consumer<String> consumer1 = (x) -> System.out.println(x);

        //对象::实例方法名
        PrintStream ps = System.out;
        Consumer<String> consumer2 = ps::println;

        //使用时，当前方法参数列表和返回值需要一致
        Consumer<String> consumer3 = System.out::println;

    }

    @Test
    public void test2() {

        //对象::实例方法名
        Employee employee = new Employee();
        Supplier<String> stringSupplier1 = () -> employee.getName();
        Supplier<String> stringSupplier2 = employee::getName;
        Supplier<Integer> integerSupplier = employee::getAge;

//        String str = employee::getName;
    }

    @Test
    public void test3() {
        //类::静态方法名
        Comparator<Integer> comparator = Integer::compareTo;

    }

    @Test
    public void test4() {
        //类::实例方法名   前提：第一个参数x,在方法中是调用者，第二个参数y是方法中的参数
        BiPredicate<String,String> biPredicate1 = (x, y) -> x.equals(y);

        BiPredicate<String,String> biPredicate2 = String::equals;
    }

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

    @Test
    public void test6() {
        //数组引用
        Function<Integer,String[]> function1 = (x) -> new String[x];
        Function<Integer,String[]> function2 = String[]::new;
        String[] apply = function2.apply(20);
        System.out.println(apply.length);
    }
}
