package view;

import javax.swing.*;

public class MainMenu {

    private JFrame frame;

    public MainMenu() {
        showMainMenu();
    }

    public void showMainMenu() {
        frame = new JFrame("Main Menu");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel labelWelcome = new JLabel("Welcome, Employee!");
        labelWelcome.setBounds(140, 100, 200, 20);
        frame.add(labelWelcome);

        JButton buttonRegister = new JButton("Register");
        buttonRegister.setBounds(70, 220, 100, 30);
        frame.add(buttonRegister);

        JButton buttonViewMemberList = new JButton("View Member List");
        buttonViewMemberList.setBounds(180, 220, 150, 30);
        frame.add(buttonViewMemberList);

        buttonRegister.addActionListener(e -> {
            frame.dispose();
            // new FormRegister();
        });

        
        frame.setVisible(true);
    }
}