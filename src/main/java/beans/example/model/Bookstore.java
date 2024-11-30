package beans.example.model;

import beans.example.controller.Inventory;

import java.util.ArrayList;
import java.util.List;

public class Bookstore {
    private List<Book> books;
    private Inventory inventory;

    public Bookstore() {
        this.books = new ArrayList<>();
        this.inventory = new Inventory(this);
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getBooks() {
        return books;
    }

    public Book getBookByIsbn(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }
    // Expose the Inventory instance
    public Inventory getInventory() {
        return inventory;
    }
}
