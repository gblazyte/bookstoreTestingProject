package test;

import beans.example.model.Book;
import beans.example.controller.ShoppingCart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {

    private ShoppingCart shoppingCart;
    private Book book1;
    private Book book2;

    @BeforeEach
    void setUp() {
        // Initialize test data
        shoppingCart = new ShoppingCart();
        book1 = new Book("Book 1", "Author 1", 29.99, "1234567890");
        book2 = new Book("Book 2", "Author 2", 39.99, "0987654321");
    }

    @Test
    void testAddBook() {
        // Test adding a book to the shopping cart
        shoppingCart.addBook(book1);
        shoppingCart.addBook(book2);

        List<Book> booksInCart = shoppingCart.getBooks();
        assertEquals(2, booksInCart.size());
        assertTrue(booksInCart.contains(book1));
        assertTrue(booksInCart.contains(book2));
    }

    @Test
    void testGetBooks() {
        // Test that getBooks returns the correct list of books
        shoppingCart.addBook(book1);
        shoppingCart.addBook(book2);

        List<Book> booksInCart = shoppingCart.getBooks();
        assertEquals(2, booksInCart.size());
        assertTrue(booksInCart.contains(book1));
        assertTrue(booksInCart.contains(book2));
    }

    @Test
    void testGetTotal() {
        // Test that getTotal correctly calculates the total price of the books in the cart
        shoppingCart.addBook(book1);
        shoppingCart.addBook(book2);

        double expectedTotal = book1.getPrice() + book2.getPrice();
        assertEquals(expectedTotal, shoppingCart.getTotal(), 0.01);
    }

    @Test
    void testGetTotalEmptyCart() {
        // Test that getTotal returns 0 when the cart is empty
        double expectedTotal = 0.0;
        assertEquals(expectedTotal, shoppingCart.getTotal(), 0.01);
    }

    @Test
    void testAddSingleBook() {
        // Test adding a single book to the cart and checking the total
        shoppingCart.addBook(book1);
        assertEquals(1, shoppingCart.getBooks().size());
        assertEquals(book1.getPrice(), shoppingCart.getTotal(), 0.01);
    }
}
