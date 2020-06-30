package typer;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 * Back end code for typer
 *
 * @author David
 */
public class Code {

    // Forbidden characters
    private final String forbidden = "`~!@#$%^&*()\"";

    // Error start
    private final String err = "Error - ";

    // Typer
    private Typer typer;

    /**
     * Starting method to retrieve and process input
     *
     * @param msgArea Must be passed as getName() is always null!
     * @return True if was successful
     */
    public boolean processInput(JTextArea msgArea) {

        // Notify
        System.out.println("");
        System.out.println("\nNEW INPUT");
        notifyUser("Attempting to process user input... (to make new Typer)");

        // Retrieve user input
        // User input
        String[] messages;
        int loSec;
        int hiSec;
        int keyDelay;
        try {

            // Retrieve messages
            messages = getMessages(msgArea);

            // Get bounds values
            loSec = getSecVal("lo");
            hiSec = getSecVal("hi");

            // If high is not greater/equal than low
            if (!(loSec <= hiSec)) {

                // Notify
                String msg = "Upper bound should be greater";
                msg += " than/equal to the lower bound";
                throwErr(msg);
            }

            // Get key delay
            keyDelay = getKeyDelay();

        } catch (NumberFormatException e) {

            // Notify about number error
            notifyUser(err + "One of the numbers was invalid");

            // Do not process further
            return false;

        } catch (IllegalArgumentException e) {

            // Notify about our errors
            notifyUser(e.getMessage());

            // Do not process further
            return false;
        }

        // Get enter check
        JCheckBox enterCheck;
        enterCheck = (JCheckBox) tGUI.gui.getComponentByName("enterCheck");
        boolean enterWanted = enterCheck.isSelected();

        // Notify user
        notifyUser("Input was successfully processed! Typing starting in 4s");

        // Display actual messsages used
        System.out.println("\nInternal: Actual messages that will be used: ");
        for (String curMsg : messages) {
            System.out.println(" -" + curMsg);
        }
        System.out.println("");

        // Create typer and start
        typer = new Typer(messages, loSec, hiSec,
                enterWanted, keyDelay);
        typer.startDoing();

        // Finish succesfully
        return true;
    }

    /**
     * Extract messages from message text area
     *
     * @param msgArea
     * @return
     */
    private String[] getMessages(JTextArea msgArea) {

        // Get all messages
        String allMsg = msgArea.getText().trim();

        // If there are no messages
        if (allMsg.equals("")) {

            // Notify
            throwErr("No comma-separated messages found");
        }

        // Split messages using a comma
        String[] messages = allMsg.split(",");

        // Refine messages
        for (int i = 0; i < messages.length; i++) {

            // Trim
            messages[i] = messages[i].trim();

            // Remove forbidden characters
            String regEx = "[" + forbidden + "]";
            messages[i] = messages[i].replaceAll(regEx, "");
        }

        // Return messages
        return messages;
    }

    /**
     * Get second value
     *
     * @param type lo OR hi
     * @return
     */
    private int getSecVal(String type) {

        // Get minute value and check
        int min = tGUI.gui.getNumFromField(type + "Min");
        checkTimeVal(min, false);

        // Get second value and check
        int sec = tGUI.gui.getNumFromField(type + "Sec");
        checkTimeVal(sec, true);

        // If both values of the bound are zero
        if (min == 0 && sec == 0) {

            // Notify
            throwErr("A bound cannot have a zero value");
        }

        // Convert minutes to seconds and add to seconds
        sec += min * 60;

        // Return final value
        return sec;
    }

    /**
     * Get key delay
     *
     * @return
     */
    private int getKeyDelay() {

        // Get value
        int keyDelay = tGUI.gui.getNumFromField("keyDelay");

        // Check value
        if (keyDelay == 0 || keyDelay < 0) {

            // Notify
            throwErr("The key delay cannot be zero or negative");
        }

        // Return value
        return keyDelay;
    }

    /**
     * Throw an exception is the given time value is incorrect
     *
     * @param num
     */
    private void checkTimeVal(int num, boolean isSec) {

        // If number is negative
        if (num < 0) {

            // Notify
            throwErr("Enter time values that are zero or positive");

        } else if (isSec && num > 60) {

            // Else if is a second value greater than 60
            throwErr("The second values must be less than/equal to 60");
        }
    }

    /**
     * Give user a string
     *
     * @param msg
     */
    public void notifyUser(String msg) {

        // Get out label
        JLabel out;
        out = (JLabel) tGUI.gui.getComponentByName("outLabel");

        // Get full message
        String full = "Output: " + msg;

        // Give message
        out.setText(full);

        // Print message
        System.out.println(full);
    }

    /**
     * Throw a custom error
     *
     * @param msg
     */
    private void throwErr(String msg) {
        throw new IllegalArgumentException(err + msg);
    }

    /**
     * Stop typer
     */
    public void stopTyper() {
        typer.stopDoing();
    }
}
