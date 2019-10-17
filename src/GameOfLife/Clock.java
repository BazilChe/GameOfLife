package GameOfLife;

public abstract class Clock implements Runnable {
    public abstract void makeTick();
    @Override
    public void run() {
        while (true) makeTick();
    }
}
