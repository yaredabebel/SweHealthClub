public class LoginPortal {
    private ClubDatabase clubDatabase;

    public LoginPortal(ClubDatabase clubDatabase) {
        this.clubDatabase = clubDatabase;
    }

    public Person login(int userID, String password) {
        Person user = clubDatabase.getUser(userID, password);
        if (user != null) {
            System.out.println("Login successful for User ID: " + userID);
            return user;
        }
        System.out.println("Invalid credentials for User ID: " + userID);
        return null;
    }


    public ClubDatabase getClubDatabase() {
        return clubDatabase;
    }
}
