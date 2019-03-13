package net.tf.java8.lambda;

import lombok.*;

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
}
