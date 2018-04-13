/*
    AddClientUI has all the UI elements and operations for adding a client
    to table from main window. It also passes an Undoable object to UndoManager.
*/

package asiakasrekisteri;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class AddClientUI {

    private final JFrame addClientFrame = new JFrame("Lisää asiakas");
    private final JLabel name, phone, email, miscInf;
    private final JButton saveButton, cancelButton;
    private JTextField nameField, phoneField, emailField;
    private final JTextArea miscInfArea;
    private Client addedClient;
    private Undoable undoable;
    private final String type = "deleteable";

    AddClientUI(UndoManager um, JButton undoButton) {

        name = new JLabel("Nimi:");
        phone = new JLabel("Puhelin:");
        email = new JLabel("Sähköposti:");
        miscInf = new JLabel("Lisätiedot:");

        ActionListener aL1 = new ActionListener() {

            private String nameToSave = "", phoneToSave = "", emailToSave = "", miscInfToSave = "";

            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                this.nameToSave = nameField.getText();
                this.phoneToSave = phoneField.getText();
                this.emailToSave = emailField.getText();
                this.miscInfToSave = miscInfArea.getText();
                addedClient = new Client(Integer.toString(Client.quantity), nameToSave, phoneToSave, emailToSave, miscInfToSave);
                undoable = new Undoable(addedClient, type);
                um.addState(undoable);

                if ("".equals(this.nameToSave)) {
                    //Do nothing
                } else {
                    GUI.model.addRow(new Object[]{nameToSave, phoneToSave, emailToSave, miscInfToSave});
                    Client.quantity++;
                    addClientFrame.dispose();
                    undoButton.setEnabled(true);
                }
            }
        };

        ActionListener aL2 = (ActionEvent e) -> {
            addClientFrame.dispose();
        };

        JPanel backGround = new JPanel();
        JPanel addClientPanel = new JPanel();
        JPanel buttonPanel = new JPanel();

        Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1);

        backGround.setLayout(new BoxLayout(backGround, BoxLayout.Y_AXIS));
        backGround.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        backGround.setBackground(Color.pink);

        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        addClientPanel.setLayout(gridbag);
        addClientPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));

        c.insets = new Insets(10, 10, 0, 10);
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 0;
        c.gridy = 0;
        addClientPanel.add(name, c);

        c.insets = new Insets(0, 10, 0, 10);
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        nameField = new JTextField(20);
        nameField.setBorder(border);
        addClientPanel.add(nameField, c);

        c.insets = new Insets(10, 10, 0, 10);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 2;
        addClientPanel.add(phone, c);

        c.insets = new Insets(0, 10, 0, 10);
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 3;
        phoneField = new JTextField(20);
        phoneField.setBorder(border);
        addClientPanel.add(phoneField, c);

        c.insets = new Insets(10, 10, 0, 10);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 4;
        addClientPanel.add(email, c);

        c.insets = new Insets(0, 10, 0, 10);
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 5;
        emailField = new JTextField(20);
        emailField.setBorder(border);
        addClientPanel.add(emailField, c);

        c.insets = new Insets(10, 10, 0, 10);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 6;
        addClientPanel.add(miscInf, c);

        c.insets = new Insets(0, 10, 10, 10);
        c.anchor = GridBagConstraints.CENTER;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 7;
        miscInfArea = new JTextArea(5, 20);
        miscInfArea.setLineWrap(true);

        miscInfArea.setBorder(border);
        addClientPanel.add(miscInfArea, c);

        GridBagLayout gridbag2 = new GridBagLayout();
        GridBagConstraints c2 = new GridBagConstraints();
        buttonPanel.setLayout(gridbag2);
        buttonPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        buttonPanel.setBackground(Color.pink);

        c2.fill = GridBagConstraints.BOTH;
        c2.insets = new Insets(5, 2, 5, 10);
        saveButton = new JButton("Tallenna");
        buttonPanel.add(saveButton, c2);
        saveButton.addActionListener(aL1);
        saveButton.setActionCommand("true");
        saveButton.setPreferredSize(new Dimension(120, 30));

        c2.insets = new Insets(5, 10, 5, 2);
        cancelButton = new JButton("Takaisin");
        buttonPanel.add(cancelButton, c2);
        cancelButton.addActionListener(aL2);
        cancelButton.setActionCommand("true");
        cancelButton.setPreferredSize(new Dimension(120, 30));

        backGround.add(addClientPanel);
        backGround.add(buttonPanel);

        addClientFrame.setResizable(false);
        addClientFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addClientFrame.add(backGround);
        addClientFrame.pack();
        addClientFrame.setLocationRelativeTo(null);
        addClientFrame.setVisible(true);
    }
}
