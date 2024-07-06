package password.Gui;

import javax.swing.*;
import java.io.IOException;

/**
 * Classe usata per generare {@link JOptionPane} per segnalare errori
 */
public class ErrorPanes {

    public static void errorPane(String err, JFrame f) {
        JOptionPane.showMessageDialog(f,err,"Error",JOptionPane.ERROR_MESSAGE);
    }
    public static void fileNotFoundPane(String err, JFrame f) {
        JOptionPane.showMessageDialog(f,err + "\n" + "Esegui prima il signUp","File Not Found",JOptionPane.ERROR_MESSAGE);
        f.dispose();
        try {
            new GUI();
        } catch (IOException e) {
            ErrorPanes.errorPane(e.getMessage(), f);
            throw new RuntimeException(e);
        }
    }

    public static void pwEmptyErr(JFrame f) {
        JOptionPane.showMessageDialog(f,"Inserisci una password","Empty Password",JOptionPane.ERROR_MESSAGE);
    }
    public static void userEmptyErr(JFrame f) {
        JOptionPane.showMessageDialog(f, "Inserisci un username", "Empty Username", JOptionPane.ERROR_MESSAGE);
    }
    public static void wrongPw(JFrame f) {
        JOptionPane.showMessageDialog(f,"Password Errata. Riprova.","Password Errata",JOptionPane.ERROR_MESSAGE);
    }

    public static void accessDenied(JFrame f) {
        JOptionPane.showMessageDialog(f,"Hai esaurito i tentativi della password. Il programma verrà terminato","Tentativi Esauriti",JOptionPane.ERROR_MESSAGE);
        f.dispose();
    }

    public static void fileNotWrittenCorrectly(JFrame f) {
        JOptionPane.showMessageDialog(f,"File Non Correttamente Salvato","File Error",JOptionPane.ERROR_MESSAGE);
    }

}
