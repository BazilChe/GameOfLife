package GameOfLife;

import GameOfLife.GUI.GamePanel;

public class Game {
    private Field field;
    private GamePanel gp;

    public Game(Field field, GamePanel gp) {
        this.field = field;
        this.gp = gp;
    }
    public void start() {
        new Thread(new FieldClock(field)).start();
        new Thread(new DrawClock(gp, field)).start();
    }
}
