
package io;

import exceptions.MazeMalformedException;
import exceptions.MazeSizeMissmatchException;

import java.io.FileNotFoundException;
import java.util.Scanner;


public class Move {
    public int[] currentPosition;
    public String mapMaze;
    public char[][] map; //getting the map from the pos class so that getMap is only used once


    /**
     * The Move function takes in a direction and moves the player to that position.
     *
     *
     * @param fileName Read the maze file
     *
     * @return The new position of the player
     *
     */
    public Move(String fileName) throws FileNotFoundException, MazeMalformedException, MazeSizeMissmatchException {
        Position position = new Position(fileName);
        currentPosition = position.getCurrentPosition();
        map = position.map; // Initialize the map array
        mapMaze = generateMapString();
    }

    /**
     * The getMove function is used to get the move from the user.
     * It will be used when GUI is not selected.
     *
     *
     * @return The move that the player has chosen
     *
     */
    public void getMove(){
        // will be used to print game when gui is not selected
        GameLauncher gameLauncher = new GameLauncher();
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter move: ");
        String set_move = myObj.nextLine();
        // quit game
        if (set_move.equals("q")){
            gameLauncher.quit = true;
        }
        else {
            setMove(set_move);
        }
    }


    /**
     * The getMapData function returns the map data.
     *
     *
     *
     * @return The map
     *
     */
    public char[][] getMapData() {
        return map;
    }

    /**
     * The generateMapString function takes the map array and converts it into a string.
     *
     *
     *
     * @return A string representation of the map, which is a 2d array of characters
     *
     */
    public String generateMapString() {
        StringBuilder mapString = new StringBuilder();
        for (char[] row : map) {
            mapString.append(row).append('\n');
        }
        return mapString.toString();
    }

    /**
     * The setMove function takes in a String as an argument and uses it to determine the direction of movement.
     * It then checks if the move is valid, and if so, updates the map accordingly.

     *
     * @param String move Determine which direction the player is moving
     *
     * @return The current position of the player
     *
     */
    public void setMove(String move) {
        GameLauncher gameLauncher = new GameLauncher();


        int newY = currentPosition[1];
        int newX = currentPosition[0];


        switch (move) {
            case "w":
                newY -= 1;
                break;
            case "s":
                newY += 1;
                break;
            case "a":
                newX -= 1;
                break;
            case "d":
                newX += 1;
                break;
            default:
                System.out.println("Invalid move.");
                break;
        }

        if (isValidMove(newX, newY)) {
            if (isFinalMove(newX, newY)) {
                    map[currentPosition[1]][currentPosition[0]] = ' ';
                    currentPosition[0] = newX;
                    currentPosition[1] = newY;
                    System.out.println("Winner!");
                    gameLauncher.winner = true; // sets winner to true in game launcher to break the loop
                }
            else if (isOldMove(newX, newY) || isDoneOldMove(newX, newY)) {
                    map[currentPosition[1]][currentPosition[0]] = ' '; //sets previous position
                    currentPosition[0] = newX;
                    currentPosition[1] = newY;
                    map[newY][newX] = 'p';
                }
            else if (move == "h") {

                map[currentPosition[1]][currentPosition[0]] = 'h'; //sets previous position
                currentPosition[0] = newX;
                currentPosition[1] = newY;
                map[newY][newX] = 'p';
            }
            else {
                    map[currentPosition[1]][currentPosition[0]] = ' ';//sets previous position
                    currentPosition[0] = newX;
                    currentPosition[1] = newY;
                    map[newY][newX] = 'p';
                }


            } else {
            System.out.println("Invalid move or obstacle.");
            } // Exit the method if the move is invalid
        }


    /**
     * The isValidMove function checks to see if the player can move in a certain direction.
     *
     *
     * @param int x Determine the x coordinate of a tile
     * @param int y Determine the row of the map array
     *
     * @return True if the space is empty, false otherwise
     *
     */
    public boolean isValidMove(int x, int y) {
        return map[y][x] == ' ' || map[y][x] == '.' || map[y][x] == 'O' || map[y][x] == 'E'|| map[y][x] == 'U';
    }
    /**
     * The isFinalMove function checks if the player has reached the end of the maze.
     *
     *
     * @param int x Get the x coordinate of the current position
     * @param int y Determine the y-coordinate of the player's position
     *
     * @return True if the player is on the exit square
     *
     */
    public boolean isFinalMove(int x, int y) {
        return map[y][x] == 'E';
    }

    /**
     * The isOldMove function checks if the move is an old move.
     *
     *
     * @param int x Represent the x coordinate of the move
     * @param int y Determine the row of the map array
     *
     * @return True if the move is an old move
     *
     */
    public boolean isOldMove(int x, int y){
        return map[y][x] == 'U';
    }

    /**
     * The isDoneOldMove function checks if the move is already done.
     *
     *
     * @param int x Determine the x coordinate of a move
     * @param int y Specify the row of the map array
     *
     * @return True if the given position is a done old move, false otherwise
     */
    public boolean isDoneOldMove(int x, int y){
        return map[y][x] == 'O';
    }


}