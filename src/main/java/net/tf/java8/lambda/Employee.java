package net.tf.java8.lambda;

import lombok.*;

import java.util.Objects;

/**
 * @author hy
 * @version 1.00
 * @time 2019/3/13 16:05
 * @desc 员工pojo
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private String name;
    private int age;
    private double salary;

    public Employee(int age) {
        this.age = age;
    }

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void test() {
        System.out.println(name + "是我的爱呀！！！！！！！！");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Employee)) {
            return false;
        }
        Employee employee = (Employee) o;
        return getAge() == employee.getAge() &&
                Double.compare(employee.getSalary(), getSalary()) == 0 &&
                Objects.equals(getName(), employee.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAge(), getSalary());
    }
}
