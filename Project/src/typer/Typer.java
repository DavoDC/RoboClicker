package typer;

import common.Doer;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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
    private final int loSec;
    private final int hiSec;
    private final boolean enterWanted;
    private final int keyDelay;

    // Time started
    private LocalTime startTime;

    // Statistic variables
    private int msgCount;
    private int totalSecs;
    private double aveDelay;

    /**
     * Create Typer
     *
     * @param messages
     * @param loSec
     * @param hiSec
     * @param enterWanted
     * @param keyDelay
     */
    public Typer(String[] messages, int loSec, int hiSec,
            boolean enterWanted, int keyDelay) {
        this.messages = messages;
        this.loSec = loSec;
        this.hiSec = hiSec;
        this.enterWanted = enterWanted;
        this.keyDelay = keyDelay;
    }

    /**
     * Inner class for Task
     */
    class Task extends TimerTask {

        @Override
        public void run() {

            // Get random number of seconds within bounds
            int ranSec = getRanVal(loSec, hiSec + 1);

            // Convert to milliseconds
            int ranMS = ranSec * 1000;

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

            // Message Statistics
            // List of information
            ArrayList<String> info = new ArrayList<>();

            // Increase message count
            msgCount++;
            addPart(info, "N", msgCount);

            // Calculate average delay between mesages
            totalSecs += ranSec;
            aveDelay = (double) totalSecs / (double) msgCount;
            aveDelay = roundTo2DP(aveDelay);
            addPart(info, "aveDelay", aveDelay, "s");

            // Get up time
            Duration timeSinceStart;
            timeSinceStart = Duration.between(startTime, LocalTime.now());
            String upTime = "";
            upTime += timeSinceStart.toHoursPart() + "h";
            upTime += timeSinceStart.toMinutesPart() + "m";
            upTime += timeSinceStart.toSecondsPart() + "s";
            addPart(info, "upTime", upTime);

            // Calculate messages per minute
            int minsSinceStart = timeSinceStart.toMinutesPart();
            boolean minHasPassed = minsSinceStart != 0;
            double msgRate = (double) msgCount / (double) minsSinceStart;
            msgRate = roundTo2DP(msgRate);
            if (minHasPassed) {
                addPart(info, "msgRate", msgRate, "msg/min");
            }

            // Calculate XP estimate
            if (minHasPassed && msgRate >= 1.0) {
                int estXP = minsSinceStart * 20;
                addPart(info, "estXP", estXP, "XP");
            }

            // Give info to user
            String infoS = "";
            String sep = ", ";
            for (String infoPart : info) {
                infoS += infoPart + sep;
            }
            int end = infoS.length() - sep.length();
            infoS = infoS.substring(0, end);
            tGUI.code.notifyUser(infoS);
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
            bot.delay(keyDelay);

            // Releasing
            // Release key
            bot.keyRelease(keyCode);

            // Wait (to ensure shift is not still down after program ends)
            bot.delay(keyDelay);

            // Release shift if upper case
            if (isUpper) {
                bot.keyRelease(KeyEvent.VK_SHIFT);
            }

            // Wait
            bot.delay(keyDelay);
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
                startTime = LocalTime.now();
                Task task = new Task();
                task.run();
            }
        }, 4000);
    }

    /**
     * Add info part to list
     *
     * @param name
     * @param val
     * @param unit
     * @return
     */
    private void addPart(ArrayList<String> list, String name, Object val,
            String unit) {
        list.add(name + ": " + val + unit);
    }

    /**
     * Add info part to list with no unit
     *
     * @param name
     * @param val
     * @param unit
     * @return
     */
    private void addPart(ArrayList<String> list, String name, Object val) {
        addPart(list, name, val, "");
    }

    /**
     * Round a given double to 2 decimal places
     *
     * @param input
     * @return
     */
    private double roundTo2DP(double input) {
        return ((double) Math.round(input * 100) / 100);
    }
}
