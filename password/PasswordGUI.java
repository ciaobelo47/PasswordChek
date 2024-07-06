package password;

import password.Gui.ErrorPanes;
import password.Gui.SuccessPane;

import javax.swing.*;
import java.io.*;

public class PasswordGUI {
    public static User realPw;
    public static File DB = new File("./database.dat");
    public static int i = 0;

    /**
     * Metodo logico usato per il controllo della password
     * @param pw Stringa in entrata per essere controllata
     * @param f JFrame per generare {@link ErrorPanes}
     */
    public static void login(String usr, String pw, JFrame f) {
        User u = new User(usr, pw);

        if (!DB.isFile()) {
            ErrorPanes.fileNotFoundPane("Impossibile Trovare Il Database", f);
            return;
        }
        if (usr.isEmpty()) {
            ErrorPanes.userEmptyErr(f);
            return;
        }
        if (pw.isEmpty()) {
            ErrorPanes.pwEmptyErr(f);
            return;
        }
        try {
            FileInputStream fis = new FileInputStream(DB);
            ObjectInputStream ois = new ObjectInputStream(fis);

            realPw = (User) ois.readObject();
            i++;
            if (realPw.toString().equals(u.toString())) {
                if (i != 5) {
                    System.out.println("ACCESSO CONSENTITO");
                    fis.close();
                    ois.close();
                    SuccessPane.accessGranted(f);
                } else {
                    ErrorPanes.accessDenied(f);
                }

            } else {
                if (i != 5) {
                    ErrorPanes.wrongPw(f);
                    return;
                } else {
                    ErrorPanes.accessDenied(f);
                }

            }
        } catch (IOException | ClassNotFoundException err) {
            ErrorPanes.fileNotFoundPane(err.getMessage(), f);
            throw new RuntimeException(err);
        }
    }

    /**
     * Metodo logico usato per la scrittura di una password in un file
     * @param password Password da associare al User
     * @param f JFrame per generare ErrorPanes
     */
    public static void signUp(String username, String password, JFrame f) {
        System.out.println("[Debug] Username recieved: " + username);
        System.out.println("[Debug] Password recieved: " + password);
        User pw = new User(username, password);
        if (password.isEmpty()) {
            ErrorPanes.pwEmptyErr(f);
            return;
        }
        if (!DB.isFile()) {
            try {
                DB.createNewFile();
            } catch (IOException err) {
                ErrorPanes.errorPane(err.getMessage(),f);
                throw new RuntimeException(err);
            }
        }
        try {
            FileOutputStream fos = new FileOutputStream(DB);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(pw);
            oos.close();
            fos.close();

            FileInputStream fis = new FileInputStream(DB);
            ObjectInputStream ois = new ObjectInputStream(fis);

            User object = (User) ois.readObject();

            System.out.println(object.toString());

            fis.close();
            ois.close();

            if (object.toString().equals(pw.toString())){
                SuccessPane.fileCorrectlyWritten(f);
            } else {
                ErrorPanes.fileNotWrittenCorrectly(f);
            }
        } catch (IOException | ClassNotFoundException e) {
            ErrorPanes.errorPane(e.getMessage(),f);
            throw new RuntimeException(e);
        }

    }

}
