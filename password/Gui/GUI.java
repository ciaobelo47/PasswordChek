package password.Gui;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Menu Principale
 */
public class GUI extends JFrame {

    private final JFrame f = this;
    private final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private final int WIDTH = 600;
    private final int HEIGHT = 155;
    private final int POS_HOR;
    private final int POS_VER;
    private JButton ok;
    private JRadioButton login;
    private JRadioButton signup;

    public GUI() throws IOException {
        this.POS_HOR = Integer.valueOf((screen.width - WIDTH)/2);
        this.POS_VER = Integer.valueOf((screen.height - HEIGHT)/2);
        this.ok = new JButton("Open");
        this.login = new JRadioButton("Login");
        this.signup = new JRadioButton("Sign Up");
        this.setTitle("Passwords");
        this.setBounds(this.POS_HOR,this.POS_VER,WIDTH,HEIGHT);
        this.setLayout(new BorderLayout());
        this.add(okPanel(),"South");
        this.add(mainPanel(),"Center");
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    private JPanel mainPanel() {
        JPanel p = new JPanel();
        ButtonGroup bg = new ButtonGroup();
        bg.add(login);
        bg.add(signup);
        p.add(login);
        p.add(signup);
        return p;
    }

    private JPanel okPanel() {
        JPanel p = new JPanel();
        p.add(ok);
        ok.addActionListener(lam -> {
            if (login.isSelected()){
                f.dispose();
                try {
                    new Login();
                } catch (IOException err) {
                    throw new RuntimeException(err);
                }
            } else if (signup.isSelected()) {
                f.dispose();
                try {
                    new signUp();
                } catch (IOException err) {
                    throw new RuntimeException(err);
                }
            }

        });
        return p;

    }


}
