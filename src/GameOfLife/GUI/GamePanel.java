package GameOfLife.GUI;

import GameOfLife.Field;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class GamePanel extends JLabel {
    private int pxSize, fieldWidth, fieldHeight;
    private boolean isToPaint;
    private LinkedList<Coordinate> cellsToSwitch;
    int count = 0;
    private Field field;

    final private Color aliveCellColor = Color.cyan;
    final private Color deadCellColor = Color.darkGray;

    public GamePanel(int pxSize, int fieldWidth, int fieldHeight, LinkedList<Coordinate> cellsToSwitch) {
        this.cellsToSwitch = cellsToSwitch;
        this.pxSize = pxSize;
        this.fieldWidth = fieldWidth;
        this.fieldHeight =fieldHeight;

        this.addMouseListener(new GamePanelMouseListener(cellsToSwitch, this.pxSize));
    }
    public void draw(Field f) {
        this.isToPaint = true;
        this.field = f;
        super.repaint();
    }
    public int getPxSize() {
        return this.pxSize;
    }
    public int getWidthSize() {
        return this.fieldWidth;
    }
    public int getHeightSize() {
        return this.fieldHeight;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);

        if (isToPaint) {
            for (int x = 0; x < this.fieldWidth; x++)
                for (int y = 0; y < this.fieldHeight; y++) {
                    if (this.field.getCell(x, y).isCellAlive())
                        g2d.setColor(aliveCellColor);
                    else
                        g2d.setColor(deadCellColor);

                    g2d.fillRect(x*pxSize, y*pxSize, pxSize, pxSize);
                }

            for(Coordinate coord : cellsToSwitch) {
                if (field.getCell(coord.getX(), coord.getY()).isCellAlive())
                    g2d.setColor(this.deadCellColor);
                else
                    g2d.setColor(this.aliveCellColor);

                g2d.fillRect(coord.getX()*pxSize, coord.getY()*pxSize, pxSize, pxSize);
            }
            
            this.isToPaint = false;
        }
    }
}
