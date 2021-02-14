package example1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class BigTaskManyThreads {

    //  Simulates running several computation however on multiple threads
    public Long startTask() {
        int ap = Runtime.getRuntime().availableProcessors();
        ExecutorService es = Executors.newFixedThreadPool(ap);

        Long sum = 0L;
        try {
            List<MyCallable> threads = new ArrayList<MyCallable>();
            for (int i = 0; i < TaskTesting.STR_COUNT; i++) {
                threads.add(new MyCallable());
            }

            // Automatically invokes method call inb each instance of MyCallable
            List<Future<Long>> result = es.invokeAll(threads);

            // Future instance will hold the result of call() method from each MyCallable instance
            for (Future<Long> f : result) {
                sum += f.get();
            }
            es.shutdown();

        } catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace(System.out);
        }
        return sum;
    }
}
