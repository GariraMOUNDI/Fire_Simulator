package persistence.data;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String username, password, email, helpWord;
    private List<String> friends = new ArrayList<>();
    private List<Item> items = new ArrayList<>();
    private Object _id;

    public User(String username, String password, String email, String helpWord) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.helpWord = helpWord;
    }
    public Object get_id(){
        return _id;
    }
    public void set_id(Object _id){
        this._id = _id;
    }
    public String getPassword(){
        return password;
    }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getHelpWord() { return helpWord; }
    public void setUsername(String username){
        this.username = username;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setHelpWord(String help){
        this.helpWord = help;
    }
    public void setPassword(String password){
        this.password = password;
    }

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

    public List getItems(){
        return items;
    }
}
