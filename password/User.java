package password;

import java.io.Serializable;

/**
 * Oggetto usato per salvare le password tramite {@link java.io.FileInputStream}
 */
public class User implements Serializable {

    private String username = "default";
    private String password;

    public User(String password) {
        this.password = password;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "[Debug] This is user: " + username + " with password: " + password;
    }
}
