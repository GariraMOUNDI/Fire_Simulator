package persistence.data;

import org.bson.Document;

import java.io.Serializable;

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
    public String toString(){
        return username + " : " + password ;
    }


}
