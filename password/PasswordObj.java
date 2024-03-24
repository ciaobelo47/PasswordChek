package password;

import java.io.Serializable;

/**
 * Oggetto usato per salvare le password tramite {@link java.io.FileInputStream}
 */
public class PasswordObj implements Serializable {

    private final String user = "default";
    private String password;

    public PasswordObj() {
    }

    public PasswordObj(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return password;
    }
}
