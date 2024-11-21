public abstract class Person {
    private int userID;
    private String password;
    private String email;
    private String firstName;
    private String lastName;

    public Person(int userID, String password, String email, String firstName, String lastName) {
        this.userID = userID;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
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

    public String getEmail() {
        return email;
    }

    // Abstract method to be overridden by subclasses
    public abstract String getRole();
}

