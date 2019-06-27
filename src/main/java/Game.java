import java.util.ArrayList;
//import java.util.Random;

public class Game {
    //number of hole (1-18) will be the one that player clicks
    public int currentHole;
    private int newHole;
    //true if it is players turn
    public boolean playerTurn;
    private Player player = new Player();

    //player side
    private ArrayList<Integer> holes = new ArrayList<>();
    public boolean take;
    //private static final Random generator = new Random();

    /** Creates the Toguz Korgool game board:
     * 162 korgools are distributed equally over the 18 holes in the board
     * 9 korgools per hole
     * kazan for player and computer
     */
    public Game(){
        playerTurn = true;
        currentHole = 1;
        newHole = 0;
        take = false;
        for (int i = 0; i < 18; i++) {
            //9 korgools in each of the holes
            holes.add(9);
        }
    }
    /**
     * Korgool redisribution
     */
    public void makeMove(){
        int toMove = checkHole();
        //set currentHole equal to the hole the player clicks
        if(toMove > 0) {
            take = true;
            move();
            for (int i = 0; i < toMove ; i++) {
                //redistribution after hole 18, go back to hole 1
                int moveTo = (currentHole -1 + i) ;
                if (moveTo > 17) {
                    moveTo = moveTo - 18;
                }
                int inHole = currentKorgoolsInHole(moveTo);
                holes.set(moveTo, inHole + 1);
               // holes.set(moveTo, (currentKorgoolsInHole(moveTo) + 1));

            }
            newHole = currentHole + toMove - 2;
            //goes back to player's side of the board
            if (newHole > 17) {
                newHole = newHole - 18;
            }
            check();
            newHole = 0;
        }
    }

    /**
     * Checks which side the last korgool lands in
     * @return true if last korgool lands in opponents side
     */
    public boolean checkSide() {
        if (playerTurn && (newHole > 8)) {
            //take all balls in last hole
            holes.set(newHole,0);
            return true;
        } else if (!playerTurn && (newHole < 9)) {
            holes.set(newHole,0);
            return true;
        }
        return false;
    }

    /**
     * Gets the number of korgools in hole
     */
    private int checkHole(){
        return holes.get(currentHole - 1);
    }

    /**
     * @return returns number of korgools in hole index i
     */
    private int currentKorgoolsInHole(int i){
        return holes.get(i);
    }

    /**
     * @return the number of korgools in hole as a string
     */
    public String inHole(int i){
        return holes.get(i).toString();
    }

    /**
     * Gets the number of korgools in hole with the last korgool
     */
    public int lastCheck(){
        return holes.get(newHole);
    }

    /**
     * Takes all the korgools from hole
     */
    private void move() {
        holes.set(currentHole - 1, 0);
    }

    /**
     * Checks if any korgools have been won or tuz
     */
    private void check() {
        int inHole = lastCheck();
        if (lastCheck() % 2 == 0 && checkSide() && !player.playerTuz && !player.computerTuz) {
            take = true;
            collectKorgools(inHole);
            checkSide();
        } else if (lastCheck() == 3 && !checkSide() && !player.playerTuz) {
            //set hole to tuz
            player.playerTuzHole = newHole;
            player.playerTuz = true;
        } else if (lastCheck() == 3 && !checkSide() && !player.computerTuz) {
            //set hole to tuz
            player.computerTuzHole = newHole;
            player.computerTuz = true;
        } else if (player.playerTuz && !playerTurn) {
            collectKorgools((holes.get(player.playerTuzHole)));
        } else if (player.computerTuz && playerTurn) {
            collectKorgools((holes.get(player.computerTuzHole)));
        }
    }

    public void computerMove(){
        currentHole = player.randomHole();
            if(checkHole() == 0){
                currentHole = player.randomHole();
            }
    }

    /**
     * Checks whether a player has collected 82 or more korgools in their kazan
     */
    public boolean gameOver() {
        if(playerNumberOfKorgools() > 81){
            return true;
        }
        else if(computerNumberOfKorgools() > 81){
            return true;
        }
        return false;
    }

    /**
     * Gets the number of korgools in the player's kazan
     */
    public int playerNumberOfKorgools() {
        return player.playerKazan;
    }

    /**
     * Gets the number of korgools in the computer's kazan
     */
    public int computerNumberOfKorgools() {
        return player.computerKazan;
    }

    /**
     * Picks up korgools if even number
     */
    public void collectKorgools(int i) {
        if (playerTurn) {
            player.playerKazan += i;
        } else {
            player.computerKazan += i;
        }
    }

    /**
     * @return the number of korgools in player or computer kazaan as a string
     */
    public String getKazan(){
        String playerkazan = "Player: " + playerNumberOfKorgools();
        String computerkazan = "Computer: " + computerNumberOfKorgools();
        if (playerTurn) {
            return playerkazan;
        }
        else{
            return computerkazan;
        }
    }

    public int getNewHole(){
        return newHole;
    }

    public int getCurrentHole() {return currentHole;}
}
