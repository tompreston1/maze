import exceptions.MazeMalformedException;
import exceptions.MazeSizeMissmatchException;
import io.Position;

import java.io.FileNotFoundException;
import java.util.Stack;

public class MazeSolver {
    private char[][] maze;
    private int startX;
    private int startY;
    private int endX;
    private int endY;

    /**
     * The MazeSolver function takes in a file name and creates a maze object.
     * It then sets the startX, startY, endX, and endY variables to their respective values.
     *
     *
     * @param String fileName Read the file and create a new maze
     *
     * @return A mazesolver object
     *
     */
    public MazeSolver(String fileName) throws FileNotFoundException, MazeMalformedException, MazeSizeMissmatchException {
        Position pos = new Position(fileName);
        maze = pos.map;
        startX = pos.getStartingPosition()[0];
        startY = pos.getStartingPosition()[1];
        endX = pos.getEndingPosition()[0];
        endY = pos.getEndingPosition()[1];
    }

    /**
     * The main function takes in a single argument, which is the name of the file
     * containing the maze. It then creates a MazeSolver object and uses it to solve
     * the maze. If there are any errors, they will be printed out to stderr.

     *
     * @param String[] args Pass command line arguments to the program
     *
     * @return Nothing
     *
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            return;
        }

        try {
            MazeSolver mazeSolver = new MazeSolver(args[0]);
            if (mazeSolver.isMazeSolvable()) {
                System.out.println("Maze is solvable!");
            } else {
                System.out.println("Maze is unsolvable!");
            }
        } catch (FileNotFoundException | MazeMalformedException | MazeSizeMissmatchException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * The isMazeSolvable function determines whether or not the maze is solvable.
     * It does this by using a stack to keep track of the current position and all possible moves from that position.
     * If there are no more possible moves, it pops off the last element in the stack and checks for more possible moves from that position.
     * This continues until either a solution is found or there are no more elements in the stack (meaning there is no solution).

     *
     *
     * @return True if the maze is solvable, and false otherwise
     *
     */
    public boolean isMazeSolvable() {
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{startX, startY});
        boolean[][] visited = new boolean[maze.length][maze[0].length];

        while (!stack.isEmpty()) {
            int[] currentPosition = stack.pop();
            int x = currentPosition[0];
            int y = currentPosition[1];

            if (x == endX && y == endY) {
                // Maze is solvable
                return true;
            }

            if (isValidMove(x + 1, y, visited)) {
                stack.push(new int[]{x + 1, y});
                visited[y][x + 1] = true;
            }
            if (isValidMove(x - 1, y, visited)) {
                stack.push(new int[]{x - 1, y});
                visited[y][x - 1] = true;
            }
            if (isValidMove(x, y + 1, visited)) {
                stack.push(new int[]{x, y + 1});
                visited[y + 1][x] = true;
            }
            if (isValidMove(x, y - 1, visited)) {
                stack.push(new int[]{x, y - 1});
                visited[y - 1][x] = true;
            }
        }

        // Maze is unsolvable
        return false;
    }

    /**
     * The isValidMove function checks if the given x and y coordinates are valid
     * for the current maze. It does this by checking that they are within the bounds
     * of the maze, and that they do not correspond to a wall in the maze. The visited array is used to ensure
     * we don't visit any cell more than once. This function returns true if it is a valid move, false otherwise.

     *
     * @param int x Represent the x coordinate of a cell in the maze
     * @param int y Keep track of the y coordinate in the maze
     * @param boolean[][] visited Keep track of which cells have been visited
     *
     * @return Whether or not a given move is valid
     *
     */
    private boolean isValidMove(int x, int y, boolean[][] visited) {
        if (x >= 0 && x < maze[0].length && y >= 0 && y < maze.length) {
            return maze[y][x] != '#' && !visited[y][x];
        }
        return false;
    }
}
