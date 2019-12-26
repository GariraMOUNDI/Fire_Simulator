package persistence.data;

import org.bson.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String username, password, email, helpWord;
    private List<String> friends = new ArrayList<>();

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

    public List<String> getFriends(){
        return friends;
    }
    public void addFriend(String arg){
        friends.add(0,arg);
    }
    public void removeFriend(String arg){
        friends.remove(arg);
    }

}
