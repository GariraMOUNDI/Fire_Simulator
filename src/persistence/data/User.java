package persistence.data;

public class User {
    private String username, password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword(){
        return password;
    }
    public String getUsername() { return username; }
}
