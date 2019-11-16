
import java.awt.Component;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;

/**
 * Contains methods to help with GUI processing
 *
 * @author David Charkey
 */
public class Utils
{
    // Current GUI frame
    private JFrame frame;

    /**
     * Create a tool object
     *
     * @param frame
     */
    public Utils(JFrame frame)
    {
        // Initialize frame
        this.frame = frame;
    }

    /**
     * Retrieve a component by its name
     *
     * @param nameQuery
     * @return
     */
    public Component getComponentByName(String nameQuery)
    {
        // Return variable 
        Component comp = null;

        // Get all components
        JRootPane jrp = (JRootPane) frame.getComponents()[0];
        Container cp = (Container) jrp.getContentPane();
        JPanel jp = (JPanel) cp.getComponents()[0];
        Component[] parts = jp.getComponents();

        // Iterate over all parts
        for (Component curComp : parts)
        {
            // When name matches, save and stop
            if (nameQuery.equalsIgnoreCase(curComp.getName()))
            {
                comp = curComp;
                break;
            }
        }

        // Return component
        return comp;
    }

    /**
     * Retrieve the value of a given JTextField
     *
     * @param nameQuery
     * @return
     */
    public int getFieldValue(String nameQuery)
    {
        // Get text field
        JTextField field = (JTextField) getComponentByName(nameQuery);

        // Process input
        int value = Integer.parseInt(field.getText());

        // Return value found
        return value;
    }

    /**
     * Set the value of a given JTextField
     *
     * @param nameQuery
     * @param newText
     */
    public void setFieldValue(String nameQuery, String newText)
    {
        // Get text field
        JTextField field = (JTextField) getComponentByName(nameQuery);

        // Process input
        field.setText(newText);
    }

    /**
     * Act on a list of given components
     *
     * @param compNames
     * @param m Action
     */
    public void changeComps(String[] compNames, actionInterface m)
    {
        // Current component 
        Component curComp;
        
        // For all specified components
        for (String curName : compNames)
        {
            // Retrieve it
            curComp = getComponentByName(curName);
            
            // Do the action 
            m.change(curComp);
        }
    }

    /**
     * Used to do custom actions
     */
    interface actionInterface
    {
        public void change(Component component);
    }

}
