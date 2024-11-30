package test;
import beans.example.model.Book;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookTest {

    private Book book;

    @BeforeEach
    public void setUp() {
        // Set up a new Book object before each test
        book = new Book("1984", "George Orwell", 9.99, "1234567890");
    }

    @Test
    void setTitle() {
        // Test the setter for the title
        book.setTitle("Animal Farm");
        assertEquals("Animal Farm", book.getTitle(), "Title should be updated to Animal Farm");
    }

    @Test
    void setAuthor() {
        // Test the setter for the author
        book.setAuthor("Aldous Huxley");
        assertEquals("Aldous Huxley", book.getAuthor(), "Author should be updated to Aldous Huxley");
    }

    @Test
    void setPrice() {
        // Test the setter for the price
        book.setPrice(12.99);
        assertEquals(12.99, book.getPrice(), "Price should be updated to 12.99");
    }

    @Test
    void setIsbn() {
        // Test the setter for the ISBN
        book.setIsbn("0987654321");
        assertEquals("0987654321", book.getIsbn(), "ISBN should be updated to 0987654321");
    }

    @Test
    void getTitle() {
        // Test that the getTitle() method works as expected
        assertEquals("1984", book.getTitle(), "Title should be 1984");
    }

    @Test
    void getAuthor() {
        // Test that the getAuthor() method works as expected
        assertEquals("George Orwell", book.getAuthor(), "Author should be George Orwell");
    }

    @Test
    void getPrice() {
        // Test that the getPrice() method works as expected
        assertEquals(9.99, book.getPrice(), "Price should be 9.99");
    }

    @Test
    void getIsbn() {
        // Test that the getIsbn() method works as expected
        assertEquals("1234567890", book.getIsbn(), "ISBN should be 1234567890");
    }
}