package beans.example.gui;

import beans.example.controller.ShoppingCart;
import beans.example.model.Book;

import javax.swing.*;
import java.awt.*;

public class ShoppingCartWindow {
    private JFrame frame;
    private JTextArea cartArea;
    private ShoppingCart shoppingCart;

    public ShoppingCartWindow(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;

        // Create and configure the frame
        frame = new JFrame("Shopping Cart");
        frame.setSize(500, 400);  // Set a more appropriate size for the window
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);  // Center the window on the screen

        // Set up the cart area to display the list of books
        cartArea = new JTextArea();
        cartArea.setEditable(false);
        cartArea.setFont(new Font("Arial", Font.PLAIN, 14));  // Increase font size for readability
        JScrollPane scrollPane = new JScrollPane(cartArea);

        // Add the scroll pane to the frame
        frame.add(scrollPane, BorderLayout.CENTER);

        // Add a button to close the window
        JPanel bottomPanel = new JPanel();
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> frame.dispose());  // Close the window when clicked
        bottomPanel.add(closeButton);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // Display the books in the cart
        updateCartDisplay();

        // Make the frame visible
        frame.setVisible(true);
    }

    private void updateCartDisplay() {
        StringBuilder sb = new StringBuilder();
        double total = 0;

        // Loop through the books in the shopping cart and display them
        for (Book book : shoppingCart.getBooks()) {
            sb.append("Title: ").append(book.getTitle())
                    .append(" - Author: ").append(book.getAuthor())
                    .append(" - ISBN: ").append(book.getIsbn())
                    .append(" - Price: $").append(book.getPrice())
                    .append("\n");
            total += book.getPrice();
        }

        // Add the total price of the cart
        sb.append("\nTotal: $").append(total);
        cartArea.setText(sb.toString());  // Update the cart area with the current cart content
    }
}
