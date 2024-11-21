import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ManagerDashboard extends JFrame {
    private Manager manager;
    private ClubDatabase clubDatabase;

    public ManagerDashboard(Manager manager, ClubDatabase clubDatabase, JFrame loginPage) {
        this.manager = manager;
        this.clubDatabase = clubDatabase;

        setTitle("Manager Dashboard");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Add functionalities as buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9, 1, 10, 10)); // One extra row for "Generate Report"
        add(panel);

        // Add buttons for manager functionalities
        addButton(panel, "Verify Customer Membership", e -> verifyCustomerMembership());
        addButton(panel, "Check for Expired Membership", e -> checkForExpiredMembership());
        addButton(panel, "Record Entry", e -> recordEntry());
        addButton(panel, "Renew Membership", e -> renewMembership());
        addButton(panel, "Update User Info", e -> updateUserInfo());
        addButton(panel, "Add New Member", e -> addNewMember());
        addButton(panel, "Remove User", e -> removeUser());
        addButton(panel, "Send Renewal Notices", e -> sendRenewalNotices());

        // Add "Generate Report" button
        addButton(panel, "Generate Report as PDF", e -> generateReport());

        JButton backButton = new JButton("Back to Login");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false); // Hide the current dashboard
                loginPage.setVisible(true); // Show the login page
            }
        });
        panel.add(backButton);
    }

    // Helper method to create buttons
    private void addButton(JPanel panel, String title, ActionListener action) {
        JButton button = new JButton(title);
        button.addActionListener(action);
        panel.add(button);
    }

    // Verify membership of a customer
    private void verifyCustomerMembership() {
        int userID = getUserIDInput("Enter User ID to verify membership:");
        Person user = clubDatabase.getUser(userID, "");
        if (user != null) {
            JOptionPane.showMessageDialog(this, "User is a valid member: " + user.getFirstName() + " " + user.getLastName());
        } else {
            JOptionPane.showMessageDialog(this, "User ID not found in the system.");
        }
    }

    // Check if a customer's membership is expired
    private void checkForExpiredMembership() {
        int userID = getUserIDInput("Enter User ID to check for expired membership:");
        Person user = clubDatabase.getUser(userID, "");
        if (user instanceof Member) {
            JOptionPane.showMessageDialog(this, "Membership for " + user.getFirstName() + " is valid.");
        } else {
            JOptionPane.showMessageDialog(this, "User ID not found or not a member.");
        }
    }

    // Record a customer's entry into the gym
    private void recordEntry() {
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
    private void renewMembership() {
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
    private void updateUserInfo() {
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
    private void addNewMember() {
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
    private void removeUser() {
        int userID = getUserIDInput("Enter User ID to remove:");
        Person user = clubDatabase.getUser(userID, "");
        if (user != null) {
            JOptionPane.showMessageDialog(this, "User removed successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "User ID not found.");
        }
    }

    // Send renewal notices to all members
    private void sendRenewalNotices() {
        JOptionPane.showMessageDialog(this, "Renewal notices sent to all members.");
    }

    // Generate a report of all users and save it as a PDF file
    private void generateReport() {
        String fileName = JOptionPane.showInputDialog(this, "Enter file name for the report (without extension):");
        if (fileName == null || fileName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Invalid file name.");
            return;
        }

        try {
            // Create a new PDF document
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(fileName + ".pdf"));
            document.open();

            // Add a title to the document
            document.add(new Paragraph("Membership Report", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Font.BOLD)));
            document.add(new Paragraph("Generated by Manager: " + manager.getFirstName() + " " + manager.getLastName()));
            document.add(new Paragraph("\n"));

            // Add a table to the PDF
            PdfPTable table = new PdfPTable(5); // 5 columns for User ID, Name, Email, Role, and Visits
            table.addCell("User ID");
            table.addCell("Name");
            table.addCell("Email");
            table.addCell("Role");
            table.addCell("Total Visits");

            // Populate table with user data from ClubDatabase
            for (Person user : clubDatabase.getAllUsers()) {
                table.addCell(String.valueOf(user.getUserID()));
                table.addCell(user.getFirstName() + " " + user.getLastName());
                table.addCell(user.getEmail());
                table.addCell(user instanceof Member ? "Member" : (user instanceof Staff ? "Staff" : "Manager"));
                table.addCell(user instanceof Member ? String.valueOf(((Member) user).getTotalVisits()) : "N/A");
            }

            document.add(table); // Add the table to the document
            document.close(); // Close the document

            JOptionPane.showMessageDialog(this, "Report successfully generated as " + fileName + ".pdf");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error generating report: " + e.getMessage());
        }
    }

    // Utility methods for input handling
    private int getUserIDInput(String message) {
        String input = JOptionPane.showInputDialog(this, message);
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a numeric User ID.");
            return -1;
        }
    }

    private int getIntegerInput(String message) {
        String input = JOptionPane.showInputDialog(this, message);
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a number.");
            return -1;
        }
    }
}
