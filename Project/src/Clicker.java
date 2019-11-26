
import java.awt.Robot;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Does random clicking
 *
 * @author David 
 */
public class Clicker
{

    // Main objects needed
    // Robot
    private Robot bot;
    // Timer
    private Timer timer;
    // RNG
    private Random rng;

    // Parameters
    private int loX;
    private int hiX;
    private int loY;
    private int hiY;
    private int loTime;
    private int hiTime;
    private int mCode;

    /**
     * Create and start Clicker object
     *
     * @param params
     */
    public Clicker(int[] params)
    {
        // Save parameters
        loX = params[0];
        hiX = params[1];
        loY = params[2];
        hiY = params[3];
        loTime = params[4];
        hiTime = params[5];
        mCode = params[6];

        try
        {
            // Initialize main objects
            bot = new Robot();
            timer = new Timer();
            rng = new Random();

            // Start clicking
            Task click = new Task();
            click.run();
        }
        catch (Exception e)
        {
            System.out.println("Clicker constructor error");
        }

    }

    /**
     * Inner class for Task
     */
    class Task extends TimerTask
    {

        @Override
        public void run()
        {
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
     * Helper that gets a random value between the given bounds
     *
     * @param lowerBound
     * @param upperBound
     * @return
     */
    public int getRanVal(int lowerBound, int upperBound)
    {
        return rng.nextInt(upperBound - lowerBound) + lowerBound;
    }

}
