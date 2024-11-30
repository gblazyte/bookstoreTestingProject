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
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Set a nice background color
        frame.getContentPane().setBackground(new Color(240, 240, 240));

        // Create the text fields with better font and size
        titleField = new JTextField(15);
        authorField = new JTextField(15);
        priceField = new JTextField(10);
        isbnField = new JTextField(10);

        // Set tooltips for fields
        titleField.setToolTipText("Enter the title of the book");
        authorField.setToolTipText("Enter the author of the book");
        priceField.setToolTipText("Enter the price of the book (e.g. 19.99)");
        isbnField.setToolTipText("Enter the ISBN of the book");

        // Use GridBagLayout for better alignment and control
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // Padding between components

        // Title field
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Title:"), gbc);
        gbc.gridx = 1;
        panel.add(titleField, gbc);

        // Author field
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Author:"), gbc);
        gbc.gridx = 1;
        panel.add(authorField, gbc);

        // Price field
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Price:"), gbc);
        gbc.gridx = 1;
        panel.add(priceField, gbc);

        // ISBN field
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("ISBN:"), gbc);
        gbc.gridx = 1;
        panel.add(isbnField, gbc);

        // Add Button with styling
        JButton addButton = new JButton("Add Book");
        addButton.setBackground(new Color(33, 150, 243));  // Blue background
        addButton.setForeground(Color.WHITE);  // White text
        addButton.setFont(new Font("Arial", Font.BOLD, 14));  // Bold font
        addButton.setPreferredSize(new Dimension(150, 40));
        addButton.addActionListener(e -> addBook());
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(addButton, gbc);

        // Add the panel to the frame
        frame.add(panel);
        frame.setLocationRelativeTo(null); // Center the window on the screen
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
            bookstoreGUI.showBooks(inventory.getBooksInInventory());
            JOptionPane.showMessageDialog(frame, "Book added successfully!");
            frame.dispose(); // Close the window after adding
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid price. Please enter a valid number.");
        }
    }
}
