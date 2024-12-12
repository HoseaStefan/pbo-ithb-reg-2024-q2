package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Users;
import controller.*;

public class FormLogin {
    public FormLogin(){
        showFormLogin();
    }

    public void showFormLogin(){
        JFrame frame = new JFrame("Login");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextField inputEmail = new JTextField();
        inputEmail.setBounds(100, 80, 200, 30);
        frame.add(inputEmail);

        JTextField inputPassword = new JTextField();
        inputPassword.setBounds(100, 110, 200, 30);
        frame.add(inputPassword);

        JButton buttonLogin = new JButton("Login");
        buttonLogin.setBounds(100, 140, 200, 30);
        frame.add(buttonLogin);

        buttonLogin.addActionListener(e -> {
            String email = inputEmail.getText();
            String password = inputPassword.getText();

            if (!email.isEmpty() && !password.isEmpty()) {
                Users verifying = Controller.verifyUser(email, password);
                if (verifying == null) {
                    JOptionPane.showMessageDialog(frame, "Salah username/password!");
                } else {
                    frame.dispose();
                    new MenuUser();
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Isi terlebih dahulu kawan!");
            }
        });
        
        frame.setVisible(true);
    }
}
