package beans.example;

import beans.example.model.Bookstore;
import beans.example.controller.Inventory;
import beans.example.gui.BookstoreGUI;


public class Main {
    public static void main(String[] args) {

        Bookstore bookstore = new Bookstore();
        Inventory inventory = new Inventory(bookstore);
        BookstoreGUI gui = new BookstoreGUI(bookstore);

        gui.setVisible(true);

    }
}