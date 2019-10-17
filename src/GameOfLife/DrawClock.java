package GameOfLife;

import GameOfLife.GUI.GamePanel;

public class DrawClock extends Clock {
    private GamePanel panel;
    private Field field;

    DrawClock(GamePanel panel, Field field) {
        this.panel = panel;
        this.field = field;
    }
    @Override
    public void makeTick() {
        try {
            this.panel.draw(field);
            Thread.sleep(Inf.drawPeriod);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
