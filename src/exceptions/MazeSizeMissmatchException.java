package exceptions;

public class MazeSizeMissmatchException extends Exception {
    /**
     * The MazeSizeMissmatchException function is a constructor that takes in a string message and passes it to the super class.
     *
     *
     * @param String message Display a message to the user when an exception is thrown
     *
     * @return A message
     *
     */
    public MazeSizeMissmatchException(String message){
        super(message);
    }
}
