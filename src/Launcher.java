import exceptions.MazeMalformedException;
import exceptions.MazeSizeMissmatchException;
import io.GUI;
import io.GameLauncher;
import io.Move;
import io.Position;

import java.io.FileNotFoundException;

public class Launcher {

    /**
     * The main function is the entry point of the program.
     * It takes in command line arguments and parses them to determine whether or not a GUI should be used,
     * and which text file should be used for maze generation.
     * The main function then creates a new thread that runs MazeSolver on the given text file, while also running runGame() on it's own thread.

     *
     * @param String[] args Pass command line arguments to the program
     *
     * @return Nothing
     *
     */
    public static void main(String[] args) throws MazeSizeMissmatchException, MazeMalformedException, FileNotFoundException {
        GameLauncher gameLauncher = new GameLauncher();

        boolean guiRequested = false;
        String textFile = "src/Maze001.txt"; //Default Maze
        guiRequested = true;
        for (String arg : args) {
            if (arg.contains("GUI")) {
                guiRequested = true; // selects gui
            }
            if (arg.contains(".txt")) {
                textFile = arg; // selects text file
            }

        }

        new Position(textFile);
        new Move(textFile);
        new GUI(textFile);

        if (guiRequested) {
            GameLauncher.isGUI = true;
        }

        //This is an auto solver which initialy tests to check if the maze is solvable
        String finalTextFile = textFile;
        Thread solverThread = new Thread(() -> {
            try {
                MazeSolver mazeSolver = new MazeSolver(finalTextFile);
                if (!mazeSolver.isMazeSolvable()) {
                    throw new RuntimeException("Unsolvable Maze");
                }
            } catch (FileNotFoundException | MazeMalformedException | MazeSizeMissmatchException e) {
                // Handle other exceptions here
                System.err.println("Error: " + e.getMessage());
            }
        });

        solverThread.start();

        GameLauncher.runGame(textFile); //runs the game if maze is solvable

    }
}