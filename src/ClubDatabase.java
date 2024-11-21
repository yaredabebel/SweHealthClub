import java.util.HashMap;

import java.util.ArrayList;
import java.util.List;

public class ClubDatabase {
    private HashMap<Integer, Person> users;

    public ClubDatabase() {
        users = new HashMap<>();
        // Add some sample users
        addUser(new Member(101, "101", "Jared@example.com", "Jared", "Da Coolest"));
        addUser(new Staff(102, "staff", "RED@example.com", "Red", "Dot"));
        addUser(new Manager(103, "manager", "YaredLeake@gmail.com", "Yared", "Leake"));
    }

    public void addUser(Person user) {
        users.put(user.getUserID(), user);
    }

    public Person getUser(int userID, String password) {
        Person user = users.get(userID);
        if (user != null && (password.isEmpty() || user.getPassword().equals(password))) {
            return user;
        }
        return null;
    }

    // Returns a list of all users
    public List<Person> getAllUsers() {
        return new ArrayList<>(users.values());
    }
}

