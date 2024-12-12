package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.Controller;
import model.Books;
import model.CurrentUser;
import model.Users;

public class MenuBookList {
    public MenuBookList(Books book) {
        showMenuBookList(book);
    }

    public void showMenuBookList(Books book) {
        CurrentUser currentUser = CurrentUser.getInstance();
        Users user = currentUser.getUser();
        JFrame frame = new JFrame("Book Menu");
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton buttonTransactions = new JButton("Transactions");
        buttonTransactions.setBounds(100, 20, 200, 30);
        frame.add(buttonTransactions);

        JLabel labelBookId = new JLabel("id buku : " + book.getBook_id());
        labelBookId.setBounds(100, 70, 200, 20);
        frame.add(labelBookId);

        JLabel labelAuthor = new JLabel("Author : " + book.getAuthor());
        labelAuthor.setBounds(100, 100, 200, 20);
        frame.add(labelAuthor);

        JLabel labelGenre = new JLabel("Genre : " + book.getGenre());
        labelGenre.setBounds(100, 130, 200, 20);
        frame.add(labelGenre);

        JLabel labelPrice = new JLabel("Price : " + book.getPrice());
        labelPrice.setBounds(100, 160, 200, 20);
        frame.add(labelPrice);

        JButton buttonBuyBook = new JButton("Buy Books");
        buttonBuyBook.setBounds(100, 190, 200, 30);
        frame.add(buttonBuyBook);

        JButton buttonExit = new JButton("Exit Menu");
        buttonExit.setBounds(100, 230, 200, 30);
        frame.add(buttonExit);

        buttonTransactions.addActionListener(e -> {
            frame.dispose();
            new MenuTransactions();
        });

        buttonBuyBook.addActionListener(e -> {
            frame.dispose();
            boolean buyBook = Controller.buyBook(book.getBook_id(), user.getId());
            if (buyBook) {
                new MenuTransactions();
            } else {
                new MenuUser();
            }
        });

        buttonExit.addActionListener(e -> {
            frame.dispose();
            new MenuUser();
        });

        frame.setVisible(true);
    }
}
