package io;

import exceptions.MazeMalformedException;
import exceptions.MazeSizeMissmatchException;

import java.io.FileNotFoundException;

public class GameLauncher {

    public static Boolean isGUI = false;
    public static boolean winner = false;
    public static boolean quit = false;

    /**
     * The runGame function is the main function that runs the game.
     * It takes in a file path as an argument and creates a Move object with it.
     * Then, if GUI mode is enabled, it will create and show the GUI for this game.
     * If not, then it will print out the map to terminal and get user input for moves until they win or quit.

     *
     * @param String filePath Pass the file path of the maze to be used in the game
     *
     *
     */
    public static void runGame(String filePath) throws FileNotFoundException, MazeMalformedException, MazeSizeMissmatchException {
        Move move = new Move(filePath);
        GUI myGui = new GUI(filePath);

        while (!winner && !quit) {
            if (isGUI) {
                 myGui.createAndShowGui();
                winner = true; // GUI has finished, set winner to true
            } else {

                System.out.println(move.generateMapString()); // prints game to terminal
                move.getMove(); // outputs enter move to terminal and scans for next move
            }
        }
        System.exit(0); //makes the gui exit the loop once finished
    }
}
