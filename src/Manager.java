public class Manager extends Person {

    public Manager(int userID, String password, String email, String firstName, String lastName) {
        super(userID, password, email, firstName, lastName);
    }

    public void generateReport() {
        System.out.println("Manager generating a report.");
    }

    @Override
    public String getRole() {
        return "Manager";
    }
}

