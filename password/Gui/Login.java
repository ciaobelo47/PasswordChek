package password.Gui;

import password.PasswordGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Menu del Login
 */
public class Login extends JFrame {
    private JFrame f = this;
    private final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private final int WIDTH = 600;
    private final int HEIGHT = 315;
    private final int POS_HOR;
    private final int POS_VER;
    private JButton ok;
    private JTextField txtField;
    private JLabel label;
    private ImageIcon image;
    private JLabel imageLabel;

    public Login() throws IOException {
        this.POS_HOR = Integer.valueOf((screen.width-WIDTH)/2);
        this.POS_VER = Integer.valueOf((screen.height-HEIGHT)/2);
        this.ok = new JButton("OK.");
        this.txtField = new JTextField();
        this.label = new JLabel("Password:");
        this.image = new ImageIcon(getClass().getClassLoader().getResource("Shut.png"));
        this.setTitle("Passwords - Login");
        this.setBounds(this.POS_HOR,this.POS_VER,WIDTH,HEIGHT);
        this.setLayout(new BorderLayout());
        this.add(okPanel(),"South");
        this.add(loginPanel(),"Center");
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    private JPanel loginPanel() {
        JPanel p = new JPanel(new GridLayout(4,1));
        label.setHorizontalAlignment(JLabel.CENTER);
        imageLabel = new JLabel(image);
        p.add(imageLabel);
        p.add(label);
        p.add(txtField);
        return p;

    }

    private JPanel okPanel() {
        JPanel p = new JPanel();
        p.add(ok);
        ActionListener al = lam -> PasswordGUI.login(txtField.getText(),f);
        ok.addActionListener(al);
        return p;
    }
}
