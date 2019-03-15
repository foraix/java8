package net.tf.java8.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author hy
 * @version 1.00
 * @time 2019/3/15 13:18
 * @desc 简单的练习
 */
public class TestLambda1 {
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
}
