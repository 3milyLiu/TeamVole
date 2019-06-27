import static org.junit.Assert.*;
import org.junit.Test;
import static org.hamcrest.Matchers.*;

public class GameTest {

    private Game game = new Game();

    @Test
    public void testInHole(){
        assertEquals("9", game.inHole(9));
    }

    @Test
    public void testLastCheck(){
        assertEquals(9, game.lastCheck());
    }

    @Test
    public void testGameOutcome(){
        assertFalse(game.gameOver());
    }

    @Test
    public void testGetKazan(){
        assertEquals("Player: 0", game.getKazan());
    }

    @Test
    public void testCheckSide(){
        assertFalse(game.checkSide());
    }

    @Test
    public void testNewHole(){
        assertEquals(0, game.getNewHole());
    }

    //Tests that the computer can only move between 9 to 18
    @Test
    public void testComputerMove() {
        game.computerMove();
        assertThat(game.getCurrentHole(), allOf(greaterThan(9), lessThan(18)));
    }

    @Test
    public void testPlayerNumberOfKorgools(){
        assertEquals(0, game.playerNumberOfKorgools());
    }

    @Test
    public void testComputerNumberOfKorgools(){
        assertEquals(0, game.computerNumberOfKorgools());
    }

    @Test
    public void testCollectKorgools(){
        game.collectKorgools(5);
        assertEquals("Player: 5", game.getKazan());
    }

    @Test
    public void testCollectKorgoolsIfItsZero(){
        game.collectKorgools(0);
        assertEquals("Player: 0", game.getKazan());
    }
}
