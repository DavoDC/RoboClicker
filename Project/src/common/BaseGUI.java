package common;

import java.awt.Component;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;

/**
 * Custom JFrame
 *
 * @author David
 */
public class BaseGUI extends JFrame {

    /**
     * Retrieve a component by its name
     *
     * @param nameQuery
     * @return
     */
    public Component getComponentByName(String nameQuery) {

        // Return variable 
        Component comp = null;

        // Get all components
        JRootPane jrp = (JRootPane) this.getComponents()[0];
        Container cp = (Container) jrp.getContentPane();
        JPanel jp = (JPanel) cp.getComponents()[0];
        Component[] parts = jp.getComponents();

        // Iterate over all parts
        for (Component curComp : parts) {
            
            // When name matches, save and stop
            if (nameQuery.equalsIgnoreCase(curComp.getName())) {
                comp = curComp;
                break;
            }
        }

        // Return component
        return comp;
    }

    /**
     * Retrieve the numerical value of a given JTextField
     *
     * @param nameQuery
     * @return
     */
    public int getNumFromField(String nameQuery) {

        // Get text field
        JTextField field = (JTextField) getComponentByName(nameQuery);

        // Convert text to integer
        int value = Integer.parseInt(field.getText());

        // Return value found
        return value;
    }

    /**
     * Set the numerical value of a given JTextField
     *
     * @param nameQuery
     * @param newText
     */
    public void setNumFieldVal(String nameQuery, String newText) {

        // Get text field
        JTextField field = (JTextField) getComponentByName(nameQuery);

        // Process input
        field.setText(newText);
    }
}
