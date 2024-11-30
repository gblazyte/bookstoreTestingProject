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

        frame = new JFrame("Shopping Cart");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        cartArea = new JTextArea();
        cartArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(cartArea);

        frame.add(scrollPane, BorderLayout.CENTER);

        // Display the books in the cart
        updateCartDisplay();

        frame.setVisible(true);
    }

    private void updateCartDisplay() {
        StringBuilder sb = new StringBuilder();
        double total = 0;

        for (Book book : shoppingCart.getBooks()) {
            sb.append("Title: ").append(book.getTitle())
                    .append(" - Author: ").append(book.getAuthor())
                    .append(" - ISBN: ").append(book.getIsbn())
                    .append(" - Price: $").append(book.getPrice())
                    .append("\n");
            total += book.getPrice();
        }

        sb.append("\nTotal: $").append(total);
        cartArea.setText(sb.toString());
    }
}


