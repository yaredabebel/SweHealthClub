public class Staff extends Person {

    public Staff(int userID, String password, String email, String firstName, String lastName) {
        super(userID, password, email, firstName, lastName);
    }

    public void manageMembership(int memberID) {
        System.out.println("Staff managing membership for Member ID: " + memberID);
    }

    @Override
    public String getRole() {
        return "Staff";
    }
}

