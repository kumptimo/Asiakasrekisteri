/*
    QuitConfirmationUI shows window to user just to confirm if they need to
    save the data once more to prevent it from getting lost.
*/

package asiakasrekisteri;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class QuitConfirmationUI {

    private final JFrame quitConfirmationFrame = new JFrame("Sulje");
    private final JButton quitAndSaveButton, quitButton, cancelButton;
    private final JLabel doYouReally;

    QuitConfirmationUI(GUI mainGUI) {

        JPanel warningTextPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JPanel backGround = new JPanel();
        doYouReally = new JLabel("Haluatko tallentaa ennen sulkemista?");

        ActionListener aL1 = (ActionEvent actionEvent) -> {
            try {
                mainGUI.writeTableToFile();
            } catch (IOException ex) {
                Logger.getLogger(QuitConfirmationUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            Runtime.getRuntime().exit(0);
        };

        ActionListener aL2 = (ActionEvent actionEvent) -> {
            Runtime.getRuntime().exit(0);
        };

        ActionListener aL3 = (ActionEvent actionEvent) -> {
            quitConfirmationFrame.dispose();
        };

        backGround.setLayout(new BoxLayout(backGround, BoxLayout.Y_AXIS));
        backGround.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        backGround.setPreferredSize(new Dimension(440, 150));

        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        warningTextPanel.setLayout(gridbag);
        warningTextPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));

        GridBagLayout gridbag2 = new GridBagLayout();
        GridBagConstraints c2 = new GridBagConstraints();
        buttonPanel.setLayout(gridbag2);
        buttonPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        buttonPanel.setBackground(Color.pink);

        quitAndSaveButton = new JButton("Tallenna ja sulje");
        quitAndSaveButton.addActionListener(aL1);
        quitAndSaveButton.setActionCommand("true");

        quitButton = new JButton("Sulje");
        quitButton.addActionListener(aL2);
        quitButton.setActionCommand("true");

        cancelButton = new JButton("Takaisin");
        cancelButton.addActionListener(aL3);
        cancelButton.setActionCommand("true");
        
        quitAndSaveButton.setPreferredSize(new Dimension(80, 30));
        quitButton.setPreferredSize(new Dimension(40, 30));
        cancelButton.setPreferredSize(new Dimension(40, 30));

        c.insets = new Insets(20, 10, 25, 10);
        c.gridwidth = 3;
        warningTextPanel.add(doYouReally, c);

        c2.fill = GridBagConstraints.BOTH;
        
        c2.gridx = 0;
        c2.gridy = 0;
        c2.gridwidth = 2;
        c2.insets = new Insets(5, 2, 2, 2);
        buttonPanel.add(quitAndSaveButton, c2);
        
        c2.gridx = 0;
        c2.gridy = 1;
        c2.gridwidth = 1;
        c2.weightx = 0.5;
        c2.insets = new Insets(2, 2, 5, 0);
        buttonPanel.add(quitButton, c2);
        
        c2.gridx = 1;
        c2.gridy = 1;
        c2.gridwidth = 1;
        c2.weightx = 0.5;
        c2.insets = new Insets(2, 5, 5, 2);
        buttonPanel.add(cancelButton, c2);

        backGround.add(warningTextPanel);
        backGround.add(buttonPanel);
        
        quitConfirmationFrame.setResizable(false);
        quitConfirmationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        quitConfirmationFrame.add(backGround);
        quitConfirmationFrame.pack();
        quitConfirmationFrame.setLocationRelativeTo(null);
        quitConfirmationFrame.setVisible(true);
    }
}
