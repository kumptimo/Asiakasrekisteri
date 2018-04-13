/*
    EditClient is an operation to save edited JTable index data into new Client
    object and is then passed to UndoManager if needed for undo edit.
*/

package asiakasrekisteri;

import javax.swing.JButton;

public class EditClient {

    private Client editedClient;
    private Undoable undoable;
    private final String type = "uneditable";
    int rowIndex = -1;

    EditClient(UndoManager um, JButton undoButton) {

        String nameToSave, phoneToSave, emailToSave, miscInfToSave;

        if (GUI.clientList.getSelectedRow() != -1) {
            rowIndex = GUI.clientList.getSelectedRow();

            nameToSave = (String) GUI.clientList.getValueAt(rowIndex, 0);
            phoneToSave = (String) GUI.clientList.getValueAt(rowIndex, 1);
            emailToSave = (String) GUI.clientList.getValueAt(rowIndex, 2);
            miscInfToSave = (String) GUI.clientList.getValueAt(rowIndex, 3);

            editedClient = new Client(Integer.toString(rowIndex), nameToSave, phoneToSave, emailToSave, miscInfToSave);
            undoable = new Undoable(editedClient, type);
            um.addState(undoable);

            undoButton.setEnabled(true);
        }
    }
}
