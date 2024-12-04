package test;

import beans.example.model.Book;
import beans.example.model.Customer;
import beans.example.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private Customer customer;
    private Book book1;
    private Book book2;
    private Order order;

    @BeforeEach
    void setUp() {
        // Initialize test data
        customer = new Customer("John Doe", "john.doe@example.com");
        book1 = new Book("Book 1", "Author 1", 29.99, "1234567890");
        book2 = new Book("Book 2", "Author 2", 39.99, "0987654321");

        List<Book> books = Arrays.asList(book1, book2);
        order = new Order(customer, books);
    }

    @Test
    void testOrderConstructor() {
        // Test that the constructor correctly initializes the Order with Customer and Books
        assertEquals(customer, order.getCustomer());
        assertEquals(2, order.getBooks().size());
        assertTrue(order.getBooks().contains(book1));
        assertTrue(order.getBooks().contains(book2));
    }

    @Test
    void testGetCustomer() {
        // Test that the getCustomer method returns the correct customer
        assertEquals(customer, order.getCustomer());
    }

    @Test
    void testGetBooks() {
        // Test that the getBooks method returns the correct list of books
        List<Book> books = order.getBooks();
        assertEquals(2, books.size());
        assertTrue(books.contains(book1));
        assertTrue(books.contains(book2));
    }

    @Test
    void testGetTotalPrice() {
        // Test that getTotalPrice calculates the correct total
        double expectedTotalPrice = book1.getPrice() + book2.getPrice();
        assertEquals(expectedTotalPrice, order.getTotalPrice(), 0.01);
    }

    @Test
    void testSetCustomer() {
        // Test that setCustomer correctly updates the customer
        Customer newCustomer = new Customer("Jane Doe", "jane.doe@example.com");
        order.setCustomer(newCustomer);
        assertEquals(newCustomer, order.getCustomer());
    }

    @Test
    void testSetBooks() {
        // Test that setBooks correctly updates the list of books
        Book book3 = new Book("Book 3", "Author 3", 49.99, "1122334455");
        List<Book> newBooks = Arrays.asList(book3);
        order.setBooks(newBooks);
        assertEquals(1, order.getBooks().size());
        assertTrue(order.getBooks().contains(book3));
    }

    @Test
    void testGetTotalPriceWithNoBooks() {
        // Test the case where the order has no books
        Order emptyOrder = new Order(customer, Arrays.asList());
        assertEquals(0.0, emptyOrder.getTotalPrice(), 0.01);
    }
}
