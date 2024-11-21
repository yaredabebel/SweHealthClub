import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StaffDashboard extends JFrame {
    protected Staff staff; // Works for Staff and its subclasses, like Manager
    protected ClubDatabase clubDatabase;

    public StaffDashboard(Staff staff, ClubDatabase clubDatabase,JFrame loginPage) {
        this.staff = staff; // Polymorphic assignment
        this.clubDatabase = clubDatabase;

        setTitle("Staff Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Add functionalities as buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 1, 10, 10));
        add(panel);

        // Add buttons for staff functionalities
        addButton(panel, "Verify Customer Membership", e -> verifyCustomerMembership());
        addButton(panel, "Check for Expired Membership", e -> checkForExpiredMembership());
        addButton(panel, "Record Entry", e -> recordEntry());
        addButton(panel, "Renew Membership", e -> renewMembership());
        addButton(panel, "Update User Info", e -> updateUserInfo());
        addButton(panel, "Add New Member", e -> addNewMember());
        addButton(panel, "Remove User", e -> removeUser());
        addButton(panel, "Send Renewal Notices", e -> sendRenewalNotices());

        JButton backButton = new JButton("Back to Login");
        backButton.addActionListener(e -> {
            setVisible(false); // Hide the current dashboard
            loginPage.setVisible(true); // Show the login page
        });
        panel.add(backButton);
    }

    // Method to initialize the UI
    private void initializeUI() {

    }

    // Helper method to create buttons
    protected void addButton(JPanel panel, String title, ActionListener action) {
        JButton button = new JButton(title);
        button.addActionListener(action);
        panel.add(button);
    }

    // Verify membership of a customer
    protected void verifyCustomerMembership() {
        int userID = getUserIDInput("Enter User ID to verify membership:");
        Person user = clubDatabase.getUser(userID, "");
        if (user != null) {
            JOptionPane.showMessageDialog(this, "User is a valid member: " + user.getFirstName() + " " + user.getLastName());
        } else {
            JOptionPane.showMessageDialog(this, "User ID not found in the system.");
        }
    }

    // Check if a customer's membership is expired
    protected void checkForExpiredMembership() {
        int userID = getUserIDInput("Enter User ID to check for expired membership:");
        Person user = clubDatabase.getUser(userID, "");
        if (user instanceof Member) {
            JOptionPane.showMessageDialog(this, "Membership for " + user.getFirstName() + " is valid.");
        } else {
            JOptionPane.showMessageDialog(this, "User ID not found or not a member.");
        }
    }

    // Record a customer's entry into the gym
    protected void recordEntry() {
        int userID = getUserIDInput("Enter User ID to record entry:");
        Person user = clubDatabase.getUser(userID, "");
        if (user instanceof Member) {
            Member member = (Member) user;
            member.visitGym();
            JOptionPane.showMessageDialog(this, "Entry recorded for: " + member.getFirstName());
        } else {
            JOptionPane.showMessageDialog(this, "User ID not found or not a member.");
        }
    }

    // Renew a customer's membership
    protected void renewMembership() {
        int userID = getUserIDInput("Enter User ID to renew membership:");
        int duration = getIntegerInput("Enter duration to renew (in months):");
        Person user = clubDatabase.getUser(userID, "");
        if (user instanceof Member) {
            JOptionPane.showMessageDialog(this, "Membership renewed for " + duration + " months.");
        } else {
            JOptionPane.showMessageDialog(this, "User ID not found or not a member.");
        }
    }

    // Update a customer's information
    protected void updateUserInfo() {
        int userID = getUserIDInput("Enter User ID to update:");
        String fieldName = JOptionPane.showInputDialog(this, "Enter field to update (e.g., email):");
        String newValue = JOptionPane.showInputDialog(this, "Enter new value:");
        Person user = clubDatabase.getUser(userID, "");
        if (user != null) {
            JOptionPane.showMessageDialog(this, fieldName + " updated to: " + newValue);
        } else {
            JOptionPane.showMessageDialog(this, "User ID not found.");
        }
    }

    // Add a new member to the system
    protected void addNewMember() {
        int userID = getIntegerInput("Enter new User ID:");
        String password = JOptionPane.showInputDialog(this, "Enter password:");
        String email = JOptionPane.showInputDialog(this, "Enter email:");
        String firstName = JOptionPane.showInputDialog(this, "Enter first name:");
        String lastName = JOptionPane.showInputDialog(this, "Enter last name:");
        Member newMember = new Member(userID, password, email, firstName, lastName);
        clubDatabase.addUser(newMember);
        JOptionPane.showMessageDialog(this, "New member added: " + firstName + " " + lastName);
    }

    // Remove a user from the system
    protected void removeUser() {
        int userID = getUserIDInput("Enter User ID to remove:");
        Person user = clubDatabase.getUser(userID, "");
        if (user != null) {
            JOptionPane.showMessageDialog(this, "User removed successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "User ID not found.");
        }
    }

    // Send renewal notices to all members
    protected void sendRenewalNotices() {
        JOptionPane.showMessageDialog(this, "Renewal notices sent to all members.");
    }

    // Utility methods for input handling
    protected int getUserIDInput(String message) {
        String input = JOptionPane.showInputDialog(this, message);
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a numeric User ID.");
            return -1;
        }
    }

    protected int getIntegerInput(String message) {
        String input = JOptionPane.showInputDialog(this, message);
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a number.");
            return -1;
        }
    }
}
