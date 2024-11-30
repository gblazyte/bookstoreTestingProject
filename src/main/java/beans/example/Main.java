package beans.example;

import beans.example.model.Bookstore;
import beans.example.controller.Inventory;
import beans.example.gui.BookstoreGUI;
import beans.example.gui.LoginWindow;

public class Main {

    public static void main(String[] args) {
        new LoginWindow(new Main()); // Pass this instance to LoginWindow

    }

    public void launchBookstoreApp() {
        // Initialize the bookstore and other components after login
        Bookstore bookstore = new Bookstore();
        Inventory inventory = new Inventory(bookstore);
        BookstoreGUI gui = new BookstoreGUI(bookstore);

        gui.setVisible(true); // Display the bookstore GUI
    }
}
