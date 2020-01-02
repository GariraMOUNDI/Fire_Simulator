package persistence.data;

import org.bson.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String username, password, email, helpWord;
    private int diamonds, XP, gold;
    private List<Item> userItems;
    private List<Character> userCharacters;

    public User(String username, String password, String email, String helpWord) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.helpWord = helpWord;
        this.userItems = new ArrayList<>();
        this.userCharacters = new ArrayList<>();
        this.diamonds = 0;
        this.XP = 0;
        this.gold = 0;
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
    public void addItem(Item i) {
        userItems.add(i);
    }
    public List<Item> getItems() {
        return userItems;
    }
    public void addCharacter(Character c) { userCharacters.add(c); }
    public List<Character> getCharacters() { return userCharacters; }

    public int getDiamonds() {
        return diamonds;
    }

    public int getXP() {
        return XP;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void setDiamonds(int diamonds) {
        this.diamonds = diamonds;
    }

    public void setXP(int XP) {
        this.XP = XP;
    }
}
