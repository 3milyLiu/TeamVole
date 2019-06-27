import java.util.Random;

public class Player {
    public boolean computerTuz;
    public int computerTuzHole = 0;
    public int computerKazan;
    private static final Random generator = new Random();
    public boolean playerTuz;
    public int playerTuzHole = 0;
    public int playerKazan;

    public Player(){
        computerTuz = false;
        computerKazan = 0;
        playerTuz = false;
        playerKazan = 0;
    }

    private static int getRandomInRange(int start, int end) {
        return start + generator.nextInt(end - start + 1);
    }

    public int randomHole() {
        return getRandomInRange(10, 18);
    }
}
