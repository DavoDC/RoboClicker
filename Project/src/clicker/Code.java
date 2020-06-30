package clicker;

import common.BaseGUI;
import java.awt.Component;
import java.awt.event.InputEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 * Contains methods to help with GUI processing
 *
 * @author David
 */
public class Code {

    // Clicker
    private Clicker clicker;

    /**
     * Process user input
     *
     * @return
     */
    public boolean processInput() {
        try {

            // Get GUI
            BaseGUI g = cGUI.gui;

            // Get user input
            int loX = g.getNumFromField("loX");
            int hiX = g.getNumFromField("hiX");
            int loY = g.getNumFromField("loY");
            int hiY = g.getNumFromField("hiY");
            int loTime = g.getNumFromField("loTime");
            int hiTime = g.getNumFromField("hiTime");

            // Process mouse button selected
            JComboBox jcb = (JComboBox) g.getComponentByName("mouseSelect");
            String rawMB = (String) jcb.getSelectedItem();
            int mCode = 0;
            switch (rawMB) {
                case "Left":
                    mCode = InputEvent.BUTTON1_DOWN_MASK;
                    break;
                case "Middle":
                    mCode = InputEvent.BUTTON2_DOWN_MASK;
                    break;
                case "Right":
                    mCode = InputEvent.BUTTON3_DOWN_MASK;
                    break;
                default:
                    mCode = InputEvent.BUTTON3_DOWN_MASK;
                    break;
            }

            // Put values into array
            int params[]
                    = {
                        loX, hiX, loY, hiY, loTime, hiTime, mCode
                    };

            // Check for negative, invalid input
            for (int curInt : params) {
                if (curInt < 0) {
                    throw new IllegalArgumentException();
                }
            }

            // Pass to clicker
            clicker = new Clicker(params);
            clicker.startDoing();

            // Finish successfully
            return true;

        } catch (Exception e) {

            // Open invalid input notification
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {

                    // Get main GUI info
                    int x = cGUI.gui.getX();
                    int y = cGUI.gui.getY();
                    int w = cGUI.gui.getWidth();
                    int h = cGUI.gui.getHeight();

                    // Create error popup                       
                    Error n = new Error();

                    // Adjust error popup
                    int nXpos = (x + w / 2) - (n.getWidth() / 2);
                    int nYpos = (y + h / 2) - (n.getHeight() / 2);
                    n.setLocation(nXpos, nYpos);
                    n.setVisible(true);
                    n.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                }
            });

            // Reset fields
            changeComps(new String[]{
                "loX", "hiX", "loY", "hiY",
                "loTime", "hiTime"
            }, (Component m)
                    -> {
                ((JTextField) m).setText("0");
            });

            // Finish with issue
            return false;
        }
    }

    /**
     * Act on a list of given components
     *
     * @param compNames
     * @param m Action
     */
    public void changeComps(String[] compNames, actionInterface m) {

        // Current component 
        Component curComp;

        // For all specified components
        for (String curName : compNames) {

            // Retrieve it
            curComp = cGUI.gui.getComponentByName(curName);

            // Do the action 
            m.change(curComp);
        }
    }

    /**
     * Used to do custom actions
     */
    interface actionInterface {

        public void change(Component component);
    }

    /**
     * Stop typer
     */
    public void stopClicker() {
        clicker.stopDoing();
    }
}
