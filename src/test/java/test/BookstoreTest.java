package test;

import beans.example.controller.Inventory;
import beans.example.model.Book;
import beans.example.model.Bookstore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookstoreTest {

    private Bookstore bookstore;

    @BeforeEach
    void setUp() {
        bookstore = new Bookstore();
    }

    @Test
    void testAddBook() {
        Book book = new Book("Test Title", "Test Author", 19.99, "1234567890");
        bookstore.addBook(book);

        List<Book> books = bookstore.getBooks();
        assertEquals(1, books.size());
        assertEquals(book, books.get(0));
    }

    @Test
    void testGetBooks() {
        Book book1 = new Book("Test Title 1", "Test Author 1", 29.99, "1234567890");
        Book book2 = new Book("Test Title 2", "Test Author 2", 39.99, "0987654321");

        bookstore.addBook(book1);
        bookstore.addBook(book2);

        List<Book> books = bookstore.getBooks();
        assertEquals(2, books.size());
        assertTrue(books.contains(book1));
        assertTrue(books.contains(book2));
    }

    @Test
    void testGetBookByIsbn() {
        Book book = new Book("Test Title", "Test Author", 19.99, "1234567890");
        bookstore.addBook(book);

        Book foundBook = bookstore.getBookByIsbn("1234567890");
        assertNotNull(foundBook);
        assertEquals("1234567890", foundBook.getIsbn());

        Book notFoundBook = bookstore.getBookByIsbn("1111111111");
        assertNull(notFoundBook);
    }

    @Test
    void testGetInventory() {
        Inventory inventory = bookstore.getInventory();
        assertNotNull(inventory);

        // Ensure the Inventory is correctly associated with the Bookstore instance
        assertEquals(bookstore, inventory.getBookstore());
    }



}