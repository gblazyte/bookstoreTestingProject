package beans.example.gui;

import beans.example.controller.Inventory;
import beans.example.controller.ShoppingCart;
import beans.example.model.Book;
import beans.example.model.Bookstore;
import beans.example.model.Customer;
import beans.example.model.Order;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BookstoreGUI {
    private JFrame frame;
    private JTextArea bookListArea;
    private Bookstore bookstore;
    private ShoppingCart shoppingCart;

    public BookstoreGUI(Bookstore bookstore) {
        this.bookstore = bookstore; // Store the bookstore instance
        this.shoppingCart = new ShoppingCart(); // Initialize the shopping cart

        frame = new JFrame("Bookstore");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);

        bookListArea = new JTextArea();
        bookListArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(bookListArea);

        JButton addBookButton = new JButton("Add Book");
        addBookButton.addActionListener(e -> openAddBookWindow()); // Link to the add book window

        JButton viewCartButton = new JButton("View Cart");
        viewCartButton.addActionListener(e -> viewCart());

        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(e -> checkout());


        // Panel for buttons (Add Book, View Cart, Checkout)
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addBookButton);
        buttonPanel.add(viewCartButton);
        buttonPanel.add(checkoutButton);

        // Layout for the frame: add the scrollable book list and the buttons
        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Make the frame visible
        frame.setVisible(true);
    }

    public void showBooks(List<Book> books) {
        // Create a new StringBuilder for displaying book list details in the text area
        StringBuilder sb = new StringBuilder();

        // Create a new main panel to hold all books and their buttons
        JPanel bookPanel = new JPanel();
        bookPanel.setLayout(new BoxLayout(bookPanel, BoxLayout.Y_AXIS)); // Stack vertically

        for (Book book : books) {
            // Add book details to the StringBuilder (for displaying in the text area)
            sb.append("Title: ").append(book.getTitle())
                    .append(" - Author: ").append(book.getAuthor())
                    .append(" - ISBN: ").append(book.getIsbn())
                    .append(" - Price: ").append(book.getPrice())
                    .append("\n");

            // Create a panel to hold the book details and the Add to Cart button
            JPanel bookInfoPanel = new JPanel();
            bookInfoPanel.setLayout(new FlowLayout()); // Layout for book details and button

            // Label for book details
            JLabel bookLabel = new JLabel("Title: " + book.getTitle() + " | Author: " + book.getAuthor() +
                    " | ISBN: " + book.getIsbn() + " | Price: $" + book.getPrice());
            bookInfoPanel.add(bookLabel);

            // Create the Add to Cart button
            JButton addToCartButton = new JButton("Add to Cart");
            addToCartButton.addActionListener(e -> shoppingCart.addBook(book)); // Add book to cart
            bookInfoPanel.add(addToCartButton); // Add the button to the panel

            // Add this book info panel to the main book panel
            bookPanel.add(bookInfoPanel);
        }

        // Update the text area with book list details
        bookListArea.setText(sb.toString());

        // Clear the previous content from the frame and update with the new book panel
        JScrollPane bookScrollPane = new JScrollPane(bookPanel); // Scrollable panel for the books and buttons
        frame.getContentPane().removeAll(); // Clear previous components
        frame.add(bookScrollPane, BorderLayout.CENTER); // Add the new bookPanel with buttons
        frame.add(createBottomPanel(), BorderLayout.SOUTH); // Add the buttons panel back

        // Ensure the layout is updated properly
        frame.revalidate();
        frame.repaint();
    }

    private JPanel createBottomPanel() {
        JPanel buttonPanel = new JPanel();
        JButton addBookButton = new JButton("Add Book");
        addBookButton.addActionListener(e -> openAddBookWindow());

        JButton viewCartButton = new JButton("View Cart");
        viewCartButton.addActionListener(e -> viewCart());

        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(e -> checkout());

        buttonPanel.add(addBookButton);
        buttonPanel.add(viewCartButton);
        buttonPanel.add(checkoutButton);

        return buttonPanel;
    }

    public void openAddBookWindow() {
        new AddBookWindow(bookstore.getInventory(), this);
    }

    public void viewCart() {
        // Show a new window with the cart contents
        new ShoppingCartWindow(shoppingCart);
    }

    public void checkout() {
        if (shoppingCart.getBooks().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Your cart is empty. Please add books to the cart.");
        } else {
            // Create an order with a dummy customer (you can add fields for real customer data)
            Order order = new Order(new Customer("Customer Name", "email@gmail.com"), shoppingCart.getBooks());

            // Show order confirmation and total price
            JOptionPane.showMessageDialog(frame, "Order placed successfully!\nTotal: $" + order.getTotalPrice());

            // Show the order summary in a new window
            new OrderSummaryWindow(order);

            // Reset the shopping cart after the order is placed
            shoppingCart = new ShoppingCart();
        }
    }

    public void viewOrderSummary() {
        if (shoppingCart.getBooks().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Your cart is empty. Please add books to the cart.");
        } else {
            // Create an order to display summary
            Order order = new Order(new Customer("Customer Name", "email@gmail.com"), shoppingCart.getBooks());

            // Show the order summary in a modal dialog
            new OrderSummaryWindow(order); // This is the modal dialog
        }
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }
}
