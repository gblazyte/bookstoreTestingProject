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
        this.bookstore = bookstore;
        this.shoppingCart = new ShoppingCart();

        frame = new JFrame("Bookstore");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);  // Set the window size

        // Center the window on the screen
        frame.setLocationRelativeTo(null);

        // Main panel with BorderLayout for better organization
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        bookListArea = new JTextArea();
        bookListArea.setEditable(false);
        bookListArea.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(bookListArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Add buttons at the bottom
        mainPanel.add(createBottomPanel(), BorderLayout.SOUTH);

        // Set the frame properties
        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }

    public void showBooks(List<Book> books) {
        StringBuilder sb = new StringBuilder();
        JPanel bookPanel = new JPanel();
        bookPanel.setLayout(new BoxLayout(bookPanel, BoxLayout.Y_AXIS));

        for (Book book : books) {
            sb.append("Title: ").append(book.getTitle())
                    .append(" - Author: ").append(book.getAuthor())
                    .append(" - ISBN: ").append(book.getIsbn())
                    .append(" - Price: €").append(book.getPrice())
                    .append("\n");

            JPanel bookInfoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            JLabel bookLabel = new JLabel("Title: " + book.getTitle() + " | Author: " + book.getAuthor() +
                    " | ISBN: " + book.getIsbn() + " | Price: €" + book.getPrice());
            bookInfoPanel.add(bookLabel);

            JButton addToCartButton = new JButton("Add to Cart");
            addToCartButton.setBackground(new Color(76, 175, 80));  // Green background
            addToCartButton.setForeground(Color.WHITE);  // White text
            addToCartButton.addActionListener(e -> shoppingCart.addBook(book));
            bookInfoPanel.add(addToCartButton);

            bookPanel.add(bookInfoPanel);
        }

        bookListArea.setText(sb.toString());

        JScrollPane bookScrollPane = new JScrollPane(bookPanel);
        frame.getContentPane().removeAll();
        frame.add(bookScrollPane, BorderLayout.CENTER);
        frame.add(createBottomPanel(), BorderLayout.SOUTH);
        frame.revalidate();
        frame.repaint();
    }

    private JPanel createBottomPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));  // Spacing between buttons

        JButton addBookButton = new JButton("Add Book");
        addBookButton.setBackground(new Color(33, 150, 243));  // Blue color
        addBookButton.setForeground(Color.WHITE);
        addBookButton.addActionListener(e -> openAddBookWindow());
        addBookButton.setToolTipText("Add a new book to the bookstore");
        buttonPanel.add(addBookButton);

        JButton viewCartButton = new JButton("View Cart");
        viewCartButton.setBackground(new Color(255, 87, 34));  // Orange color
        viewCartButton.setForeground(Color.WHITE);
        viewCartButton.addActionListener(e -> viewCart());
        viewCartButton.setToolTipText("View your shopping cart");
        buttonPanel.add(viewCartButton);

        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.setBackground(new Color(0, 188, 212));  // Teal color
        checkoutButton.setForeground(Color.WHITE);
        checkoutButton.addActionListener(e -> checkout());
        checkoutButton.setToolTipText("Proceed to checkout");
        buttonPanel.add(checkoutButton);

        JButton removeBookButton = new JButton("Remove Book");
        removeBookButton.setBackground(new Color(239, 83, 80));  // Red color
        removeBookButton.setForeground(Color.WHITE);
        removeBookButton.addActionListener(e -> removeBook());
        removeBookButton.setToolTipText("Remove a book from the inventory");
        buttonPanel.add(removeBookButton);

        return buttonPanel;
    }

    public void openAddBookWindow() {
        new AddBookWindow(bookstore.getInventory(), this);
    }

    public void viewCart() {
        new ShoppingCartWindow(shoppingCart);
    }

    public void checkout() {
        if (shoppingCart.getBooks().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Your cart is empty. Please add books to the cart.");
        } else {
            Order order = new Order(new Customer("Customer Name", "email@gmail.com"), shoppingCart.getBooks());
            JOptionPane.showMessageDialog(frame, "Order placed successfully!\nTotal: €" + order.getTotalPrice());
            new OrderSummaryWindow(order);
            shoppingCart = new ShoppingCart();
        }
    }

    public void removeBook() {
        String isbn = JOptionPane.showInputDialog(frame, "Enter ISBN of the book to remove:");
        if (isbn != null && !isbn.isEmpty()) {
            boolean success = bookstore.getInventory().removeBookByISBN(isbn);
            if (success) {
                JOptionPane.showMessageDialog(frame, "Book removed successfully.");
                showBooks(bookstore.getInventory().getBooksInInventory());
            } else {
                JOptionPane.showMessageDialog(frame, "No book found with that ISBN.");
            }
        }
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }
}
