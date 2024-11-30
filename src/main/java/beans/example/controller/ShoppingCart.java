package beans.example.controller;

import beans.example.model.Book;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private List<Book> books;

    public ShoppingCart() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getBooks() {
        return books;
    }

    public double getTotal() {
        double total = 0;
        for (Book book : books) {
            total += book.getPrice();
        }
        return total;
    }
}
