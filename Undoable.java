/*
    Undoable is a simple class consisting of one Client object and the type
    of its undoabiliness. In general, it's more of an instruction to
    UndoManager how to undo this kind of operation.
*/

package asiakasrekisteri;

public class Undoable {
    
    private final Client client;
    private final String unDoableType;
    
    Undoable(Client c, String unDoableType) {
        this.client = c;
        this.unDoableType = unDoableType;
    }
    
    public String getType() {
        return this.unDoableType;
    }
    
    public int getRow() {
        int row = Integer.parseInt(this.client.getClientNumber());
        return row;
    }
    
    public String getClientNumber() {
        return this.client.getClientNumber();
    }
    
    public String getName() {
        return this.client.getName();
    }

    public String getPhoneNumber() {
        return this.client.getPhoneNumber();
    }

    public String getEmail() {
        return this.client.getEmail();
    }

    public String getMiscInf() {
        return this.client.getMiscInf();
    }
}
