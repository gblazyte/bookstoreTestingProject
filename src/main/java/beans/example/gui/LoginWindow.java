package beans.example.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import beans.example.Main;

public class LoginWindow extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private Main mainApp;

    public LoginWindow(Main mainApp) {
        this.mainApp = mainApp;

        // Set up the frame
        setTitle("Login");
        setSize(300, 200);
        setLocationRelativeTo(null); // Center the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2)); // Grid layout for form fields

        // Create components
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        JButton cancelButton = new JButton("Cancel");

        // Add components to the frame
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
        add(cancelButton);

        // Add action listeners for the buttons
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginAction();
            }
        });

        cancelButton.addActionListener(e -> System.exit(0)); // Close the app on cancel

        setVisible(true);
    }

    // Handle login action
    private void loginAction() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // Hardcoded login credentials for demo purposes
        if ("admin".equals(username) && "password".equals(password)) {
            JOptionPane.showMessageDialog(this, "Login Successful!");
            // Notify the Main class to initialize the BookstoreGUI
            mainApp.launchBookstoreApp();
            this.dispose(); // Close the login window
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password. Please try again.");
        }
    }
}
