package typer;

import common.BaseGUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JButton;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 * RoboTyper GUI
 *
 * @author David
 */
public class tGUI extends BaseGUI {

    // Program info
    public static final String NAME = "RoboTyper";
    public static final double VERSION = 1.0;

    // Color constants
    public static final Color RED = new Color(255, 51, 51);
    public static final Color GREEN = new Color(102, 255, 102);

    // Main objects
    public static tGUI gui;
    public static Code code;

    /**
     * Start
     */
    public static void start() {

        // Set look and feel
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            System.err.print(e.toString());
            System.exit(1);
        }

        // Create GUI
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                // Initialize GUI and tools
                gui = new tGUI();
                code = new Code();

                // Get screen info
                Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
                int SCREEN_W = (int) screen.getWidth();

                // Make adjustments to frame
                gui.setVisible(true);
                gui.setTitle(NAME + " V" + VERSION + " - by David C, 2019");
                int frameXPos = (SCREEN_W / 2) - (gui.getWidth() / 2);
                gui.setLocation(frameXPos, 39);
                gui.setResizable(false);
                gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
        });
    }

    /**
     * Creates GUI
     */
    public tGUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JMenuBar jMenuBar2 = new javax.swing.JMenuBar();
        javax.swing.JMenu jMenu3 = new javax.swing.JMenu();
        javax.swing.JMenu jMenu4 = new javax.swing.JMenu();
        javax.swing.JMenuItem jMenuItem1 = new javax.swing.JMenuItem();
        javax.swing.JMenuItem jMenuItem2 = new javax.swing.JMenuItem();
        javax.swing.JMenuItem jMenuItem3 = new javax.swing.JMenuItem();
        javax.swing.JMenuItem jMenuItem4 = new javax.swing.JMenuItem();
        javax.swing.JMenuItem jMenuItem6 = new javax.swing.JMenuItem();
        javax.swing.ButtonGroup buttonGroup1 = new javax.swing.ButtonGroup();
        javax.swing.JPanel panel = new javax.swing.JPanel();
        javax.swing.JLabel title = new javax.swing.JLabel();
        javax.swing.JButton startBut = new javax.swing.JButton();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        msgArea = new javax.swing.JTextArea();
        javax.swing.JLabel everyLabel = new javax.swing.JLabel();
        javax.swing.JLabel sendLabel = new javax.swing.JLabel();
        javax.swing.JTextField lowerMin = new javax.swing.JTextField();
        javax.swing.JLabel toLabel = new javax.swing.JLabel();
        javax.swing.JLabel minLabel = new javax.swing.JLabel();
        javax.swing.JTextField upperMin = new javax.swing.JTextField();
        javax.swing.JLabel outLabel = new javax.swing.JLabel();
        javax.swing.JCheckBox enterCheck = new javax.swing.JCheckBox();
        javax.swing.JMenuBar jMenuBar1 = new javax.swing.JMenuBar();

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        jMenuItem3.setText("jMenuItem3");

        jMenuItem4.setText("jMenuItem4");

        jMenuItem6.setText("jMenuItem6");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(820, 579));

        panel.setBackground(new java.awt.Color(255, 182, 81));
        panel.setForeground(new java.awt.Color(255, 255, 255));
        panel.setName("panel"); // NOI18N
        panel.setPreferredSize(new java.awt.Dimension(810, 575));

        title.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        title.setForeground(new java.awt.Color(0, 0, 0));
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("RoboTyper");
        title.setName("title"); // NOI18N

        startBut.setBackground(new java.awt.Color(102, 255, 102));
        startBut.setFont(new java.awt.Font("Dubai", 3, 28)); // NOI18N
        startBut.setForeground(new java.awt.Color(0, 0, 0));
        startBut.setText("Start ");
        startBut.setToolTipText("");
        startBut.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        startBut.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        startBut.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        startBut.setMargin(new java.awt.Insets(2, 2, 2, 2));
        startBut.setName("startBut"); // NOI18N
        startBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButAction(evt);
            }
        });

        msgArea.setBackground(new java.awt.Color(255, 255, 255));
        msgArea.setColumns(20);
        msgArea.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        msgArea.setForeground(new java.awt.Color(0, 0, 0));
        msgArea.setRows(5);
        msgArea.setText(":peace: , :Wario: , :star2: , :alien:, :TrapCard:, :angel: , \nNext MEE6 level here I come, \nYells Wa wa wa while putting up 3 fingers for each Wa, ");
        msgArea.setName("msgArea"); // NOI18N
        jScrollPane1.setViewportView(msgArea);

        everyLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        everyLabel.setForeground(new java.awt.Color(0, 0, 0));
        everyLabel.setText("every");
        everyLabel.setName("everyLabel"); // NOI18N

        sendLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        sendLabel.setForeground(new java.awt.Color(0, 0, 0));
        sendLabel.setText("Send the messages");
        sendLabel.setName("sendLabel"); // NOI18N

        lowerMin.setBackground(new java.awt.Color(255, 255, 255));
        lowerMin.setFont(new java.awt.Font("Cambria", 0, 24)); // NOI18N
        lowerMin.setForeground(new java.awt.Color(0, 0, 0));
        lowerMin.setText("3");
        lowerMin.setName("lowerMin"); // NOI18N

        toLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        toLabel.setForeground(new java.awt.Color(0, 0, 0));
        toLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        toLabel.setText("to");
        toLabel.setName("toLabel"); // NOI18N

        minLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        minLabel.setForeground(new java.awt.Color(0, 0, 0));
        minLabel.setText("minutes");
        minLabel.setName("minLabel"); // NOI18N

        upperMin.setBackground(new java.awt.Color(255, 255, 255));
        upperMin.setFont(new java.awt.Font("Cambria", 0, 24)); // NOI18N
        upperMin.setForeground(new java.awt.Color(0, 0, 0));
        upperMin.setText("5");
        upperMin.setName("upperMin"); // NOI18N

        outLabel.setBackground(new java.awt.Color(255, 255, 255));
        outLabel.setFont(new java.awt.Font("Candara", 0, 18)); // NOI18N
        outLabel.setForeground(new java.awt.Color(0, 0, 0));
        outLabel.setText("Output: ");
        outLabel.setName("outLabel"); // NOI18N

        enterCheck.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        enterCheck.setForeground(new java.awt.Color(0, 0, 0));
        enterCheck.setSelected(true);
        enterCheck.setText("Press Enter After Each Message");
        enterCheck.setName("enterCheck"); // NOI18N

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(264, 264, 264))
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(sendLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panelLayout.createSequentialGroup()
                            .addComponent(everyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lowerMin, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(7, 7, 7)
                            .addComponent(toLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(7, 7, 7)
                            .addComponent(upperMin, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(minLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(enterCheck))
                        .addComponent(outLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 763, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(startBut, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 739, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(sendLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lowerMin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(toLabel)
                    .addComponent(upperMin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(everyLabel)
                    .addComponent(minLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(enterCheck))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(startBut)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(outLabel)
                .addGap(8, 8, 8))
        );

        jMenuBar1.setBorder(null);
        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 803, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * When start button is pressed, process user input
     *
     * @param evt
     */
    private void startButAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButAction

        // Get button
        JButton but = ((JButton) evt.getSource());

        // If start wanted
        if (but.getText().contains("Start")) {

            // Process input
            if (code.processInput(msgArea)) {

                // If successful, set new text and color
                but.setText("Stop ");
                but.setBackground(RED);
            }

        } else {
            // Else if stop wanted
            // Stop typer
            code.stopTyper();

            // Set old text and color
            but.setText("Start ");
            but.setBackground(GREEN);
        }
    }//GEN-LAST:event_startButAction

    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JTextArea msgArea;
    // End of variables declaration//GEN-END:variables
}
