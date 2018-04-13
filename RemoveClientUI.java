/*
    RemoveClientUI is more of a dialog window, just asking the user to be
    absolutely sure to remove a client from the JTable. It also passes an
    Undoable object to UndoManager.
*/

package asiakasrekisteri;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class RemoveClientUI {

    private final JFrame removeClientFrame = new JFrame("Poista asiakas");
    private final JButton removeButton, cancelButton;
    private final JLabel doYouReally;
    int rowIndex = -1;
    private Client removedClient;
    private Undoable undoable;
    private final String type = "returnable";

    RemoveClientUI(UndoManager um, JButton undoButton) {

        JPanel warningTextPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JPanel backGround = new JPanel();
        doYouReally = new JLabel("Haluatko varmasti poistaa valitun asiakkaan?");

        ActionListener aL1 = (ActionEvent actionEvent) -> {

            String nameToSave, phoneToSave, emailToSave, miscInfToSave;

            if (GUI.clientList.getSelectedRow() != -1) {
                rowIndex = GUI.clientList.getSelectedRow();

                nameToSave = (String) GUI.clientList.getValueAt(rowIndex, 0);
                phoneToSave = (String) GUI.clientList.getValueAt(rowIndex, 1);
                emailToSave = (String) GUI.clientList.getValueAt(rowIndex, 2);
                miscInfToSave = (String) GUI.clientList.getValueAt(rowIndex, 3);

                removedClient = new Client(Integer.toString(rowIndex), nameToSave, phoneToSave, emailToSave, miscInfToSave);
                undoable = new Undoable(removedClient, type);
                um.addState(undoable);

                GUI.model.removeRow(rowIndex);
                Client.quantity--;
                removeClientFrame.dispose();
                undoButton.setEnabled(true);
            }
        };

        ActionListener aL2 = (ActionEvent actionEvent) -> {
            removeClientFrame.dispose();
        };

        backGround.setLayout(new BoxLayout(backGround, BoxLayout.Y_AXIS));
        backGround.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        backGround.setBackground(Color.pink);

        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        warningTextPanel.setLayout(gridbag);
        warningTextPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));

        GridBagLayout gridbag2 = new GridBagLayout();
        GridBagConstraints c2 = new GridBagConstraints();
        buttonPanel.setLayout(gridbag2);
        buttonPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        buttonPanel.setBackground(Color.pink);

        c.insets = new Insets(20, 10, 25, 10);
        c.gridwidth = 3;
        removeButton = new JButton("Kyllä");
        removeButton.addActionListener(aL1);
        removeButton.setActionCommand("true");
        removeButton.setPreferredSize(new Dimension(120, 30));

        cancelButton = new JButton("Takaisin");
        cancelButton.addActionListener(aL2);
        cancelButton.setActionCommand("true");
        cancelButton.setPreferredSize(new Dimension(120, 30));

        c2.weightx = 1.0;
        c2.anchor = GridBagConstraints.LINE_START;
        c2.fill = GridBagConstraints.BOTH;
        c2.gridwidth = 1;
        c2.insets = new Insets(5, 2, 5, 10);
        buttonPanel.add(removeButton, c2);

        c2.anchor = GridBagConstraints.LINE_END;
        c2.insets = new Insets(5, 10, 5, 2);
        
        warningTextPanel.add(doYouReally, c);

        buttonPanel.add(cancelButton, c2);

        backGround.add(warningTextPanel);
        backGround.add(buttonPanel);

        removeClientFrame.setResizable(false);
        removeClientFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        removeClientFrame.add(backGround);
        removeClientFrame.pack();
        removeClientFrame.setLocationRelativeTo(null);
        removeClientFrame.setVisible(true);
    }
}
