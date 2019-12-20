package persistence.data;

import org.bson.Document;

import java.io.Serializable;

public class User {
    private String username, password, email, helpWord;

    public User(String username, String password, String email, String helpWord) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.helpWord = helpWord;
    }

    public String getPassword(){
        return password;
    }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getHelpWord() { return helpWord; }
    public String toString(){
        return username + " : " + password ;
    }


}
