package GameOfLife;

public class FieldClock extends Clock{
    private Field field;

    FieldClock(Field field) {
        this.field = field;
    }

    @Override
    public void makeTick() {
        if (Inf.isRunning) {
            try {
                Thread.sleep(Inf.maxPeriod + Inf.minPeriod - Inf.currentPeriod);
                this.field.update();
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
