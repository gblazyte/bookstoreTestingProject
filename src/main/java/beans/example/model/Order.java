package beans.example.model;

import java.util.List;

public class Order {
    private Customer customer;
    private List<Book> books;

    public Order(Customer customer, List<Book> books) {
        this.customer = customer;
        this.books = books;
    }

    // Getters and Setters
    public Customer getCustomer() {
        return customer;
    }

    public List<Book> getBooks() {
        return books;
    }

    public double getTotalPrice() {
        double total = 0;
        for (Book book : books) {
            total += book.getPrice();
        }
        return total;
    }
}
