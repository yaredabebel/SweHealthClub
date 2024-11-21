public class Main {
    public static void main(String[] args) {
        // Initialize database and login portal
        ClubDatabase clubDatabase = new ClubDatabase();
        LoginPortal loginPortal = new LoginPortal(clubDatabase);

        // Launch login page
        LoginPage loginPage = new LoginPage(clubDatabase);
        Manager manager = new Manager(103, "managerPass", "manager@example.com", "Mark", "Johnson");
        // Create the ManagerDashboard and pass the LoginPage
        ManagerDashboard managerDashboard = new ManagerDashboard(manager, clubDatabase, loginPage);
        loginPage.setVisible(true);
    }


}

