package io;

import exceptions.MazeMalformedException;
import exceptions.MazeSizeMissmatchException;

import java.io.FileNotFoundException;
import java.util.Objects;

public class Position {
    public int[] currentPosition; // Store the current position
    public char[][] map;

    /**
     * The Position function is a constructor that takes in the name of a file and
     * creates an instance of Position. It also initializes the mapReader, loads
     * the map from the file, and sets currentPosition to be equal to getStartingPosition().

     *
     * @param String fileName Load the maze from a file
     *
     * @return A position object
     *
     */
    public Position(String fileName) throws FileNotFoundException, MazeMalformedException, MazeSizeMissmatchException {
        FileLoader mapReader = new FileLoader(); // Initialize mapReader
        map = mapReader.load(fileName);
        currentPosition = getStartingPosition();

    }

    /**
     * The getStartingPosition function returns the starting position of the player.
     *
     *
     *
     * @return The starting position of the player
     *
     */
    public int[] getStartingPosition() {
        int[] result = new int[2];

        for (int col = 0; col < map.length; col++) {
            for (int row = 0; row < map[col].length; row++) {
                if (Objects.equals(String.valueOf(map[col][row]), "S")) {
                    result[0] = row;
                    result[1] = col;
                }
            }
        }

        return result;
    }

    /**
     * The getEndingPosition function returns the ending position of the maze.
     *
     *
     *
     * @return The position of the ending point (the e)
     *
     */
    public int[] getEndingPosition() {
        int[] result = new int[2];

        for (int col = 0; col < map.length; col++) {
            for (int row = 0; row < map[col].length; row++) {
                if (Objects.equals(String.valueOf(map[col][row]), "E")) {
                    result[0] = row;
                    result[1] = col;
                }
            }
        }

        return result;
    }

    /**
     * The getCurrentPosition function returns the current position of the player.
     *
     *
     *
     * @return The current position of the robot
     *
     */
    public int[] getCurrentPosition() {
        return currentPosition;
    }
}
