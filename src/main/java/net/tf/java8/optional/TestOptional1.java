package net.tf.java8.optional;

import net.tf.java8.stream.Employee;
import org.junit.Test;

import java.util.Optional;

/**
 * @author hy
 * @version 1.00
 * @time 2019/3/23 14:37
 * @desc Optional 容器的常用方法
 */
public class TestOptional1 {

    @Test
    public void test1() {
        //Optional.of创建一个实例，传null依旧会报空指针，但是便于锁定空指针异常位置
        Optional<Employee> employee = Optional.of(new Employee());
        System.out.println(employee.get());

        //构建一个空的Optional 不能调用get方法
        Optional<Employee> employee1 = Optional.empty();
        Optional<Employee> employee2 = Optional.ofNullable(null);

    }

    @Test
    public void test2() {
        Optional<Employee> o = Optional.ofNullable(null);
        //isPresent 有值就执行，空不执行
        if (o.isPresent()) {
            o.get();
        }

        //如果容器为空使用默认值
        Employee employee = o.orElse(new Employee());
        System.out.println(employee);

        Optional<Employee> o1 = Optional.ofNullable(new Employee());
        if (o1.isPresent()) {
            System.out.println(o1.get());
        }
    }
}
