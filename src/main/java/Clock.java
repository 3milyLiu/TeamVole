import javax.swing.*;
import java.awt.event.ActionEvent;
import javax.swing.Timer;


public class Clock {

    private int hours;
    private int mins;
    private int secs;
    private boolean isRunning;

    private Timer timer;
    private static final int DELAY = 1000;

    //Clock constructor, sets everything to zero and false
    public Clock(){
        this.hours = 0;
        this.secs = 0;
        this.mins = 0;
        this.isRunning = false;
    }

    private void tick() {
        if (this.secs == 59) {
            this.secs = 0;
            this.mins++;
        }
        if (this.mins == 59) {
            this.mins = 0;
            this.hours++;
        }
        this.secs++;
    }

    //Starts the clock
    private void start() {
        this.isRunning = true;
        this.timer.restart();
    }

    //Stops the clock
    private void stop() {
        this.isRunning = false;
        this.timer.stop();
    }

    // Sets clock to zero
    public void reset() {
        if (this.isRunning) run();
        this.hours = 0;
        this.mins = 0;
        this.secs = 0;
        updateDisplay();
    }

    //Decides to either stops the clock or run the clock
    public void run() {
        if (this.isRunning) {
            stop();
        } else {
            start();
        }
    }

    //Displays clock as 00:00:00
    public String updateDisplay() {
        return String.format("%02d:%02d:%02d", this.hours, this.mins, this.secs);
    }

    public void timer() {
        Action updateLabelAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                tick();
            }
        };
        this.timer = new Timer(DELAY, updateLabelAction);
    }

    //@return hours as an integer
    public int getHours(){
       return hours;
    }

    //@return minutes as an integer
    public int getMins(){
        return mins;
    }

    //@return seconds as an integer
    public int getSecs(){
        return secs;
    }
}
