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


        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10)); // Adjusted layout
        add(panel);


        addButton(panel, "View Membership Details", e -> viewMembershipDetails());
        addButton(panel, "Record Visit", e -> recordVisit());


        JButton backButton = new JButton("Back to Login");
        backButton.addActionListener(e -> {
            setVisible(false); // Hide the current dashboard
            loginPage.setVisible(true); // Show the login page
        });
        panel.add(backButton);
    }


    private void addButton(JPanel panel, String title, ActionListener action) {
        JButton button = new JButton(title);
        button.addActionListener(action);
        panel.add(button);
    }


    private void viewMembershipDetails() {
        JOptionPane.showMessageDialog(this, "Membership details for " + member.getFirstName() + ":\n"
                + "Email: " + member.getEmail() + "\n"+ "Total Visits: " + member.getTotalVisits());
    }



    private void recordVisit() {
        member.visitGym();
        JOptionPane.showMessageDialog(this, "Visit recorded for " + member.getFirstName());
    }
}
