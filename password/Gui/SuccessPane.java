package password.Gui;

import javax.swing.*;
import java.io.IOException;

/**
 * Classe usata per generare {@link JOptionPane} per segnalare successi
 */
public class SuccessPane {

    public static void accessGranted(JFrame f) throws IOException {
        JOptionPane.showMessageDialog(f,"Accesso Consentito! Benvenuto!", "Accesso Consentito!",JOptionPane.INFORMATION_MESSAGE);
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec("mspaint");
        f.dispose();
    }

    public static void fileCorrectlyWritten(JFrame f) {
        JOptionPane.showMessageDialog(f,"Password Impostata Correttamente","Success",JOptionPane.INFORMATION_MESSAGE);
        f.dispose();
        try {
            new GUI();
        } catch (IOException e) {
            ErrorPanes.errorPane(e.getMessage(), f);
            throw new RuntimeException(e);
        }
    }
}
