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

        // Set up the frame with a larger size and center it
        setTitle("Login");
        setSize(400, 300); // Increased window size for better spacing
        setLocationRelativeTo(null); // Center the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create components
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);  // Set smaller width for username field
        usernameField.setPreferredSize(new Dimension(200, 30));  // Set preferred size for input field
        usernameField.setToolTipText("Enter your username");
        usernameField.setFont(new Font("Arial", Font.PLAIN, 18));  // Set larger font for text

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);  // Set smaller width for password field
        passwordField.setPreferredSize(new Dimension(200, 30));  // Set preferred size for input field
        passwordField.setToolTipText("Enter your password");
        passwordField.setFont(new Font("Arial", Font.PLAIN, 18));  // Set larger font for text

        JButton loginButton = new JButton("Login");
        JButton cancelButton = new JButton("Cancel");

        // Button Styling
        loginButton.setBackground(new Color(33, 150, 243));  // Blue background
        loginButton.setForeground(Color.WHITE);  // White text
        loginButton.setPreferredSize(new Dimension(150, 40));  // Set preferred size for buttons

        cancelButton.setBackground(new Color(255, 82, 82));  // Red background
        cancelButton.setForeground(Color.WHITE);  // White text
        cancelButton.setPreferredSize(new Dimension(150, 40));  // Set preferred size for buttons

        // Main panel for the layout
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Vertical layout for fields

        // Add horizontal glue for centering components
        panel.add(Box.createVerticalStrut(20)); // Add top margin

        // Username
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(Box.createVerticalStrut(10)); // Space between fields

        // Password
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(Box.createVerticalStrut(20)); // Space before buttons

        // Panel for buttons - horizontally aligned
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));  // Horizontal flow layout
        buttonPanel.add(loginButton);
        buttonPanel.add(cancelButton);

        // Add components to the panel
        panel.add(buttonPanel);

        // Add the main panel to the frame
        add(panel);

        // Add action listeners for buttons
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
