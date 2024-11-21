public class User {
    private int userID;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private int privilegeLevel;

    public User(int userID, String password, String email, String firstName, String lastName, int privilegeLevel) {
        this.userID = userID;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.privilegeLevel = privilegeLevel;
    }

    public int getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getPrivilegeLevel() {
        return privilegeLevel;
    }
}

