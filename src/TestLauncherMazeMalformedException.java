import exceptions.MazeMalformedException;
import org.junit.Test;

import static org.junit.Assert.assertThrows;

public class TestLauncherMazeMalformedException {

    /**
     * The testLauncherMazeMalformedException function tests the Launcher class's main function to see if it throws a MazeMalformedException when given an invalid map file.
     *
     * @return A mazemalformedexception
     *
     */
    @Test
    public void testLauncherMazeMalformedException() {
        String[] args = {"src/TestMap2.txt"};

        assertThrows(MazeMalformedException.class, () -> {
            Launcher.main(args);
        });
    }
}
