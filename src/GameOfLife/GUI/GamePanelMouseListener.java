package GameOfLife.GUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

public class GamePanelMouseListener implements MouseListener {
    private final LinkedList<Coordinate> coordinates;
    private final int pxSize;

    GamePanelMouseListener(LinkedList<Coordinate> coordinates, int pxSize) {
        this.pxSize = pxSize;
        this.coordinates = coordinates;
    }
    
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        this.coordinates.add(new Coordinate(mouseEvent.getX() / this.pxSize,
                                            mouseEvent.getY() / this.pxSize));
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {}

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {}

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {}

    @Override
    public void mouseExited(MouseEvent mouseEvent) {}
}
