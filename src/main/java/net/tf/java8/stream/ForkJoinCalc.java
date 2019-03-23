package net.tf.java8.stream;

import java.util.concurrent.RecursiveTask;

/**
 * @author hy
 * @version 1.00
 * @time 2019/3/22 11:23
 * @desc Fork/Join框架
 */
public class ForkJoinCalc extends RecursiveTask<Long> {

    private long start;
    private long end;

    /**
     * 拆分临界值
     */
    private static final long THRESHOLD = 10000;

    public ForkJoinCalc(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = end - start;

        long sum = 0;

        //如果到临界值就不再拆分
        if (length <= THRESHOLD) {
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            //此时未到临界值
            long middle = (start + end) / 2;

            //拆分子任务，同时压入线程队列
            ForkJoinCalc left = new ForkJoinCalc(start, middle);
            left.fork();

            ForkJoinCalc right = new ForkJoinCalc(middle + 1, end);
            right.fork();
            return left.join() + right.join();
        }
    }
}
