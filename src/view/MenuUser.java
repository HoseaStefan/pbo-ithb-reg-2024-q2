package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import main.Main;
import model.Books;
import model.CurrentUser;
import controller.Controller;

public class MenuUser {
    public MenuUser() {
        showMenuUser();
    }

    public void showMenuUser() {
        JFrame frame = new JFrame("User Menu");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton buttonMenuBookList = new JButton("Menu Book List");
        buttonMenuBookList.setBounds(100, 80, 200, 30);
        frame.add(buttonMenuBookList);

        JButton buttonTransactions = new JButton("Transactions");
        buttonTransactions.setBounds(100, 110, 200, 30);
        frame.add(buttonTransactions);

        JButton buttonLogout = new JButton("Logout");
        buttonLogout.setBounds(100, 140, 200, 30);
        frame.add(buttonLogout);

        buttonMenuBookList.addActionListener(e -> {
            frame.dispose();
            String input = JOptionPane.showInputDialog(frame, "Masukan id buku", "Input",
                    JOptionPane.QUESTION_MESSAGE);
            if (input != null) {
                Books book = Controller.getBookData(input);
                if (book != null) {
                    new MenuBookList(book);
                }
            } else {
                new MenuUser();
            }
        });

        buttonTransactions.addActionListener(e -> {
            frame.dispose();
            new MenuTransactions();
        });

        buttonLogout.addActionListener(e -> {
            frame.dispose();
            CurrentUser.getInstance().clearUser();
            new MainMenu();
        });

        frame.setVisible(true);
    }
}
