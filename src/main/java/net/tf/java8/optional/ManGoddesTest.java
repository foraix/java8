package net.tf.java8.optional;

import java.util.Optional;

/**
 * @author hy
 * @version 1.00
 * @time 2019/3/23 15:28
 * @desc
 */
public class ManGoddesTest {

    public static void main(String[] args) {
        Optional<Goddess> goddess = Optional.ofNullable(new Goddess("xxx"));
        String name = Man.getGodnessName(Optional.ofNullable(null));
        System.out.println(name);
    }
}
