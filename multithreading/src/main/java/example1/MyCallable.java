package example1;

import java.util.concurrent.Callable;

class MyCallable implements Callable<Long> {

    // Implementation of a method which will be automatically invoked
    @Override
    public Long call() throws Exception {
        TaskTesting t = new TaskTesting();
        return t.process();
    }
}
