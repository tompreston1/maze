import exceptions.MazeSizeMissmatchException;
import org.junit.Test;

import static org.junit.Assert.assertThrows;

public class TestLauncherMazeSizeMissmatchException {

    @Test
    public void testLauncherMazeSizeMissmatchException() {
        String[] args = {"src/TestMap.txt"};
        assertThrows(MazeSizeMissmatchException.class, () -> {
            Launcher.main(args);
        });
    }

}
