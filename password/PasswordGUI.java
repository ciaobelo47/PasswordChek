package password;

import password.Gui.ErrorPanes;
import password.Gui.SuccessPane;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PasswordGUI {
    public static String realPw;
    public static File DB = new File("./database.txt");
    public static int i = 0;

    public static void login(String pw, JFrame f) {
        System.out.println(pw);
        if (!DB.isFile()) {
            ErrorPanes.fileNotFoundPane("Impossibile Trovare Il Database", f);
            return;
        }
        try {
            if (pw.isEmpty()) {
                ErrorPanes.pwEmptyErr(f);
                return;
            }
            Scanner fr = new Scanner(DB);
            realPw = fr.nextLine();
            i++;
            if (realPw.equals(pw)) {
                if (i != 5) {
                    System.out.println("ACCESSO CONSENTITO");
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
        } catch (IOException err) {
            ErrorPanes.fileNotFoundPane(err.getMessage(), f);
            throw new RuntimeException(err);
        }
    }

    public static void signUp(String pw, JFrame f) {
        System.out.println(pw);
        if (pw.isEmpty()) {
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
            FileWriter fw = new FileWriter(DB);
            fw.write(pw);
            fw.close();
            Scanner fr = new Scanner(DB);
            if (fr.nextLine().equals(pw)){
                SuccessPane.fileCorrectlyWritten(f);

            }
        } catch (IOException e) {
            ErrorPanes.errorPane(e.getMessage(),f);
            throw new RuntimeException(e);
        }

    }

}
