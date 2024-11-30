package beans.example.controller;

import beans.example.model.Book;
import beans.example.model.Bookstore;

import java.util.List;

public class Inventory {
    private Bookstore bookstore;

    public Inventory(Bookstore bookstore) {
        this.bookstore = bookstore;
    }

    public void addBookToInventory(Book book) {
        bookstore.addBook(book);
    }

    public List<Book> getBooksInInventory() {
        return bookstore.getBooks();
    }
}
