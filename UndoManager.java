/*
    UndoManager has all the operations for undoing certain activities
    described in help window and official program instructions written in
    KLO_Kumpulainen_TIMO.pdf.
*/

package asiakasrekisteri;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

public class UndoManager {

    JButton undoButton;
    ArrayList<Undoable> unDoableList;
    DefaultTableModel newModel, temp2;

    UndoManager(JButton undoButton) {

        this.undoButton = undoButton;
        this.unDoableList = new ArrayList();
    }

    public void undoAction() {

        if ("deleteable".equals(this.unDoableList.get(0).getType())) {
            GUI.model.removeRow(Integer.parseInt(this.unDoableList.get(0).getClientNumber()));
            Client.quantity--;
        }

        if ("returnable".equals(this.unDoableList.get(0).getType())) {

            String nameToSave, phoneToSave, emailToSave, miscInfToSave, clientNumberToSave;

            int row = Integer.parseInt(this.unDoableList.get(0).getClientNumber());

            GUI.model.insertRow(row, new Object[]{
                nameToSave = this.unDoableList.get(0).getName(),
                phoneToSave = this.unDoableList.get(0).getPhoneNumber(),
                emailToSave = this.unDoableList.get(0).getEmail(),
                miscInfToSave = this.unDoableList.get(0).getMiscInf()
            });
            Client.quantity++;
        }

        if ("uneditable".equals(this.unDoableList.get(0).getType())) {
            GUI.model.removeRow(this.unDoableList.get(0).getRow());
            String nameToSave, phoneToSave, emailToSave, miscInfToSave, clientNumberToSave;

            int row = Integer.parseInt(this.unDoableList.get(0).getClientNumber());

            GUI.model.insertRow(row, new Object[]{
                nameToSave = this.unDoableList.get(0).getName(),
                phoneToSave = this.unDoableList.get(0).getPhoneNumber(),
                emailToSave = this.unDoableList.get(0).getEmail(),
                miscInfToSave = this.unDoableList.get(0).getMiscInf()
            });
        }

        undoButton.setEnabled(false);
        this.unDoableList.clear();
    }

    public void addState(Undoable undoable) {
        this.unDoableList.add(0, undoable);
    }
}
