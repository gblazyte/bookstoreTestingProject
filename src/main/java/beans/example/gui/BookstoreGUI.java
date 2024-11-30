package beans.example.gui;

import beans.example.model.Book;
import beans.example.model.Bookstore;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BookstoreGUI {
    private JFrame frame;
    private JTextArea bookListArea;

    public BookstoreGUI(Bookstore bookstore) {
        frame = new JFrame("Bookstore");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        bookListArea = new JTextArea();
        bookListArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(bookListArea);

        JButton addBookButton = new JButton("Add Book");
        addBookButton.addActionListener(e -> openAddBookWindow());

        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(addBookButton, BorderLayout.SOUTH);
    }

    public void showBooks(List<Book> books) {
        StringBuilder sb = new StringBuilder();
        for (Book book : books) {
            sb.append(book.getTitle()).append(" - ").append(book.getPrice()).append("\n");
        }
        bookListArea.setText(sb.toString());
    }

    public void openAddBookWindow() {
        // Open a window to add new books
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }
}
