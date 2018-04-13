package asiakasrekisteri;

import java.io.*;
import java.util.logging.*;
import javax.swing.JFrame;

/*
    Main-method calls showUI
*/
public class Asiakasrekisteri {

    public static void main(String[] args) throws IOException {

        javax.swing.SwingUtilities.invokeLater(() -> {

            try {
                showUI();
            } catch (IOException ex) {
                Logger.getLogger(Asiakasrekisteri.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    /*
        showUI sets JFrame and content pane for GUI
    */
    private static void showUI() throws IOException {

        JFrame mainWindow = new JFrame("Asiakasrekisteri");
        mainWindow.setResizable(false);
        mainWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //prevents user from closing without saving
        GUI mainGUI = new GUI(mainWindow);
        mainGUI.setOpaque(true);
        mainWindow.setContentPane(mainGUI);
        mainWindow.pack();
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);
    }
}
