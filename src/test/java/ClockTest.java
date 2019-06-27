import static org.junit.Assert.*;
import org.junit.Test;

public class ClockTest {

    private Clock clock = new Clock();

    @Test
    public void checksIfItResetsHoursToZero(){
        clock.reset();
        assertEquals(0, clock.getHours());
    }

    @Test
    public void checksIfItResetsMinsToZero(){
        clock.reset();
        assertEquals(0, clock.getMins());
    }

    @Test
    public void checksIfItResetsSecsToZero(){
        clock.reset();
        assertEquals(0, clock.getSecs());
    }

    @Test
    public void checksDisplay(){
        assertEquals("00:00:00", clock.updateDisplay());
    }

}
