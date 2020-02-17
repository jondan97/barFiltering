package people;

public class Admin extends User {

    public Admin(String username, String password) {
        super(username, password);
    }

    public Admin() {
    }

    @Override
    public String toString() {
        return "Admin{}";
    }
}
