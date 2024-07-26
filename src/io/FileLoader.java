package io;

import exceptions.MazeMalformedException;
import exceptions.MazeSizeMissmatchException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileLoader implements FileInterface {
    int y;
    int x;

    /**
     * Loads a maze from a specified file and returns it as a 2D char array.
     *
     * @param fileName The name of the file containing the maze data.
     * @return A 2D char array representing the loaded maze.
     * @throws MazeMalformedException    If the maze file is malformed or has invalid dimensions.
     * @throws IllegalArgumentException If the file name is null or empty.
     * @throws FileNotFoundException    If the specified maze file does not exist.
     * @throws MazeSizeMissmatchException If the loaded maze dimensions do not match the specified size.
     */
    @Override
    public char[][] load(String fileName) throws MazeMalformedException, IllegalArgumentException, FileNotFoundException, MazeSizeMissmatchException {
        ArrayList<char[]> columnList = new ArrayList<>();
        int numRows = 0;
        int numCols = 0;

        try (BufferedReader r = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean firstLineSkipped = false;

            while ((line = r.readLine()) != null) {
                if (!firstLineSkipped) {
                    if (line.length() >= 2) {
                        // Parse the dimensions from the first line
                        try {
                            y = Integer.parseInt(line.split(" ")[0]);
                            x = Integer.parseInt(line.split(" ")[1]);
                        } catch (NumberFormatException e) {
                            throw new MazeMalformedException("Invalid maze dimensions");
                        }
                    } else {
                        throw new MazeMalformedException("Invalid maze dimensions");
                    }
                    firstLineSkipped = true;
                    continue; // Skip the first line
                }
                char[] row = line.toCharArray();
                if (row.length != x) {
                    throw new MazeSizeMissmatchException("Maze row size does not match the specified dimensions");
                }
                columnList.add(row);
                numRows++;
            }
        } catch (IOException e) {
            throw new FileNotFoundException("Maze file not found: " + fileName);
        }

        if (columnList.isEmpty()) {
            throw new MazeMalformedException("No maze data found");
        }

        numCols = columnList.get(0).length;

        if (numRows != y || numCols != x) {
            throw new MazeSizeMissmatchException("Maze dimensions do not match the specified size");
        }

        char[][] result = new char[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            result[i] = columnList.get(i);
        }

        return result;
    }
}
