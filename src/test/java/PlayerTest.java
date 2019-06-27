import static org.junit.Assert.*;
import org.junit.Test;
import static org.hamcrest.Matchers.*;

public class PlayerTest {
    private Player player = new Player();

    @Test
    public void testItsMoreThanNine(){
        assertThat(player.randomHole(), greaterThan(9));
    }

    @Test
    public void testItsLessThanEightTeen(){
        assertThat(player.randomHole(), lessThan(18));
    }
}
