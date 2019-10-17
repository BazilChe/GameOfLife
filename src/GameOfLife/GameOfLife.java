package GameOfLife;

import GameOfLife.GUI.*;

import java.util.LinkedList;

public class GameOfLife {
    public static void main(String ... args) {
        //list is made for communication between field and mouse listener
        LinkedList<Coordinate> cellCoordinatesToSwitch = new LinkedList<Coordinate>();

        //the field contains a set of cells
        Field f = new Field(Inf.fieldWidth, Inf.fieldHeight, cellCoordinatesToSwitch);

        //the component on which cells are showed
        //gets cell coordinates in order to transfer it to the mouse listener of the game label
        //also contain it itself to be able to draw the cell right after it has added by user on the screen
        //  and not to wait field to complete an updated
        GamePanel gp = new GamePanel(Inf.pxSize, f.getWidth(), f.getHeight(), cellCoordinatesToSwitch);

        //main form adjusts to game panel's size
        MainForm mf = new MainForm(gp);

        //prepare the game to start
        Game game = new Game(f, gp);

        //create main form
        mf.create();

        //start the game (starts field update and draw clock
        game.start();
    }
}
