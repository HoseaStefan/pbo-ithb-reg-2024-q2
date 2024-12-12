package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.*;

import model.CurrentUser;
import model.Users;
import controller.Controller;

public class MenuTransactions {
    public MenuTransactions(){
        showMenuTransaction();
    }

    public void showMenuTransaction(){

        CurrentUser currentUser = CurrentUser.getInstance();
        Users user = currentUser.getUser();

        JFrame frame = new JFrame("Transaction Menu");
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton buttonTransactions = new JButton("Transactions");
        buttonTransactions.setBounds(100, 20, 200, 30);
        frame.add(buttonTransactions);


        String[][] data = Controller.getTransactionData(user.getId());
 
        String[] columnNames = { "Id", "User Name", "Book Title", "Book Genre", "Price" };
        JTable tableTransaction = new JTable(data, columnNames);
        tableTransaction.setBounds(50, 60, 300, 150);
        frame.add(tableTransaction);

        JButton buttonExit = new JButton("Exit Menu");
        buttonExit.setBounds(100, 230, 200, 30);
        frame.add(buttonExit);

        buttonTransactions.addActionListener(e -> {
            frame.dispose();
            new MenuTransactions();
        });

        buttonExit.addActionListener(e -> {
            frame.dispose();
            new MenuUser();
        });

        frame.setVisible(true);
    }
}
