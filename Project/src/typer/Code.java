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

    // Chars that removed
    private final String removedChars = "`~!@#$%^&*()\"";

    // Typer
    private Typer typer;

    /**
     * Starting method to retrieve and process input
     *
     * @param msgArea Must be passed as getName() is always null!
     * @return True if was successful
     */
    public boolean processInput(JTextArea msgArea) {

        // Retrieve user input
        // User input
        String[] messages;
        int lowerMin;
        int upperMin;
        try {

            // Retrieve all messages
            String allMsg = msgArea.getText().trim();

            // If there are no messages
            if (allMsg.equals("")) {

                // Notify
                throwErr("No comma-separated messages");
            }

            // Split and refine messages
            messages = allMsg.split(",");
            for (int i = 0; i < messages.length; i++) {
                messages[i] = messages[i].trim();
                String regEx = "[" + removedChars + "]";
                messages[i] = messages[i].replaceAll(regEx, "");
            }

            // Get lower bound and check
            lowerMin = tGUI.gui.getNumFromField("lowerMin");
            checkNumber(lowerMin);

            // Get upper bound and check
            upperMin = tGUI.gui.getNumFromField("upperMin");
            checkNumber(upperMin);

            // If upper minute is not greater/equal than lower minute
            if (!(lowerMin <= upperMin)) {

                // Notify
                throwErr("Second number should be greater/equal");
            }

        } catch (NumberFormatException e) {

            // Notify about number error
            notifyUser("One of the numbers was invalid");

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

        // Display actual messsages used
        System.out.println("\nActual messages used: ");
        for (String curMsg : messages) {
            System.out.println(" -" + curMsg);
        }
        System.out.println("");

        // Notify user
        notifyUser("Input was successfully processed");

        // Create typer and start
        typer = new Typer(messages, lowerMin, upperMin, enterWanted);
        typer.startDoing();

        // Finish succesfully
        return true;
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

        // Give message
        out.setText("Output: " + msg);
    }

    /**
     * Throw an exception is the given number is zero/negative
     *
     * @param num
     */
    private void checkNumber(int num) {

        // If number is zero or negative
        if (num == 0 || num < 0) {
            throwErr("Enter positive, non-zero numbers");
        }
    }

    /**
     * Throw a custom error
     *
     * @param msg
     */
    private void throwErr(String msg) {
        throw new IllegalArgumentException(msg);
    }

    /**
     * Stop typer
     */
    public void stopTyper() {
        typer.stopDoing();
    }
}
