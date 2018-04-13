/*
    Client is the class that's objects this program uses to save client data.
    It only has methods for getting field values, since only GUI.writeTableToFile()
    is needed to save them for later use and when program is running, the 
    values are stored inside DefaultTableModel of GUI.model.
*/

package asiakasrekisteri;

public class Client {

    private final String clientNumber;
    private final String name;
    private final String phoneNumber;
    private final String email;
    private final String miscInf;
    public static int quantity;

    public Client(String clientNumber,String name, String phoneNumber, String email, String miscInf) {
        
        this.clientNumber = clientNumber;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.miscInf = miscInf;
    }
    
    public String getClientNumber() {
        return this.clientNumber;
    }
    
    public String getName() {
        return this.name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getEmail() {
        return this.email;
    }

    public String getMiscInf() {
        return this.miscInf;
    }
    
}
