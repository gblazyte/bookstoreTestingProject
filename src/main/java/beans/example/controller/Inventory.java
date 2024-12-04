package beans.example.controller;

import beans.example.model.Book;
import beans.example.model.Bookstore;

import java.util.List;

public class Inventory {
    private Bookstore bookstore;

    public Inventory(Bookstore bookstore) {
        if (bookstore == null) {
            throw new IllegalArgumentException("Bookstore cannot be null.");
        }
        this.bookstore = bookstore;
    }

    // Getter for bookstore (to be used in tests)
    public Bookstore getBookstore() {
        return bookstore;
    }

    // Add a book to the inventory
    public void addBookToInventory(Book book) {
        if (book == null || book.getTitle() == null || book.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Invalid book: Title cannot be null or empty.");
        }
        bookstore.addBook(book);
    }

    // Get all books from the inventory
    public List<Book> getBooksInInventory() {
        return bookstore.getBooks();
    }

    // Optional: Search for a book by title
    public Book findBookByTitle(String title) {
        if (title == null || title.isEmpty()) {
            return null;
        }
        return bookstore.getBooks()
                .stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }

    // Remove a book by ISBN
    public boolean removeBookByISBN(String isbn) {
        if (isbn == null || isbn.isEmpty()) {
            return false;
        }
        List<Book> books = bookstore.getBooks();
        // Using removeIf to remove the book that matches the ISBN
        boolean isRemoved = books.removeIf(book -> book.getIsbn().equals(isbn));
        return isRemoved; // Return true if a book was removed, otherwise false
    }
}
