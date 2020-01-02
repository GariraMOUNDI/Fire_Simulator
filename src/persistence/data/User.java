package persistence.data;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username, password, email, helpWord;
    private List<String> friends = new ArrayList<>();
    private List<Item> items = new ArrayList<>();
    private List<Character> characters = new ArrayList<>();
    private Object _id;
    private int diamonds = 10 , XP = 10, gold = 10, level = 1;

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

    public List<Item> getItems(){
        return items;
    }
    public void addItem(Item arg){
        items.add(0,arg);
    }
    public void removeItem(Item arg){
        items.remove(arg);
    }

    public List<Character> getCharacters(){
        return characters;
    }

    public void addCharacter(Character arg){
        characters.add(0,arg);
    }
    public void removeCharacter(Character arg){
        characters.remove(arg);
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
}
