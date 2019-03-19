package net.tf.java8.stream;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author hy
 * @version 1.00
 * @time 2019/3/19 19:54
 * @desc 归约
 */
public class TestStreamAPI2 {

    private List<Employee> employees = Arrays.asList(
            new Employee("xx", 59, 666.33, Status.BUSY),
            new Employee("ss", 36, 62.33, Status.FREE),
            new Employee("zz", 18, 6699.33, Status.VOCATION),
            new Employee("gg", 65, 6689.33, Status.BUSY)
    );

    @Test
    public void test1() {

        //通过reduce计算该list的和
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer reduce = list.stream()
                //0是起始值，在第一步中先是0 + 1
                .reduce(0, (x, y) -> x + y);
        System.out.println(reduce);

    }

    @Test
    public void test2() {
        //计算工资总和
        Optional<Double> reduce1 = employees.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);
        Double aDouble = reduce1.orElse(0.00);
        System.out.println(reduce1.get());
    }

    @Test
    public void test3() {
        //将薪水收集到一个list中去
        List<Double> collect = employees.stream()
                .map(Employee::getSalary)
                .collect(Collectors.toList());
        System.out.println(collect);
        collect.forEach(System.out::println);

        System.out.println("---------------------------------------------------------------");

        //收集到指定的集合中
        HashSet<Integer> collect1 = employees.stream()
                .map(Employee::getAge)
                .collect(Collectors.toCollection(HashSet::new));
        collect1.forEach(System.out::println);

        System.out.println("---------------------------------------------------------------");

        //按照工资获取均值
        Double aDouble = employees.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(aDouble);

        System.out.println("---------------------------------------------------------------");

        //获取最高薪资的员工信息
        Optional<Employee> collect2 = employees.stream().max(Comparator.comparingDouble(Employee::getSalary));
        System.out.println(collect2.get());

        System.out.println("---------------------------------------------------------------");

        //获取年龄最大的员工信息
        Optional<Employee> max = employees.stream().max(Comparator.comparingInt(Employee::getAge));
        System.out.println(max.get());

        System.out.println("---------------------------------------------------------------");

        //获取最小工资
        Optional<Integer> min = employees.stream()
                .map(Employee::getAge)
                .min(Integer::compareTo);
        System.out.println(min.get());

        //按照状态分组统计员工信息
        Map<Status, List<Employee>> statusListMap = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));

        for (Status s : statusListMap.keySet()
        ) {
            System.out.println("==============================================");
            List<Employee> employeeList = statusListMap.get(s);
            employeeList.forEach(System.out::println);
        }

        System.out.println("---------------------------------------------------------------");

        //多级分组
        Map<Double, Map<Object, List<Employee>>> collect3 = employees.stream()
                .collect(Collectors.groupingBy(Employee::getSalary, Collectors.groupingBy((employees) -> {
                    if (employees.getAge() >= 18 && employees.getAge() < 65) {
                        return "中";
                    } else if (employees.getAge() <= 18) {
                        return "小";
                    } else {
                        return "老";
                    }
                })));
        System.out.println(collect3);

    }


}
