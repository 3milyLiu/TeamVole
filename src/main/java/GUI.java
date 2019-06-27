import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GUI extends JFrame {
    private JFrame board = new JFrame();

    private final JPanel left_panel = new JPanel(new BorderLayout());
    private final JPanel right_panel = new JPanel(new BorderLayout());

    private final JPanel top = new JPanel(new BorderLayout());
    private final JPanel top_button = new JPanel(new GridLayout(2, 9));
    private final JPanel top_main_button = new JPanel(new GridLayout(1, 9));

    private final JPanel bottom = new JPanel(new GridLayout(2, 9));
    private final JPanel bottom_button = new JPanel(new GridLayout(2, 9));
    private final JPanel bottom_main_button = new JPanel(new GridLayout(1, 9));

    private final JPanel middle = new JPanel(new BorderLayout());
    private final JPanel center = new JPanel(new FlowLayout());

    private final JButton[] playerButtons = new JButton[9];
    private final JButton[] computerButtons = new JButton[9];
    private final JButton[] redPButtons = new JButton[9];
    private final JButton[] redCButtons = new JButton[9];

    private JButton redBallHolder1, redBallHolder2;
    private JButton playerKazan, comKazan;

    private final JLabel[] tpLabel = new JLabel[11];
    private final JLabel[] bmLabel = new JLabel[11];

    private JButton handshake = new JButton("Shake Hands");

    //constructor for clock
    private Clock clk = new Clock();
    private final JLabel timeLabel = new JLabel();
    private JPanel top_clock = new JPanel(new FlowLayout());
    private ButtonListener listener = new ButtonListener();
    private JButton startButton = new JButton("Start");
    private JButton resetButton = new JButton("Reset");

    private Game game = new Game();
    private JFrame frame = new JFrame();

    private boolean isComplete;

    /**
     * Set the overall skeleton of the board
     */
    private void initSetUp() {
        Dimension boardSize = new Dimension(1090, 560);

        board.setPreferredSize(boardSize);
        board.setTitle("Toguz Korgool || Team Vole");
        board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //main panels added to the container
        Container contents = board.getContentPane();
        contents.add(left_panel, BorderLayout.WEST);
        contents.add(right_panel, BorderLayout.EAST);

        //Left panel set up
        left_panel.setPreferredSize(new Dimension(900, 550));
        left_panel.setBackground(Color.WHITE);

        board.setResizable(false);
        board.setVisible(true);
        board.pack();

        isComplete = true;
    }

    public GUI() {
        addButtons();
        addLabels();
        clk.timer();
        initSetUp();
    }

    private void addButtons() {
        //Red ball holders (next to Kazan)
        redBallHolder1 = new JButton("1");
        redBallHolder2 = new JButton("1");
        redBallHolder1.setForeground(Color.magenta);
        redBallHolder2.setForeground(Color.blue);
        redBallHolder1.setEnabled(false);
        redBallHolder2.setEnabled(false);
        middle.add(redBallHolder1, BorderLayout.WEST);
        middle.add(redBallHolder2, BorderLayout.EAST);

        //Kazan
        Dimension kazan_size = new Dimension(340, 130);
        middle.add(center, BorderLayout.CENTER);

        playerKazan = new JButton("Player: 0");
        playerKazan.setForeground(Color.magenta);
        playerKazan.setEnabled(false);
        center.add(playerKazan);
        playerKazan.setPreferredSize(kazan_size);
        //playerKazan.addActionListener(new EndGameListener());

        comKazan = new JButton("Computer: 0");
        comKazan.setForeground(Color.blue);
        comKazan.setEnabled(false);
        center.add(comKazan);
        comKazan.setPreferredSize(kazan_size);
       // comKazan.addActionListener(new EndGameListener());

        //main buttons for player 1
        for (int p = 0; p < 9; p++) {
            playerButtons[p] = new JButton("9");
            playerButtons[p].setPreferredSize(new Dimension(50, 90));
            top_main_button.add(playerButtons[p]);
            playerButtons[p].setForeground(Color.magenta);
            playerButtons[p].addActionListener(new GameActionListener());
            playerButtons[p].setEnabled(false);
        }

        //red ball buttons for player 1
        for (int rp = 0; rp < 9; rp++) {
            redPButtons[rp] = new JButton("0");
            redPButtons[rp].setPreferredSize(new Dimension(50, 50));
            top_button.add(redPButtons[rp]);
            redPButtons[rp].setForeground(Color.blue);
            redPButtons[rp].setEnabled(false);
            redPButtons[rp].addActionListener(new TuzListener());
        }

        // main buttons for player 2
        for (int c = 0; c < 9; c++) {
            computerButtons[c] = new JButton("9");
            computerButtons[c].setPreferredSize(new Dimension(50, 90));
            bottom_main_button.add(computerButtons[c]);
            computerButtons[c].setForeground(Color.blue);
            computerButtons[c].addActionListener(new GameActionListener());
            computerButtons[c].setEnabled(false);
        }

        //red ball holder for player 2
        for (int rc = 0; rc < 9; rc++) {
            redCButtons[rc] = new JButton("0");
            redCButtons[rc].setPreferredSize(new Dimension(50, 50));
            bottom_button.add(redCButtons[rc]);
            redCButtons[rc].setForeground(Color.magenta);
            redCButtons[rc].setEnabled(false);
            redCButtons[rc].addActionListener(new TuzListener());
        }
    }

    //Add labels to the GUI
    private void addLabels() {
        //main panels for right panel
        JPanel top_right = new JPanel(new BorderLayout());
        JPanel bottom_right = new JPanel(new BorderLayout());
        JPanel top_name = new JPanel();
        JPanel bottom_name = new JPanel();

        top.add(top_main_button, BorderLayout.NORTH);
        top.add(top_button, BorderLayout.SOUTH);

        bottom.add(bottom_button, BorderLayout.NORTH);
        bottom.add(bottom_main_button, BorderLayout.SOUTH);
        //right panel dimensions
        Dimension right_top_bottom = new Dimension(180, 265);

        Dimension player_size = new Dimension(180, 20);
        Dimension top_clock_size = new Dimension(179, 100);
        right_panel.setPreferredSize(new Dimension(180, 550));

        top_right.setPreferredSize(right_top_bottom);
        bottom_right.setPreferredSize(right_top_bottom);

        top_name.setSize(player_size);
        bottom_name.setPreferredSize(player_size);
        top_clock.setPreferredSize(top_clock_size);

        startButton.addActionListener(listener);
        startButton.setPreferredSize(new Dimension(90, 25));
        startButton.setEnabled(false);
        top_clock.add(startButton);

        resetButton.addActionListener(listener);
        resetButton.setPreferredSize(new Dimension(90, 25));
        resetButton.setEnabled(false);
        top_clock.add(resetButton);

        //adding to the left panel
        left_panel.add(top, BorderLayout.NORTH);
        left_panel.add(bottom, BorderLayout.SOUTH);
        left_panel.add(middle, BorderLayout.CENTER);

        //adding to the right panel
        right_panel.add(top_right, BorderLayout.NORTH);
        right_panel.add(bottom_right, BorderLayout.SOUTH);

        //adding to the top right panel
        top_right.add(top_name, BorderLayout.NORTH);
        top_right.add(top_clock, BorderLayout.SOUTH);
        top_right.add(timeLabel, BorderLayout.CENTER);
        timeLabel.setHorizontalAlignment(JLabel.CENTER);
        timeLabel.setPreferredSize(new Dimension(100, 50));
        top_name.add(new JLabel("Player"));
        bottom_right.add(bottom_name, BorderLayout.NORTH);

        bottom_name.add(new JLabel("Computer"));
        bottom_right.add(handshake, BorderLayout.SOUTH);
        handshake.addActionListener(new HandshakeListener());

        //Player's hole label
        for (int l = 9; l > 0; l--) {
            tpLabel[l] = new JLabel("" + l );
            tpLabel[l].setHorizontalAlignment(JLabel.CENTER);
            top_button.add(tpLabel[l]);
        }

        //Computer's hole label
        for (int l = 0; l < 9; l++) {
            bmLabel[l] = new JLabel("" + (l + 1));
            bmLabel[l].setHorizontalAlignment(JLabel.CENTER);
            bottom_button.add(bmLabel[l]);
        }
    }

    private class GameActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == playerButtons[0]) {
                game.currentHole = 9;
                run();
            } else if (e.getSource() == playerButtons[1]) {
                game.currentHole = 8;
                run();
            } else if (e.getSource() == playerButtons[2]) {
                game.currentHole = 7;
                run();
            } else if (e.getSource() == playerButtons[3]) {
                game.currentHole = 6;
                run();
            } else if (e.getSource() == playerButtons[4]) {
                game.currentHole = 5;
                run();
            } else if (e.getSource() == playerButtons[5]) {
                game.currentHole = 4;
                run();
            } else if (e.getSource() == playerButtons[6]) {
                game.currentHole = 3;
                run();
            } else if (e.getSource() == playerButtons[7]) {
                game.currentHole = 2;
                run();
            } else if (e.getSource() == playerButtons[8]) {
                game.currentHole = 1;
                run();
            } else if (e.getSource() == computerButtons[0]) {
                game.currentHole = 17;
                game.makeMove();
                setText();
            } else if (e.getSource() == computerButtons[1]) {
                game.currentHole = 16;
                game.makeMove();
                setText();
            } else if (e.getSource() == computerButtons[2]) {
                game.currentHole = 15;
                game.makeMove();
                setText();
            } else if (e.getSource() == computerButtons[3]) {
                game.currentHole = 14;
                game.makeMove();
                setText();
            } else if (e.getSource() == computerButtons[4]) {
                game.currentHole = 13;
                game.makeMove();
                setText();
            } else if (e.getSource() == computerButtons[5]) {
                game.currentHole = 12;
                game.makeMove();
                game.makeMove();
                setText();

            } else if (e.getSource() == computerButtons[6]) {
                game.currentHole = 11;
                game.makeMove();
                setText();
            } else if (e.getSource() == computerButtons[7]) {
                game.currentHole = 10;
                game.makeMove();
                setText();
            } else if (e.getSource() == computerButtons[8]) {
                game.currentHole = 9;
                game.makeMove();
                setText();
            }
        }
    }

    private void run() {
        game.makeMove();
        move();
        setText();
        switchPlayer();
        outcomeDisplay();
        computerMove();
        outcomeDisplay();
    }

    private void outcomeDisplay() {
        if(game.gameOver() && game.playerNumberOfKorgools() > 81 ){
            JOptionPane.showMessageDialog(frame,"You won!!",
                    "Congratulations!", JOptionPane.PLAIN_MESSAGE);
        }
        else if(game.gameOver()){
            JOptionPane.showMessageDialog(frame,"You Lost",
                    "Try Again!", JOptionPane.PLAIN_MESSAGE);
        }
    }

    private void switchPlayer(){
        game.playerTurn = !(game.playerTurn);
    }

    private void computerMove(){
        if (!game.playerTurn) {
            game.computerMove();
            game.makeMove();
            move();
            setText();
            switchPlayer();
        }
    }

    private void move(){
        if (game.take) {
            if (game.playerTurn) {
                //set the number in players kazan
               playerKazan.setText(game.getKazan());
            } else {
                comKazan.setText(game.getKazan());
                comKazan.setText(game.getKazan());
            }
            setText();
        }

    }
    private void setText() {
        playerButtons[8].setText(game.inHole(0));
        playerButtons[7].setText(game.inHole(1));
        playerButtons[6].setText(game.inHole(2));
        playerButtons[5].setText(game.inHole(3));
        playerButtons[4].setText(game.inHole(4));
        playerButtons[3].setText(game.inHole(5));
        playerButtons[2].setText(game.inHole(6));
        playerButtons[1].setText(game.inHole(7));
        playerButtons[0].setText(game.inHole(8));
        computerButtons[0].setText(game.inHole(9));
        computerButtons[1].setText(game.inHole(10));
        computerButtons[2].setText(game.inHole(11));
        computerButtons[3].setText(game.inHole(12));
        computerButtons[4].setText(game.inHole(13));
        computerButtons[5].setText(game.inHole(14));
        computerButtons[6].setText(game.inHole(15));
        computerButtons[7].setText(game.inHole(16));
        computerButtons[8].setText(game.inHole(17));
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == startButton) {
                clk.run();
                startButton.setText("Start/Stop");
                timeLabel.setText(clk.updateDisplay());
            } else {
                clk.reset();
                startButton.setText("Start");
                timeLabel.setText(clk.updateDisplay());
            }
        }
    }

    private class HandshakeListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            if (e.getSource() == handshake) {
                if(isComplete){
                    isComplete = false;
                    for(int i = 0; i < 9; i++){
                        playerButtons[i].setEnabled(true);
                        computerButtons[i].setEnabled(true);
                        redCButtons[i].setEnabled(true);
                        redPButtons[i].setEnabled(true);
                    }
                    resetButton.setEnabled(true);
                    startButton.setEnabled(true);
                    redBallHolder1.setEnabled(true);
                    redBallHolder2.setEnabled(true);
                    playerKazan.setEnabled(true);
                    comKazan.setEnabled(true);
                    handshake.setText("Reset Game");
                }
                else {
                    isComplete = true;
                    board.setVisible(false);
                    new GUI();
                }
            }
        }
    }

    private class TuzListener implements  ActionListener {
        public void actionPerformed(ActionEvent e){
            //for player
            if (e.getSource() == redCButtons[0]) {
                if(computerButtons[0].getText().equals("3") && redBallHolder1.getText().equals("1")){
                    redBallHolder1.setText("0");
                    redCButtons[0].setText("X");
                }
            }
            else if (e.getSource() == redCButtons[1]){
                if(computerButtons[1].getText().equals("3") && redBallHolder1.getText().equals("1")){
                    redBallHolder1.setText("0");
                    redCButtons[1].setText("X");
                }
            }
            else if (e.getSource() == redCButtons[2]){
                if(computerButtons[2].getText().equals("3") && redBallHolder1.getText().equals("1")){
                    redBallHolder1.setText("0");
                    redCButtons[2].setText("X");
                }
            }
            else if (e.getSource() == redCButtons[3]){
                if(computerButtons[3].getText().equals("3") && redBallHolder1.getText().equals("1")){
                    redBallHolder1.setText("0");
                    redCButtons[3].setText("X");
                }
            }
            else if (e.getSource() == redCButtons[4]){
                if(computerButtons[4].getText().equals("3") && redBallHolder1.getText().equals("1")){
                    redBallHolder1.setText("0");
                    redCButtons[4].setText("X");
                }
            }
            else if (e.getSource() == redCButtons[5]){
                if(computerButtons[5].getText().equals("3") && redBallHolder1.getText().equals("1")){
                    redBallHolder1.setText("0");
                    redCButtons[5].setText("X");
                }
            }
            else if (e.getSource() == redCButtons[6]){
                if(computerButtons[6].getText().equals("3") && redBallHolder1.getText().equals("1")){
                    redBallHolder1.setText("0");
                    redCButtons[6].setText("X");
                }
            }
            else if (e.getSource() == redCButtons[7]){
                if(computerButtons[7].getText().equals("3") && redBallHolder1.getText().equals("1")){
                    redBallHolder1.setText("0");
                    redCButtons[7].setText("X");
                }
            }
            else if (e.getSource() == redCButtons[8]){
                if(computerButtons[8].getText().equals("3") && redBallHolder1.getText().equals("1")){
                    redBallHolder1.setText("0");
                    redCButtons[8].setText("X");
                }
            }
        }
    }
}
