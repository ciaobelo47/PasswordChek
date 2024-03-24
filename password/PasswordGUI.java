package password;

import password.Gui.ErrorPanes;
import password.Gui.SuccessPane;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class PasswordGUI {
    public static PasswordObj realPw;
    public static File DB = new File("./database.dat");
    public static int i = 0;

    /**
     * Metodo logico usato per il controllo della password
     * @param pw Stringa in entrata per essere controllata
     * @param f JFrame per generare {@link ErrorPanes}
     */
    public static void login(String pw, JFrame f) {
        System.out.println(pw);
        if (!DB.isFile()) {
            ErrorPanes.fileNotFoundPane("Impossibile Trovare Il Database", f);
            return;
        }
        if (pw.isEmpty()) {
            ErrorPanes.pwEmptyErr(f);
            return;
        }
        try {
            FileInputStream fis = new FileInputStream(DB);
            ObjectInputStream ois = new ObjectInputStream(fis);

            realPw = (PasswordObj) ois.readObject();
            i++;
            if (realPw.toString().equals(pw)) {
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
     * @param password Password da associare al PasswordObj
     * @param f JFrame per generare ErrorPanes
     */
    public static void signUp(String password, JFrame f) {
        System.out.println(password);
        PasswordObj pw = new PasswordObj(password);
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

            PasswordObj object = (PasswordObj) ois.readObject();

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
