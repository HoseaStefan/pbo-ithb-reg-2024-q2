package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.CurrentUser;
import model.Users;
import model.Books;

public class Controller {
    static DatabaseHandler conn = new DatabaseHandler();

    public static Users verifyUser(String username, String password) {
        try {
            conn.connect();
            String query = "SELECT * FROM users WHERE email = ? AND password = ?";
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Users loggedInUser = new Users(rs.getInt("user_id"), rs.getString("name"), rs.getString("email"),
                        rs.getString("password"));

                if (loggedInUser != null) {
                    CurrentUser.getInstance().setUser(loggedInUser);
                }

                return loggedInUser;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Books getBookData(String book_id) {
        try {
            conn.connect();
            String query = "SELECT * FROM books WHERE book_id = ?";
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setString(1, book_id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String title = rs.getString("title");
                String author = rs.getString("author");
                String genre = rs.getString("genre");
                String price = rs.getString("price");
                Books book = new Books(Integer.parseInt(book_id), title, author, genre, Integer.parseInt(price));
                return book;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static boolean buyBook(int book_id, int user_id) {
        try {
            conn.connect();
            String query = "SELECT * FROM transactions WHERE book_id = ? AND user_id = ?";
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, book_id);
            stmt.setInt(2, user_id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Buku sudah pernah dibeli!");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            conn.connect();
            String query = "INSERT INTO transactions (book_id, user_id) VALUES (?, ?)";
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, book_id);
            stmt.setInt(2, user_id);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String[][] getTransactionData(int user_id) {
    try {
        conn.connect();
        String query = "SELECT transactions.transaction_id, users.name, books.title, books.genre, books.price " +
                       "FROM transactions " +
                       "INNER JOIN users ON transactions.user_id = users.user_id " +
                       "INNER JOIN books ON transactions.book_id = books.book_id " +
                       "WHERE transactions.user_id = ?";
        PreparedStatement stmt = conn.con.prepareStatement(query);
        stmt.setInt(1, user_id);

        ResultSet rs = stmt.executeQuery();

        List<String[]> resultList = new ArrayList<>();
        while (rs.next()) {
            String[] row = new String[5];
            row[0] = rs.getString("transaction_id");
            row[1] = rs.getString("name");
            row[2] = rs.getString("title");
            row[3] = rs.getString("genre");
            row[4] = rs.getString("price");
            resultList.add(row);
        }

        String[][] returnData = new String[resultList.size()][5];
        for (int i = 0; i < resultList.size(); i++) {
            returnData[i] = resultList.get(i);
        }

        return returnData;

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

}
