package GameOfLife;

public class Cell {
    private boolean isAlive;

    public boolean isCellAlive() {
        return this.isAlive;
    }
    public void switchCell() {
        this.isAlive = !isAlive;
    }
    public void setCellLifeStatus(boolean status) {
        this.isAlive = status;
    }
}
