public class Citizen extends User {

    public Citizen(String username, String password, String name) {
        super(username, password, name);
    }

    @Override
    public String getRole() { return "Citizen"; }
}
 