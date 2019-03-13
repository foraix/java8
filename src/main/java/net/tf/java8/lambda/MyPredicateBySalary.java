package net.tf.java8.lambda;

/**
 * @author hy
 * @version 1.00
 * @time 2019/3/13 16:24
 * @desc
 */
public class MyPredicateBySalary implements MyPredicate<Employee> {

    @Override
    public boolean test(Employee employee) {
        return employee.getSalary() >= 2500.00;
    }
}
