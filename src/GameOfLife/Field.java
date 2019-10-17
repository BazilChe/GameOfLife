package GameOfLife;

import GameOfLife.GUI.Coordinate;

import java.util.LinkedList;
import java.util.Random;

public class Field {
    private final LinkedList<Coordinate> cellCoordinatesToSwitch;
    private Cell field[][];
    private Cell nextField[][];
    int width, height;

    Field(int width, int height, LinkedList<Coordinate> cellCoordinatesToSwitch) {
        this.width = width;
        this.height = height;
        this.cellCoordinatesToSwitch = cellCoordinatesToSwitch;
        this.field = new Cell[width][height];
        this.nextField = new Cell[width][height];

        Random random = new Random();

        for (int x = 0; x < width; x++)
            for(int y = 0; y < height; y++) {
                field[x][y] = new Cell();
                if (random.nextInt(Inf.probability) != 0)
                    field[x][y].switchCell();
                nextField[x][y] = new Cell();
            }
    }
    public synchronized void update() {
        switchRequestedCells();
        makeNewGeneration();
    }

    int getWidth() {
        return this.width;
    }
    int getHeight() {
        return this.height;
    }
    private void switchRequestedCells() {
        Coordinate currentCoordinate;
        int x = 0, y = 0;
        while (!cellCoordinatesToSwitch.isEmpty()) {
            currentCoordinate = cellCoordinatesToSwitch.removeLast();
            if ((x = currentCoordinate.getX()) < this.width &&
                (y = currentCoordinate.getY()) < this.height)
                field[x][y].switchCell();
        }
    }
    public synchronized Cell getCell(int x, int y) {
        return field[x][y];
    }

    /*
                ~ ~ ~ * ~ ~ ~ THE PART OF THE ALGORITHM ~ ~ ~ * ~ ~ ~
     */

