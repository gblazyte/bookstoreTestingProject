package beans.example.gui;

import beans.example.controller.Inventory;
import beans.example.model.Book;

import javax.swing.*;
import java.awt.*;

public class AddBookWindow {

    private JTextField titleField;
    private JTextField authorField;
    private JTextField priceField;
    private JTextField isbnField;
    private Inventory inventory;
    private JFrame frame;
    private BookstoreGUI bookstoreGUI;


    public AddBookWindow(Inventory inventory, BookstoreGUI bookstoreGUI) {
        this.inventory = inventory;
        this.bookstoreGUI = bookstoreGUI;

        frame = new JFrame("Add Book");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        titleField = new JTextField(15);
        authorField = new JTextField(15);
        priceField = new JTextField(5);
        isbnField = new JTextField(10);

        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
        panel.add(new JLabel("Title"));
        panel.add(titleField);
        panel.add(new JLabel("Author"));
        panel.add(authorField);
        panel.add(new JLabel("Price"));
        panel.add(priceField);
        panel.add(new JLabel("ISBN"));
        panel.add(isbnField);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> addBook());
//        panel.add(new JLabel()); // Empty cell for alignment
        panel.add(addButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void addBook() {
        try {
            String title = titleField.getText().trim();
            String author = authorField.getText().trim();
            String isbn = isbnField.getText().trim();
            double price = Double.parseDouble(priceField.getText().trim());

            if (title.isEmpty() || author.isEmpty() || isbn.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All fields are required.");
                return;
            }

            Book book = new Book(title, author, price, isbn);
            inventory.addBookToInventory(book);
            // Notify BookstoreGUI to refresh the book list
            bookstoreGUI.showBooks(inventory.getBooksInInventory());
            JOptionPane.showMessageDialog(frame, "Book added successfully!");
            frame.dispose(); // Close the window
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid price. Please enter a valid number.");
        }
    }
}
