package password;

import java.io.*;
import java.util.Scanner;

@Deprecated
public class Password {
    public static Scanner sc = new Scanner(System.in);
    public static String realPw;
    public static File DB = new File("./base_di_dati.txt");

    public static void password() throws IOException {
        if (!DB.isFile()){
            try {
                DB.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("1. Registrati");
        System.out.println("2. Accedi");
        System.out.println("0. Esci");
        int ch = sc.nextInt();

        if (ch == 1){
            signIn();
        } else if (ch == 2){
            logIn();
        } else {
            System.exit(104);
        }
    }

    private static void logIn() throws IOException {
        Scanner fr = new Scanner(DB);
        realPw = fr.nextLine();
        int i = 0;
        System.out.println("Inserisci la password:");
        String pw = sc.nextLine();
//        while (pw.isEmpty()) {
//            pw = sc.nextLine();
//        }
        i++;
        while (i != 5 && !realPw.equals(pw)) {
            System.out.println("ACCESSO NEGATO");
            System.out.println("Reinserisci la password:");
            pw = sc.nextLine();
            i++;

        }
        if (i != 5) {
            System.out.println("ACCESSO CONSENTITO");
        } else {
            System.out.println("Tentativi d'accesso esauriti");
        }

    }

    private static void signIn() throws IOException {
        FileWriter fw = new FileWriter(DB);
        System.out.println("Inserisci la nuova password:");
        String pw = sc.nextLine();
//        while (pw.isEmpty()){
//            pw = sc.nextLine();
//        }
        fw.write(pw);
        fw.close();
        System.out.println("PASSWORD AGGIORNATA CORRETTAMENTE");
        password();
    }
}
