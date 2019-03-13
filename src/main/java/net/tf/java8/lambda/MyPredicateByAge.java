package net.tf.java8.lambda;

/**
 * @author hy
 * @version 1.00
 * @time 2019/3/13 16:14
 * @desc
 */
public class MyPredicateByAge implements MyPredicate<Employee> {

    @Override
    public boolean test(Employee employee) {
        return employee.getAge() > 35;
    }
}
