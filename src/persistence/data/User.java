package persistence.data;

import org.bson.Document;

import java.io.Serializable;

public class User {
    private String username, password, email;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getPassword(){
        return password;
    }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String toString(){
        return username + " : " + password ;
    }


}
