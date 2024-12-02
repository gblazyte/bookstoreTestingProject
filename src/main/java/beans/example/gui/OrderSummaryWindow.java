package beans.example.gui;

import beans.example.model.Book;
import beans.example.model.Order;

import javax.swing.*;
import java.awt.*;

public class OrderSummaryWindow extends JDialog {

    public OrderSummaryWindow(Order order) {
        // Set the title and size of the modal window
        setTitle("Order Summary");
        setSize(500, 400);
        setLocationRelativeTo(null); // Center the window on the screen

        // Create the panel to display the order details
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add some padding around the content

        // Set up font for the labels
        Font labelFont = new Font("Arial", Font.PLAIN, 16);

        // Add the order details (Customer and Email)
        JLabel customerLabel = new JLabel("Customer: " + order.getCustomer().getName());
        customerLabel.setFont(labelFont);
        panel.add(customerLabel);

        JLabel emailLabel = new JLabel("Email: " + order.getCustomer().getEmail());
        emailLabel.setFont(labelFont);
        panel.add(emailLabel);

        // Add spacing between the customer info and book details
        panel.add(Box.createVerticalStrut(10));

        // Add the books in the order
        JLabel booksLabel = new JLabel("Books in Order:");
        booksLabel.setFont(labelFont);
        panel.add(booksLabel);

        // Loop through books and add them to the panel
        for (Book book : order.getBooks()) {
            JLabel bookDetails = new JLabel("<html>Title: " + book.getTitle() + " | Author: " + book.getAuthor() +
                    " | ISBN: " + book.getIsbn() + " | Price: €" + book.getPrice() + "</html>");
            bookDetails.setFont(new Font("Arial", Font.PLAIN, 14)); // Smaller font for book details
            panel.add(bookDetails);
        }

        // Add spacing between book list and total
        panel.add(Box.createVerticalStrut(10));

        // Add the total price label
        JLabel totalLabel = new JLabel("Total: €" + order.getTotalPrice());
        totalLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Bold font for total price
        panel.add(totalLabel);

        // Add a confirm button at the bottom
        JButton closeButton = new JButton("Close");
        closeButton.setPreferredSize(new Dimension(100, 30));
        closeButton.addActionListener(e -> dispose()); // Close the window on button click
        panel.add(Box.createVerticalStrut(20));  // Space between the total price and button
        panel.add(closeButton);

        // Add the panel to the frame and display the dialog
        add(panel);
        setModal(true); // Make this a modal dialog so it blocks interaction with the main window until closed
        setVisible(true);
    }
}
