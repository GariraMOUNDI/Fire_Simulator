package persistence.data;


import java.util.ArrayList;
import java.util.List;

public class User {
    private String username, password, email, helpWord;
    private List<String> friends;
    private List<Item> userItems;
    private List<Character> userCharacters;
    private Object _id;
    private int diamonds, XP, gold, level;


    public User(String username, String password, String email, String helpWord) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.helpWord = helpWord;
        this.userItems = new ArrayList<>();
        this.userCharacters = new ArrayList<>();
        this.friends = new ArrayList<>();
        this.diamonds = 10;
        this.XP = 10;
        this.gold = 10;
        this.level = 1;
    }

    public Object get_id(){
        return _id;
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

    public void addItem(Item i) {
        userItems.add(i);
    }

    public List<Item> getItems() {
        return userItems;
    }

    public void removeItem(Item arg){
        userItems.remove(arg);
    }

    public void addCharacter(Character c) { userCharacters.add(c); }

    public List<Character> getCharacters() { return userCharacters; }

    public void removeCharacter(Character arg){
        userCharacters.remove(arg);
    }

    public int getDiamonds() {
        return diamonds;
    }

    public void setDiamonds(int diamonds) {
        this.diamonds = diamonds;
    }

    public int getXP() {
        return XP;
    }

    public void setXP(int XP) {
        this.XP = XP;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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
