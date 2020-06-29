package typer;

import common.Doer;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Does typing
 *
 * @author David
 */
public class Typer extends Doer {

    // Parameters
    private final String[] messages;
    private final int lowerMin;
    private final int upperMin;
    private final boolean enterWanted;

    // Counters
    private int msgCount;
    private int totalMins;
    private double aveMins;

    /**
     * Create Typer
     *
     * @param messages
     * @param lowerMin
     * @param upperMin
     * @param enterWanted
     */
    public Typer(String[] messages, int lowerMin, int upperMin,
            boolean enterWanted) {
        this.messages = messages;
        this.lowerMin = lowerMin;
        this.upperMin = upperMin;
        this.enterWanted = enterWanted;
    }

    /**
     * Inner class for Task
     */
    class Task extends TimerTask {

        @Override
        public void run() {

            // Get random number of minutes within bounds
            int ranMins = getRanVal(lowerMin, upperMin + 1);

            // Define fast minute
            int oneMin = 50;

            // Convert to milliseconds
            int ranMS = ranMins * oneMin * 1000;

            // Reschedules another task with given random delay
            timer.schedule(new Task(), ranMS);

            // Get random message
            int ranIndex = getRanVal(0, messages.length);
            String ranMsg = messages[ranIndex];

            // Type out each character of string
            for (char c : ranMsg.toCharArray()) {

                // Get key code
                int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);

                // Check key code
                if (KeyEvent.CHAR_UNDEFINED == keyCode) {
                    String msg = "Key code not found for char '" + c + "'";
                    System.out.println(msg);
                    keyCode = KeyEvent.VK_0;
                }

                // Press and release key
                pressAndReleaseKey(keyCode, c);
            }

            // If enter wanted
            if (enterWanted) {
                pressAndReleaseKey(KeyEvent.VK_ENTER, 'a');
            }

            // Adjust counters
            msgCount++;
            totalMins += ranMins;
            aveMins = (double) totalMins / (double) msgCount;
            aveMins = (double) Math.round(aveMins * 100) / 100;

            // Notify user
            String msgCountS = "msgCount: " + msgCount;
            String minXPS = ", minXP: " + msgCount * 15;
            String aveTimeS = ", aveMins: " + aveMins;
            String info = msgCountS + minXPS + aveTimeS;
            tGUI.code.notifyUser(info);
        }
    }

    /**
     * Press and release a key
     *
     * @param keyCode
     */
    private void pressAndReleaseKey(int keyCode, char c) {

        // Fix special keys
        if (c == ' ') {
            keyCode = KeyEvent.VK_SPACE;
        } else if (c == ':') {
            keyCode = KeyEvent.VK_SEMICOLON;
            c = 'A';
        }

        // Check if upper case
        boolean isUpper = Character.isUpperCase(c);

        // Press and release
        try {
            // Pressing
            // Press shift if upper case
            if (isUpper) {
                bot.keyPress(KeyEvent.VK_SHIFT);
            }

            // Press key
            bot.keyPress(keyCode);

            // Wait
            bot.delay(100);

            // Releasing
            // Release key
            bot.keyRelease(keyCode);

            // Wait (to ensure shift is not still down after program ends)
            bot.delay(100);

            // Release shift if upper case
            if (isUpper) {
                bot.keyRelease(KeyEvent.VK_SHIFT);
            }

            // Wait
            bot.delay(100);
        } catch (Exception e) {

            // Notify
            System.out.println("KeyError caused by: '" + c + "'");
        }
    }

    /**
     * Start doing typing
     */
    @Override
    public void startDoing() {

        // Reset timer
        timer = new Timer();

        // Start doing task after a short delay
        // (to allow "successfully processed" message to display)
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Task task = new Task();
                task.run();
            }
        }, 3000);
    }
}
