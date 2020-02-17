package people;

public class Member extends User {
    public Member(String username, String password) {
        super(username, password);
    }

    public Member() {
    }

    @Override
    public String toString() {
        return "Member{}";
    }
}
