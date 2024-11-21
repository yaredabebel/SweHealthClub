import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame {
    private JTextField userIDField;
    private JPasswordField passwordField;
    private JLabel messageLabel;
    private ClubDatabase clubDatabase;

    public LoginPage(ClubDatabase clubDatabase) {
        this.clubDatabase = clubDatabase;

        setTitle("Gym Member Tracking System - Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel for the login form
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));
        add(panel);

        // User ID input
        panel.add(new JLabel("User ID:"));
        userIDField = new JTextField();
        panel.add(userIDField);

        // Password input
        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        // Login button
        JButton loginButton = new JButton("Login");
        panel.add(loginButton);

        // Message label for feedback
        messageLabel = new JLabel("", JLabel.CENTER);
        panel.add(messageLabel);

        // Add action listener for the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });
    }

    private void handleLogin() {
        try {
            int userID = Integer.parseInt(userIDField.getText());
            String password = new String(passwordField.getPassword());

            // Validate user credentials
            Person loggedInUser = clubDatabase.getUser(userID, password);

            if (loggedInUser != null) {
                messageLabel.setText("Login Successful!");
                openDashboard(loggedInUser);
            } else {
                messageLabel.setText("Invalid User ID or Password.");
            }
        } catch (NumberFormatException ex) {
            messageLabel.setText("User ID must be a number.");
        }
    }

    private void openDashboard(Person user) {
        JFrame dashboard;
        if (user instanceof Member) {
            dashboard = new MemberDashboard((Member) user, clubDatabase, this); // Pass LoginPage reference
        } else if (user instanceof Staff) {
            dashboard = new StaffDashboard((Staff) user, clubDatabase, this); // Pass LoginPage reference
        } else if (user instanceof Manager) {
            dashboard = new ManagerDashboard((Manager) user, clubDatabase, this); // Pass LoginPage reference
        } else {
            return;
        }

        setVisible(false); // Hide the login page
        dashboard.setVisible(true); // Show the appropriate dashboard
    }
}
