package beans.example.gui;

import beans.example.controller.Inventory;
import beans.example.model.Book;
import beans.example.model.Bookstore;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BookstoreGUI {
    private JFrame frame;
    private JTextArea bookListArea;
    private Bookstore bookstore;

    public BookstoreGUI(Bookstore bookstore) {
        this.bookstore = bookstore; // Store the bookstore instance

        frame = new JFrame("Bookstore");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        bookListArea = new JTextArea();
        bookListArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(bookListArea);

        JButton addBookButton = new JButton("Add Book");
        addBookButton.addActionListener(e -> openAddBookWindow()); // Link to the add book window

        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(addBookButton, BorderLayout.SOUTH);
    }

    public void showBooks(List<Book> books) {
        StringBuilder sb = new StringBuilder();
        for (Book book : books) {
            sb.append("Title: ").append(book.getTitle())
                    .append(" - Author: ").append(book.getAuthor())
                    .append(" - ISBN: ").append(book.getIsbn())
                    .append(" - Price: ").append(book.getPrice())
                    .append("\n");
        }
        bookListArea.setText(sb.toString());
    }

    public void openAddBookWindow() {
        new AddBookWindow(bookstore.getInventory(), this);
//        new AddBookWindow(bookstore.getInventory());
//        showBooks(bookstore.getInventory().getBooksInInventory());
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }
}
