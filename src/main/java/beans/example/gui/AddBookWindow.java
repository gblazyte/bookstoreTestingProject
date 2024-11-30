package beans.example.gui;

import beans.example.controller.Inventory;
import beans.example.model.Book;

import javax.swing.*;
import java.util.List;

public class AddBookWindow {

    private JTextField titleField;
    private JTextField authorField;
    private JTextField priceField;
    private JTextField isbnField;
    private Inventory inventory;

    public AddBookWindow(Inventory inventory) {
        this.inventory = inventory;

        JFrame frame = new JFrame("Add Book");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        titleField = new JTextField(15);
        authorField = new JTextField(15);
        priceField = new JTextField(5);
        isbnField = new JTextField(10);

        JPanel panel = new JPanel();
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

        panel.add(addButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void addBook() {
        String title = titleField.getText();
        String author = authorField.getText();
        double price = Double.parseDouble(priceField.getText());
        String isbn = isbnField.getText();

        Book book = new Book(title, author, price, isbn);
        inventory.addBookToInventory(book);
        JOptionPane.showMessageDialog(null, "Book added successfully!");
    }
}
