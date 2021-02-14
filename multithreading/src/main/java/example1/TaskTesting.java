package example1;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TaskTesting {

    public static final int STR_COUNT = 100;
    public static final int TASK_COUNT = 10000;

    public static void main(String[] args) {

        /**
         * The example simulates running computations
         *  using 100 objects on a single thread vs. using 100 objects on multiple threads
         *  Note: (# of threads depends on a system)
         */
        {
            BigTaskOneThread bt = new BigTaskOneThread();
            long d1 = System.currentTimeMillis();
            Long result = bt.startTask();
            long d2 = System.currentTimeMillis();
            System.out.println("BigTaskOneThread(): " + result + ", Time 1:" + (d2 - d1));
        }
        {
            BigTaskManyThreads bt = new BigTaskManyThreads();
            long d1 = System.currentTimeMillis();
            Long result = bt.startTask();
            long d2 = System.currentTimeMillis();
            System.out.println("BigTaskManyThreads(): " + result + ", Time 2:" + (d2 - d1));
        }
    }

    // Simulates resource-heavy task
    public Long process() {
        Long sum = 0L;

        SecureRandom random = new SecureRandom();
        for (int i = 0; i < TaskTesting.TASK_COUNT; i++) {
            String g = new BigInteger(500, random).toString(32);
            for (char c : g.toCharArray()) {
                sum += c;
            }
        }
        return sum;
    }
}

