package clicker;

import common.Doer;
import java.util.TimerTask;

/**
 * Does random clicking
 *
 * @author David
 */
public class Clicker extends Doer {

    // Parameters
    private final int loX;
    private final int hiX;
    private final int loY;
    private final int hiY;
    private final int loTime;
    private final int hiTime;
    private final int mCode;

    /**
     * Create Clicker
     *
     * @param params
     */
    public Clicker(int[] params) {

        // Save parameters
        loX = params[0];
        hiX = params[1];
        loY = params[2];
        hiY = params[3];
        loTime = params[4];
        hiTime = params[5];
        mCode = params[6];
    }

    /**
     * Inner class for Task
     */
    class Task extends TimerTask {

        @Override
        public void run() {

            // Get random values
            int ranX = getRanVal(loX, hiX);
            int ranY = getRanVal(loY, hiY);
            int ranTime = getRanVal(loTime, hiTime);

            // This task reschedules another one with a random delay
            timer.schedule(new Task(), ranTime);

            // Do clicking
            //  Move mouse
            bot.mouseMove(ranX, ranY);
            //  Press and release
            bot.mousePress(mCode);
            bot.mouseRelease(mCode);
        }

    }

    /**
     * Start doing clicking
     */
    @Override
    public void startDoing() {

        Task task = new Task();
        task.run();
    }
}
