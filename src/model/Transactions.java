package model;

public class Transactions {
    private int transaction_id;
    private int user_id;
    private int book_id;

    public Transactions(int transaction_id, int user_id, int book_id) {
        this.transaction_id = transaction_id;
        this.user_id = user_id;
        this.book_id = book_id;
    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }
}
