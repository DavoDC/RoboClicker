package common;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Common Robot doer
 *
 * @author David
 */
public abstract class Doer {

    // Robot
    public Robot bot;

    // Timer
    public Timer timer;

    // RNG
    public Random rng;

    /**
     * Create doer
     */
    public Doer() {

        // Initialize main objects
        try {

            bot = new Robot();
            timer = new Timer();
            rng = new Random();
        } catch (AWTException ex) {
            Logger.getLogger(Doer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Helper that gets a random value between the given bounds
     *
     * @param lowerBound
     * @param upperBound
     * @return
     */
    public int getRanVal(int lowerBound, int upperBound) {
        return rng.nextInt(upperBound - lowerBound) + lowerBound;
    }

    /**
     * Stop doing things
     */
    public void stopDoing() {
        timer.cancel();
        timer.purge();
    }

    /**
     * Inner class for Task
     */
    private abstract class Task extends TimerTask {

    }

    /**
     * Start doing actions
     */
    public abstract void startDoing();
}
