package exceptions;

public class MazeMalformedException extends Exception {
    /**
     * The MazeMalformedException function is a constructor that takes in a string message and passes it to the super class.
     *
     *
     * @param String message Display the error message
     *
     * @return A message
     *
     */
    public MazeMalformedException(String message){
        super(message);
    }
}
