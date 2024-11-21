public class Member extends Person {
    private int totalVisits;

    public Member(int userID, String password, String email, String firstName, String lastName) {
        super(userID, password, email, firstName, lastName);
        this.totalVisits = 0;
    }

    public void visitGym() {
        totalVisits++;
        System.out.println(getFirstName() + " " + getLastName() + " visited the gym. Total visits: " + totalVisits);
    }

    public int getTotalVisits() {
        return totalVisits;
    }

    @Override
    public String getRole() {
        return "Member";
    }
}
