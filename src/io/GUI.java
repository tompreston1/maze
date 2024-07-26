
package io;

import exceptions.MazeMalformedException;
import exceptions.MazeSizeMissmatchException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;

public class GUI extends JPanel implements KeyListener {
    public char[][] column;
    public Move move;  // Declare a single game object
    public char[][] map;


    /**
     * The GUI function is the main function of the GUI class. It creates a JFrame
     * and adds a panel to it, which contains all of the graphics for our game.

     *
     * @param String fileName Initialize the map and move objects

     *
     * @return A jpanel
     *
     */
    public GUI(String fileName) throws FileNotFoundException, MazeMalformedException, MazeSizeMissmatchException {
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
        move = new Move(fileName);
        move.getMapData(); // Initialize game data
        map = new Position(fileName).map;
    }

    /**
     * The paintComponent function is a function that allows the user to draw on the screen.
     * It takes in a Graphics object, which is used to draw shapes and lines on the screen.
     * The paintComponent function will be called every time we need to update what's being drawn on our JPanel.

     *
     * @param Graphics g Draw the graphics on the panel
     *
     * @return A graphics object
     *
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (column != null) {
            int cellSize = Math.min(getWidth() / column[0].length, getHeight() / column.length);

            for (int i = 0; i < column.length; i++) {
                for (int j = 0; j < column[i].length; j++) {
                    char cell = column[i][j];
                    switch (cell) {
                        case 'O':
                            g.setColor(new Color(41, 90, 255));
                            break;
                        case 'U':
                            g.setColor(new Color(80, 234, 255));
                            break;
                        case '#':
                            g.setColor(Color.GRAY);
                            break;
                        case 'S':
                            g.setColor(Color.GREEN);
                            break;
                        case 'E':
                            g.setColor(Color.RED);
                            break;
                        case 'h':
                            g.setColor(Color.GREEN);
                            break;
                        case 'p':
                            g.setColor(Color.GREEN);
                            break;
                        case ' ':
                            g.setColor(Color.WHITE);
                            break;
                        case '.':
                            g.setColor(Color.WHITE);
                            break;

                    }

                    g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                }
            }
        }
    }

    /**
     * The updateData function is used to update the data in the column.
     *
     *
     * @param char[][] newData Update the data in the column
     *
     * @return A char[][] array
     *
     */
    public void updateData(char[][] newData) {
        this.column = newData;
        repaint();
    }

    /**
     * The createAndShowGui function creates a JFrame and adds the MazePanel to it.
     * It also sets the size of the frame based on maze dimensions, makes it visible,
     * and starts a game loop that updates data in MazePanel every 100 milliseconds.

     *
     *
     * @return Void, which is why the main function can't return it
     *
     */
    public void createAndShowGui() {
        int cellSize = 30; // You can adjust the cell size as needed

        int frameWidth = map[0].length * cellSize;
        int frameHeight = map.length * cellSize;

        JFrame frame = new JFrame("Maze");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.setSize(frameWidth, frameHeight); // Set the frame size based on maze dimensions
        frame.setVisible(true);

        boolean isRunning = !move.isFinalMove(move.currentPosition[0], move.currentPosition[1]);

        while (isRunning) {
            updateData(move.getMapData());
            // Add some delay to control the game loop speed
            GameLauncher gameLauncher = new GameLauncher();
            if (gameLauncher.winner){
                break;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            isRunning = !move.isFinalMove(move.currentPosition[0], move.currentPosition[1]);
        }

    }

    /**
     * The keyTyped function is used to detect when a key has been typed.
     *
     *
     * @param KeyEvent e Get the key that was pressed
     *
     * @return The key that was pressed
     *
     */
    @Override
    public void keyTyped(KeyEvent e) {
        // Not used in this example
    }

    /**
     * The keyPressed function is a function that allows the user to move their character around
     * using the WASD keys. It also allows them to quit by pressing Q.

     *
     * @param KeyEvent e Get the key that was pressed
     *
     * @return A string that is set in the move class
     *
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            // Move left
            move.setMove("a");
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            // Move right
            move.setMove("d");
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            // Move up
            move.setMove("w");
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            // Move down
            move.setMove("s");
        } else if (e.getKeyCode() == KeyEvent.VK_P) {
            // Move down
            move.setMove("h");
        }
    }

    /**
     * The keyReleased function is used to detect when a key has been released.
     *
     *
     * @param KeyEvent e Get the key that was pressed
     *
     * @return The key that was pressed
     *
     */
    @Override
    public void keyReleased(KeyEvent e) {
        // Not used in this example
    }
}
