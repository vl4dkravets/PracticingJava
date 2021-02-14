package example3;

public class RectangleThread extends Thread {

    private final MovingObject mo;
    private volatile boolean isRunning = true;
    RectangleThread(MovingObject mo) {
        this.mo = mo;
    }

    @Override
    public void run() {
        while (isRunning) {
            mo.updateRectanglePos();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } {
            }
        }
    }

    public void stopMoving() {
        isRunning = false;
    }
    public void startMoving() {
        isRunning = true;
    }
}
