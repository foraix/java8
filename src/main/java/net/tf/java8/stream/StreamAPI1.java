package net.tf.java8.stream;

import net.tf.java8.stream.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * @author hy
 * @version 1.00
 * @time 2019/3/19 19:14
 * @desc 终止操作
 */
public class StreamAPI1 {

    List<Employee> employees = Arrays.asList(
            new Employee("xx", 59, 666.33, Status.BUSY),
            new Employee("ss", 36, 62.33, Status.FREE),
            new Employee("zz", 18, 6699.33, Status.VOCATION),
            new Employee("gg", 65, 6689.33, Status.BUSY)
    );

    /**
     *
     */
    @Test
    public void test1() {
        //allMatch 检查是否匹配所有元素,查看是不是所有的员工都处于忙碌状态

        boolean b = employees.stream()
                .allMatch((e) -> e.getStatus().equals(Status.BUSY));
        System.out.println("是不是所有的员工都处于忙碌状态" + b);

        //anyMatch 至少一个匹配
        //noneMatch 没有匹配
        //findFirst 查找所有元素
        //findAny 返回当前元素
        //count 返回元素总个数
        //max 最大值
        //min 最小值

        Optional<Employee> first = employees.stream().min(Comparator.comparingDouble(Employee::getSalary));
        //OPtional 的出现是为了最大限度的防治空指针异常
        //orElse 如果为空的话使用谁代替
        Employee xx = first.orElse(new Employee("xx", 59, 666.33, Status.BUSY));

        System.out.println(first);

        //parallelStream多个线程同时完成终止操作

        //寻找一个随机的空闲程序员
        Optional<Employee> any = employees.parallelStream()
                .filter(e -> e.getStatus().equals(Status.FREE))
                .findAny();
        Employee employee = any.get();
        System.out.println(employee);

        //拿到工资的最小值
        Optional<Double> max = employees.stream()
                .map(Employee::getSalary)
                .min(Double::compare);
        System.out.println("工资最少的是" + max);

    }
}
