/*
    SaveConfirmationUI asks the user if they are really sure if they want to
    save the data. In case of missclicks and such.
*/

package asiakasrekisteri;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.border.*;

public class SaveConfirmationUI {

    private final JFrame saveConfirmation = new JFrame("Tallenna muutokset");
    private final JButton cancelButton = new JButton("Takaisin");
    private final JButton saveButton = new JButton("Tallenna");
    private final JLabel doYouReally;

    SaveConfirmationUI(GUI mainGUI) {

        doYouReally = new JLabel("Haluatko varmasti tallentaa muutokset?");

        cancelButton.setPreferredSize(new Dimension(120, 30));
        saveButton.setPreferredSize(new Dimension(120, 30));

        JPanel warningTextPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JPanel backGround = new JPanel();

        ActionListener aL1 = (ActionEvent e) -> {
            try {
                mainGUI.writeTableToFile();
                saveConfirmation.dispose();
            } catch (IOException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        };

        ActionListener aL2 = (ActionEvent e) -> {
            saveConfirmation.dispose();
        };
        saveButton.addActionListener(aL1);
        saveButton.setActionCommand("true");

        cancelButton.addActionListener(aL2);
        cancelButton.setActionCommand("true");

        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        warningTextPanel.setLayout(gridbag);
        warningTextPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));

        GridBagLayout gridbag2 = new GridBagLayout();
        GridBagConstraints c2 = new GridBagConstraints();
        buttonPanel.setLayout(gridbag2);
        buttonPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        buttonPanel.setBackground(Color.pink);

        backGround.setLayout(new BoxLayout(backGround, BoxLayout.Y_AXIS));
        backGround.setBorder(new EtchedBorder(EtchedBorder.LOWERED));

        c.insets = new Insets(20, 10, 25, 10);
        warningTextPanel.add(doYouReally, c);

        c2.anchor = GridBagConstraints.LINE_START;
        c2.weightx = 1.0;
        c2.insets = new Insets(5, 2, 5, 0);
        buttonPanel.add(saveButton, c2);

        c2.anchor = GridBagConstraints.LINE_END;
        c2.weightx = 1.0;
        c2.insets = new Insets(5, 0, 5, 2);
        buttonPanel.add(cancelButton, c2);

        backGround.add(warningTextPanel);
        backGround.add(buttonPanel);

        saveConfirmation.add(backGround);
        saveConfirmation.setResizable(false);
        saveConfirmation.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        saveConfirmation.pack();
        saveConfirmation.setLocationRelativeTo(null);
        saveConfirmation.setVisible(true);
    }

}
