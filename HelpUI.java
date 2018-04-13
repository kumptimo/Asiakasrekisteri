/*
    HelpUI is window that displays the instructions to use the program. It
    has no operations whatsoever.
*/

package asiakasrekisteri;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class HelpUI {

    private final JFrame helpWindow = new JFrame("Ohje");
    private final JButton cancelButton = new JButton("Takaisin");
    private final JTextArea helpText;

    HelpUI() {

        Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY);

        cancelButton.setPreferredSize(new Dimension(120, 30));

        JPanel helpTextPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JPanel backGround = new JPanel();

        helpText = new JTextArea();
        helpText.setBorder(null);
        helpText.append("Lisää asiakas:\nLuo uuden asiakkaan, jolle "
                + "lisätään luomisen yhteydessä tiedot (asiakkaalla pitää olla "
                + "vähintään nimi).\n\n");
        helpText.append("Muokkaa asiakasta:\nMahdollistaa valitun rivin sisällön "
                + "muokkauksen. Valitse haluttu rivi hiirellä ja paina 'muokkaa'.\n\n");
        helpText.append("Poista asiakas:\nMahdollistaa valitun asiakkaan poistamisen."
                + "Valitse haluttu asiakas hiirellä ja paina 'Kyllä'.\n\n");
        helpText.append("Tallenna taulukon muutokset:\nTallentaa taulukon nykyisen tilan. "
                + "HUOM! Ei voi perua.\n\n");
        helpText.append("Peru viimeinen toiminto:\nPeruuttaa viimeisen asiakkaan "
                + "lisäyksen, poiston, tai muokkauksen.");

        helpText.setEditable(false);
        helpText.setLineWrap(true);
        helpText.setWrapStyleWord(true);
        helpText.setColumns(25);
        helpText.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        ActionListener aL1 = (ActionEvent e) -> {
            helpWindow.dispose();
        };
        cancelButton.addActionListener(aL1);
        cancelButton.setActionCommand("true");

        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        helpTextPanel.setLayout(gridbag);
        helpTextPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));

        GridBagLayout gridbag2 = new GridBagLayout();
        GridBagConstraints c2 = new GridBagConstraints();
        buttonPanel.setLayout(gridbag2);
        buttonPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        buttonPanel.setBackground(Color.pink);

        backGround.setPreferredSize(new Dimension(350, 430));
        backGround.setLayout(new BoxLayout(backGround, BoxLayout.Y_AXIS));
        backGround.setBorder(new EtchedBorder(EtchedBorder.LOWERED));

        c.insets = new Insets(10, 10, 10, 10);
        helpTextPanel.add(helpText, c);
        helpTextPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));

        c2.anchor = GridBagConstraints.LINE_END;
        c2.weightx = 1.0;
        c2.insets = new Insets(5, 0, 5, 2);

        buttonPanel.add(cancelButton, c2);
        buttonPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));

        backGround.add(helpTextPanel);
        backGround.add(buttonPanel);

        helpWindow.add(backGround);
        helpWindow.setResizable(false);
        helpWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        helpWindow.pack();
        helpWindow.setLocationRelativeTo(null);
        helpWindow.setVisible(true);
    }
}