    private enum Location {
        LEFTTOP,
        TOP,
        RIGHTTOP,
        RIGHT,
        RIGHTBOTTOM,
        BOTTOM,
        LEFTBOTTOM,
        LEFT,
        CENTER
    }
    private Location findCellLocation(int x, int y) {
        int maxX = this.width - 1, maxY = this.height - 1;
        Location location = null;

        if (x != maxX && y != maxY && x != 0 && y != 0) location = Location.CENTER;
        else if (x == 0) {
            if (y == 0) location = Location.LEFTTOP;
            else if (y == maxY) location = Location.LEFTBOTTOM;
            else location = Location.LEFT;
        }
        else if (x == maxX) {
            if (y == 0) location = Location.RIGHTTOP;
            else if (y == maxY) location = Location.RIGHTBOTTOM;
            else location = Location.RIGHT;
        }
        else if (y == 0) location = Location.TOP;
        else location = Location.BOTTOM;

        return location;
    }
    private int countNeighbors(int x, int y) {
        Location location = this.findCellLocation(x, y);
        int maxX = this.width - 1, maxY = this.height - 1;
        int count = 0;

        switch (location) {
            case CENTER:
                if (this.field[x + 1][y - 1].isCellAlive()) count++;
                if (this.field[x + 1][y].isCellAlive()) count++;
                if (this.field[x + 1][y + 1].isCellAlive()) count++;
                if (this.field[x][y + 1].isCellAlive()) count++;
                if (this.field[x - 1][y - 1].isCellAlive()) count++;
                if (this.field[x - 1][y].isCellAlive()) count++;
                if (this.field[x - 1][y + 1].isCellAlive()) count++;
                if (this.field[x][y - 1].isCellAlive()) count++;
                return count;
            case TOP:
                if (this.field[x + 1][maxY].isCellAlive()) count++;
                if (this.field[x + 1][y].isCellAlive()) count++;
                if (this.field[x + 1][y + 1].isCellAlive()) count++;
                if (this.field[x][y + 1].isCellAlive()) count++;
                if (this.field[x - 1][maxY].isCellAlive()) count++;
                if (this.field[x - 1][y].isCellAlive()) count++;
                if (this.field[x - 1][y + 1].isCellAlive()) count++;
                if (this.field[x][maxY].isCellAlive()) count++;
                return count;
            case BOTTOM:
                if (this.field[x + 1][y - 1].isCellAlive()) count++;
                if (this.field[x + 1][y].isCellAlive()) count++;
                if (this.field[x + 1][0].isCellAlive()) count++;
                if (this.field[x][0].isCellAlive()) count++;
                if (this.field[x - 1][y - 1].isCellAlive()) count++;
                if (this.field[x - 1][y].isCellAlive()) count++;
                if (this.field[x - 1][0].isCellAlive()) count++;
                if (this.field[x][y - 1].isCellAlive()) count++;
                return count;
            case LEFT:
                if (this.field[x + 1][y - 1].isCellAlive()) count++;
                if (this.field[x + 1][y].isCellAlive()) count++;
                if (this.field[x + 1][y + 1].isCellAlive()) count++;
                if (this.field[x][y + 1].isCellAlive()) count++;
                if (this.field[maxX][y - 1].isCellAlive()) count++;
                if (this.field[maxX][y].isCellAlive()) count++;
                if (this.field[maxX][y + 1].isCellAlive()) count++;
                if (this.field[x][y - 1].isCellAlive()) count++;
                return count;
            case RIGHT:
                if (this.field[0][y - 1].isCellAlive()) count++;
                if (this.field[0][y].isCellAlive()) count++;
                if (this.field[0][y + 1].isCellAlive()) count++;
                if (this.field[x][y + 1].isCellAlive()) count++;
                if (this.field[x - 1][y - 1].isCellAlive()) count++;
                if (this.field[x - 1][y].isCellAlive()) count++;
                if (this.field[x - 1][y + 1].isCellAlive()) count++;
                if (this.field[x][y - 1].isCellAlive()) count++;
                return count;
            case LEFTTOP:
                if (this.field[x + 1][maxY].isCellAlive()) count++;
                if (this.field[x + 1][y].isCellAlive()) count++;
                if (this.field[x + 1][y + 1].isCellAlive()) count++;
                if (this.field[x][y + 1].isCellAlive()) count++;
                if (this.field[maxX][maxY].isCellAlive()) count++;
                if (this.field[maxX][y].isCellAlive()) count++;
                if (this.field[maxX][y + 1].isCellAlive()) count++;
                if (this.field[x][maxY].isCellAlive()) count++;
                return count;
            case RIGHTTOP:
                if (this.field[0][maxY].isCellAlive()) count++;
                if (this.field[0][y].isCellAlive()) count++;
                if (this.field[0][y + 1].isCellAlive()) count++;
                if (this.field[x][y + 1].isCellAlive()) count++;
                if (this.field[x - 1][maxY].isCellAlive()) count++;
                if (this.field[x - 1][y].isCellAlive()) count++;
                if (this.field[x - 1][y + 1].isCellAlive()) count++;
                if (this.field[x][maxY].isCellAlive()) count++;
                return count;
            case LEFTBOTTOM:
                if (this.field[x + 1][y - 1].isCellAlive()) count++;
                if (this.field[x + 1][y].isCellAlive()) count++;
                if (this.field[x + 1][0].isCellAlive()) count++;
                if (this.field[x][0].isCellAlive()) count++;
                if (this.field[maxX][y - 1].isCellAlive()) count++;
                if (this.field[maxX][y].isCellAlive()) count++;
                if (this.field[maxX][0].isCellAlive()) count++;
                if (this.field[x][y - 1].isCellAlive()) count++;
                return count;
            case RIGHTBOTTOM:
                if (this.field[0][y - 1].isCellAlive()) count++;
                if (this.field[0][y].isCellAlive()) count++;
                if (this.field[0][0].isCellAlive()) count++;
                if (this.field[x][0].isCellAlive()) count++;
                if (this.field[x - 1][y - 1].isCellAlive()) count++;
                if (this.field[x - 1][y].isCellAlive()) count++;
                if (this.field[x - 1][0].isCellAlive()) count++;
                if (this.field[x][y - 1].isCellAlive()) count++;
                return count;
        }

        return count;
    }
    private boolean willCellLive(int x, int y, int neighbors) {
        if (this.field[x][y].isCellAlive()) {
            if (neighbors > 3 || neighbors < 2) return false;
            else return true;
        } else {
            if (neighbors == 3) return true;
            else return false;
        }
    }
    private void swapFields() {
        Cell[][] temp = this.field;
        this.field = nextField;
        this.nextField = temp;
    }
    private void makeNewGeneration() {
        for (int x = 0; x < this.width; x++){
            for (int y = 0; y < this.height; y++) {
                if (willCellLive(x, y, countNeighbors(x, y))) this.nextField[x][y].setCellLifeStatus(true);
                else this.nextField[x][y].setCellLifeStatus(false);
            }
        }

        swapFields();
    }
}
