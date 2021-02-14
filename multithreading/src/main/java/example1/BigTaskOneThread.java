package example1;

class BigTaskOneThread {

    // Simulates running multiple computation via multiple objects
    // however, all will run on a single thread
    public Long startTask() {
        Long summa = 0L;
        for (int i = 0; i < TaskTesting.STR_COUNT; i++) {
            TaskTesting t = new TaskTesting();
            summa += t.process();
        }
        return summa;
    }

}
