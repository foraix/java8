package net.tf.java8.lambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * @author hy
 * @version 1.00
 * @time 2019/3/15 12:17
 * @desc lambda的基本使用
 */
public class TestLambda {
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
}
