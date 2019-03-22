package net.tf.java8.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Stream;

/**
 * @author hy
 * @version 1.00
 * @time 2019/3/22 10:41
 * @desc
 */
public class TestStreamAPI3 {
    private List<Employee> employees = Arrays.asList(
            new Employee("xx", 59, 666.33, Status.BUSY),
            new Employee("ss", 36, 62.33, Status.FREE),
            new Employee("zz", 18, 6699.33, Status.VOCATION),
            new Employee("gg", 65, 6689.33, Status.BUSY)
    );

    @Test
    public void test1() {
        //每个数求平方
        Integer[] integers = new Integer[]{1, 2, 3, 4, 5};
        Arrays.stream(integers)
                .map(x -> x * x)
                .forEach(System.out::println);
    }

    @Test
    public void test2() {
        //计算个数
        Optional<Integer> reduce = employees.stream()
                .map(e -> 1)
                .reduce(Integer::sum);
        System.out.println(reduce.get());
    }

}
