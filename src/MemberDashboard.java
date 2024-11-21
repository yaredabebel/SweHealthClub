import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MemberDashboard extends JFrame {
    private Member member;
    private ClubDatabase clubDatabase;

    public MemberDashboard(Member member, ClubDatabase clubDatabase, JFrame loginPage) {
        this.member = member;
        this.clubDatabase = clubDatabase;

        setTitle("Member Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel for buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10)); // Adjusted layout
        add(panel);

        // Add buttons for member functionalities
        addButton(panel, "View Membership Details", e -> viewMembershipDetails());
        addButton(panel, "Record Visit", e -> recordVisit());

        // Add "Back" button
        JButton backButton = new JButton("Back to Login");
        backButton.addActionListener(e -> {
            setVisible(false); // Hide the current dashboard
            loginPage.setVisible(true); // Show the login page
        });
        panel.add(backButton);
    }

    // Helper method to create buttons
    private void addButton(JPanel panel, String title, ActionListener action) {
        JButton button = new JButton(title);
        button.addActionListener(action);
        panel.add(button);
    }

    // View membership details
    private void viewMembershipDetails() {
        JOptionPane.showMessageDialog(this, "Membership details for " + member.getFirstName() + ":\n"
                + "Email: " + member.getEmail() + "\n"+ "Total Visits: " + member.getTotalVisits());
    }


    // Record a visit to the gym
    private void recordVisit() {
        member.visitGym();
        JOptionPane.showMessageDialog(this, "Visit recorded for " + member.getFirstName());
    }
}
