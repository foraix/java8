package net.tf.java8.lambda;

/**
 * @author hy
 * @version 1.00
 * @time 2019/3/15 12:59
 * @desc 函数式接口的使用
 */
@FunctionalInterface
public interface MyFun<T> {

    T getValue(T value);
}
