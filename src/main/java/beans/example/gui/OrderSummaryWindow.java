package beans.example.gui;

import beans.example.model.Book;
import beans.example.model.Order;

import javax.swing.*;
import java.awt.*;

public class OrderSummaryWindow extends JDialog {

    public OrderSummaryWindow(Order order) {
        // Set the title and size of the modal window
        setTitle("Order Summary");
        setSize(400, 300);
        setLocationRelativeTo(null); // Center the window on the screen

        // Create the panel to display the order details
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Add the order details (Customer and Email)
        JLabel customerLabel = new JLabel("Customer: " + order.getCustomer().getName());
        panel.add(customerLabel);

        JLabel emailLabel = new JLabel("Email: " + order.getCustomer().getEmail());
        panel.add(emailLabel);

        // Add the books in the order
        JLabel booksLabel = new JLabel("Books:");
        panel.add(booksLabel);

        for (Book book : order.getBooks()) {
            JLabel bookDetails = new JLabel("Title: " + book.getTitle() + " | Author: " + book.getAuthor() +
                    " | ISBN: " + book.getIsbn() + " | Price: $" + book.getPrice());
            panel.add(bookDetails);
        }

        // Add the total price
        JLabel totalLabel = new JLabel("Total: $" + order.getTotalPrice());
        panel.add(totalLabel);

        // Add the panel to the frame and display the dialog
        add(panel);
        setModal(true); // Make this a modal dialog so it blocks interaction with the main window until closed
        setVisible(true);
    }
}
