package net.tf.java8.stream;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.LongStream;

/**
 * @author hy
 * @version 1.00
 * @time 2019/3/22 11:33
 * @desc
 */
public class TestForkJoinCalc {
    @Test
    public void test1() {
        Instant start = Instant.now();

        ForkJoinCalc forkJoinCalc = new ForkJoinCalc(0L,10000000000L);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Long aLong = forkJoinPool.invoke(forkJoinCalc);
        System.out.println(aLong);

        Instant end = Instant.now();

        //计算时间
        System.out.println(Duration.between(start,end).toMillis());
    }

    @Test
    public void test2() {
        Instant start = Instant.now();
        ForkJoinCalc forkJoinCalc = new ForkJoinCalc(0L,10000000000L);
        long sum = 0L;
        for (int i = 0; i <= 100000000L; i++) {
            sum += i;
        }
        System.out.println(sum);

        Instant end = Instant.now();
        //计算时间
        System.out.println(Duration.between(start,end).toMillis());
    }

    @Test
    public void test3() {

        Instant start = Instant.now();
        //测试Java8并行流
        long reduce = LongStream.rangeClosed(0, 1000000000L)
                .parallel()
                .reduce(0, Long::sum);
        System.out.println(reduce);

        Instant end = Instant.now();
        System.out.println(Duration.between(start,end).toMillis());
    }
}
