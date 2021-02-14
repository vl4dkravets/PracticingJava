package example2;

class ClockThread extends Thread {

    private StartStopClock clock;
    private volatile boolean isRunning = true;

    public ClockThread(StartStopClock clock) {
        this.clock = clock;
    }

    public void stopClock() {
        isRunning = false;
    }

    @Override
    public void run() {
        // Every half a second, you call setTime(), so graphical form that holds time is updates
        // & it'll represent the up-to-date time
        while (isRunning) {
            clock.setTime();
            try {
                Thread.sleep(500);
            } catch (Exception e) {
            }
        }
    }
}
