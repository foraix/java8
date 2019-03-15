package net.tf.java8.lambda;

/**
 * @author hy
 * @version 1.00
 * @time 2019/3/13 16:14
 * @desc FunctionalInterface 函数式接口，只有一个抽象方法的接口
 */
@FunctionalInterface
public interface
MyPredicate<T> {
    boolean test(T t);
}
