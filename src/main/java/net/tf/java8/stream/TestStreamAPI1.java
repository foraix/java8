package net.tf.java8.stream;

import net.tf.java8.lambda.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author hy
 * @version 1.00
 * @time 2019/3/16 10:06
 * @desc 流的基本操作
 */
public class TestStreamAPI1 {

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
}
