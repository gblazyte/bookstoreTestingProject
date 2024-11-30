package test;

import beans.example.model.Book;
import beans.example.model.Bookstore;
import beans.example.controller.Inventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InventoryTest {

    private Bookstore bookstore;
    private Inventory inventory;

    @BeforeEach
    public void setUp() {
        // Create a mock Bookstore object
        bookstore = mock(Bookstore.class);
        inventory = new Inventory(bookstore);
    }

    @Test
    public void testConstructorThrowsExceptionWhenBookstoreIsNull() {
        // Test if an exception is thrown when passing a null bookstore
        assertThrows(IllegalArgumentException.class, () -> new Inventory(null), "Bookstore cannot be null.");
    }

    @Test
    public void testAddBookToInventory() {
        // Create a Book object
        Book book = new Book("1984", "George Orwell", 9.99, "1234567890");

        // Mock the behavior of bookstore.addBook()
        doNothing().when(bookstore).addBook(book);

        // Add book to inventory
        inventory.addBookToInventory(book);

        // Verify that the bookstore's addBook method was called with the book
        verify(bookstore, times(1)).addBook(book);
    }

    @Test
    public void testAddBookToInventoryWithInvalidBook() {
        // Test when the book has a null title (invalid book)
        Book invalidBook = new Book(null, "George Orwell", 9.99, "1234567890");

        // Verify that adding an invalid book throws an exception
        assertThrows(IllegalArgumentException.class, () -> inventory.addBookToInventory(invalidBook), "Invalid book: Title cannot be null or empty.");
    }

    @Test
    public void testGetBooksInInventory() {
        // Mock the behavior of bookstore.getBooks()
        List<Book> mockBooks = new ArrayList<>();
        mockBooks.add(new Book("1984", "George Orwell", 9.99, "1234567890"));
        when(bookstore.getBooks()).thenReturn(mockBooks);

        // Get books from inventory
        List<Book> booksInInventory = inventory.getBooksInInventory();

        // Verify that the correct books are returned
        assertNotNull(booksInInventory);
        assertEquals(1, booksInInventory.size());
        assertEquals("1984", booksInInventory.get(0).getTitle());
    }

    @Test
    public void testFindBookByTitleFound() {
        // Mock bookstore.getBooks() to return a list with one book
        List<Book> mockBooks = new ArrayList<>();
        mockBooks.add(new Book("1984", "George Orwell", 9.99, "1234567890"));
        when(bookstore.getBooks()).thenReturn(mockBooks);

        // Search for a book by title
        Book foundBook = inventory.findBookByTitle("1984");

        // Verify the book is found
        assertNotNull(foundBook);
        assertEquals("1984", foundBook.getTitle());
    }

    @Test
    public void testFindBookByTitleNotFound() {
        // Mock bookstore.getBooks() to return an empty list
        when(bookstore.getBooks()).thenReturn(new ArrayList<>());

        // Search for a book by title
        Book foundBook = inventory.findBookByTitle("Non-existent Book");

        // Verify that no book is found
        assertNull(foundBook);
    }

    @Test
    public void testRemoveBookByISBN() {
        // Mock bookstore.getBooks() to return a list with one book
        List<Book> mockBooks = new ArrayList<>();
        Book bookToRemove = new Book("1984", "George Orwell", 9.99, "1234567890");
        mockBooks.add(bookToRemove);
        when(bookstore.getBooks()).thenReturn(mockBooks);

        // Remove the book by ISBN
        boolean isRemoved = inventory.removeBookByISBN("1234567890");

        // Verify the book was removed
        assertTrue(isRemoved);

        // Verify that the removeIf was called with the correct ISBN
        verify(bookstore, times(1)).getBooks();
    }

    @Test
    public void testRemoveBookByInvalidISBN() {
        // Mock bookstore.getBooks() to return a list with one book
        List<Book> mockBooks = new ArrayList<>();
        Book bookToRemove = new Book("1984", "George Orwell", 9.99, "1234567890");
        mockBooks.add(bookToRemove);
        when(bookstore.getBooks()).thenReturn(mockBooks);

        // Try to remove a book by an invalid ISBN (non-existent)
        boolean isRemoved = inventory.removeBookByISBN("0987654321");

        // Verify that no book was removed
        assertFalse(isRemoved);
    }

    @Test
    public void testRemoveBookByNullISBN() {
        // Try to remove a book with a null ISBN
        boolean isRemoved = inventory.removeBookByISBN(null);

        // Verify that the removal returns false
        assertFalse(isRemoved);
    }
}
