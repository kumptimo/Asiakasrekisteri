/*
    GUI includes all highest level UI operations. It calls methods to handle
    file reading and writing, and all main window code is included in it. When
    user clicks buttons, GUI creates different other windows as needed.

    GUI also has three static fields, used to control the JTable accessibility,
    and its model holding the data.

    GUI uses boxlayout and gridbaglayout, as well as all the other windows in
    this program. UI elements are stored in three JPanels, of which one is
    a background for storing the other two. This is also the case with other
    windows as well. Largest amount of code is about positioning the UI
    elements, but GUI also has the JTable clientList, which is in the center
    of the whole program. All data is stored in the clientList when the program
    starts and it then goes in and out of that JTable element, or more precisely
    of its DefaultTableModel.
*/

package asiakasrekisteri;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.*;

public class GUI extends JPanel {

    static JTable clientList;
    private final JButton addClientButton, removeClientButton, editClientButton, 
            saveChangesButton, undoLastButton, helpButton, quitButton;
    private final String threeLines = "Peru\nviimeinen\ntoiminto", type = "uneditablecell";
    private final JScrollPane clientTextPanel;
    static DefaultTableModel model;
    static boolean isTableEditable;

    public GUI(JFrame paaIkkuna) throws IOException {

        addClientButton = new JButton("Lisää asiakas");
        removeClientButton = new JButton("Poista asiakas");
        editClientButton = new JButton("Muokkaa asiakasta");
        saveChangesButton = new JButton("Tallenna taulukon muutokset");
        undoLastButton = new JButton("<html>" + threeLines.replaceAll("\\n", "<br>") + "</html>");
        helpButton = new JButton("Ohje");
        quitButton = new JButton("Sulje");

        UndoManager uM = new UndoManager(undoLastButton);
        isTableEditable = false;

        clientList = readFile();
        model = (DefaultTableModel) clientList.getModel();

        clientList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        clientList.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            isTableEditable = false;
        });

        clientList.setFillsViewportHeight(true);
        ActionListener aL1 = (ActionEvent actionEvent) -> {
            AddClientUI addClientUI = new AddClientUI(uM, undoLastButton);
        };

        ActionListener aL2 = (ActionEvent actionEvent) -> {
            if (clientList.getSelectedRow() != -1) {
                RemoveClientUI asiakkaanMuokkausUI = new RemoveClientUI(uM, undoLastButton);
            }
        };

        ActionListener aL3 = (ActionEvent actionEvent) -> {
            if (clientList.getSelectedRow() != -1) {
                isTableEditable = true;
                EditClient editClientObject = new EditClient(uM, undoLastButton);
            }
        };

        ActionListener aL4 = (ActionEvent actionEvent) -> {
            SaveConfirmationUI scUI = new SaveConfirmationUI(this);
        };

        ActionListener aL5 = (ActionEvent actionEvent) -> {
            uM.undoAction();
        };

        ActionListener aL6 = (ActionEvent actionEvent) -> {
            HelpUI helpWindow = new HelpUI();
        };

        ActionListener aL7 = (ActionEvent actionEvent) -> {
            QuitConfirmationUI quitConfirmation = new QuitConfirmationUI(this);
        };
        
        JPanel buttonPanel = new JPanel();
        JPanel backGround = new JPanel();
        clientTextPanel = new JScrollPane(clientList);

        backGround.setLayout(new BoxLayout(backGround, BoxLayout.Y_AXIS));
        backGround.setBorder(new EtchedBorder(EtchedBorder.LOWERED));

        clientTextPanel.setPreferredSize(new Dimension(950, 450));
        clientTextPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        clientTextPanel.setBackground(Color.pink);

        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        buttonPanel.setLayout(gridbag);

        buttonPanel.setPreferredSize(new Dimension(950, 100));
        buttonPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        buttonPanel.setBackground(Color.pink);

        addClientButton.setPreferredSize(new Dimension(160, 30));
        removeClientButton.setPreferredSize(new Dimension(160, 30));
        editClientButton.setPreferredSize(new Dimension(160, 30));
        saveChangesButton.setPreferredSize(new Dimension(160, 30));

        c.gridheight = 1;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;

        c.insets = new Insets(0, 2, 2, 0);
        c.gridx = 0;
        c.gridy = 0;
        buttonPanel.add(addClientButton, c);
        addClientButton.addActionListener(aL1);
        addClientButton.setActionCommand("true");

        c.insets = new Insets(2, 2, 0, 0);
        c.gridx = 0;
        c.gridy = 1;
        buttonPanel.add(removeClientButton, c);
        removeClientButton.addActionListener(aL2);
        removeClientButton.setActionCommand("true");

        c.insets = new Insets(0, 5, 2, 0);
        c.gridx = 1;
        c.gridy = 0;
        buttonPanel.add(editClientButton, c);
        editClientButton.addActionListener(aL3);
        editClientButton.setActionCommand("true");

        c.insets = new Insets(2, 5, 0, 0);
        c.gridx = 1;
        c.gridy = 1;
        buttonPanel.add(saveChangesButton, c);
        saveChangesButton.addActionListener(aL4);
        saveChangesButton.setActionCommand("true");

        c.gridheight = 2;
        c.gridx = 3;
        c.gridy = 0;
        c.insets = new Insets(0, 5, 0, 40);
        buttonPanel.add(undoLastButton, c);

        undoLastButton.addActionListener(aL5);
        undoLastButton.setActionCommand("true");
        undoLastButton.setEnabled(false);

        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 4;
        c.gridy = 0;
        buttonPanel.add(helpButton, c);
        helpButton.addActionListener(aL6);
        helpButton.setActionCommand("true");

        c.insets = new Insets(0, 5, 0, 0);
        c.gridx = 5;
        c.gridy = 0;
        buttonPanel.add(quitButton, c);
        quitButton.addActionListener(aL7);

        backGround.add(clientTextPanel);
        backGround.add(buttonPanel);

        super.add(backGround);
    }

    /*
        readFile reads a textfile (if exists) and adds its holdings to table.
        If there is no file, it creates it. It also sets up the tablemodel
        used by the JTable that shows the clients. readFile uses a normal
        java array to create this table model and populate it with clients.
    */
    private JTable readFile() throws IOException {

        ArrayList<Client> clients = new ArrayList<>();
        int i = 0, j = 0;
        String name = "", phoneNumber = "", email = "", miscInf = "";

        String[] columnNames = {"Nimi", "Puhelin", "Sähköposti", "Lisätiedot"};

        File sourceFile = new File("asiakkaat.txt");
        sourceFile.createNewFile();

        BufferedReader inFile = new BufferedReader(new FileReader("asiakkaat.txt"));

        try {
            while ((name = inFile.readLine()) != null) {
                phoneNumber = inFile.readLine();
                email = inFile.readLine();
                miscInf = inFile.readLine();
                Client a = new Client(Integer.toString(Client.quantity), name, phoneNumber, email, miscInf);
                clients.add(a);
            }
            inFile.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("Luettavaa asiakas-tiedostoa ei loytynyt.");
        }

        String[][] data = new String[clients.size()][4];

        for (String[] data1 : data) {

            for (String data2 : data1) {
                data2 = clients.get(i).getName();
                data[i][j] = data2;
                data2 = clients.get(i).getPhoneNumber();
                data[i][j + 1] = data2;
                data2 = clients.get(i).getEmail();
                data[i][j + 2] = data2;
                data2 = clients.get(i).getMiscInf();
                data[i][j + 3] = data2;
            }
            j = 0;
            i = i + 1;
            Client.quantity++;
        }
        i = 0;
        JTable clientTable = new JTable(new DefaultTableModel(data, columnNames)) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return GUI.isTableEditable == true;
            }
        };

        clientTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        clientTable.getColumnModel().getColumn(1).setPreferredWidth(60);
        clientTable.getColumnModel().getColumn(2).setPreferredWidth(150);
        clientTable.getColumnModel().getColumn(3).setPreferredWidth(500);

        return clientTable;
    }

    /*
        writeTableToFile simply writes innards of JTable to file. Used in
        saving functions to keep the data safe until next time one decides
        to use the program.
    */
    public void writeTableToFile() throws IOException {

        int j, i;

        try (BufferedWriter out = new BufferedWriter(new FileWriter("asiakkaat.txt"))) {

            for (i = 0; i < GUI.model.getRowCount(); i++) {

                for (j = 0; j < GUI.model.getColumnCount(); j++) {
                    out.write((String) GUI.model.getValueAt(i, j));
                    out.newLine();
                }
            }
        }
    }
}
