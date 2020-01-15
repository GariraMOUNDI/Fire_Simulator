package persistence.data;


import java.util.ArrayList;
import java.util.List;

/**
 * The type User. This class contains all of the attributes related to the user, including his/her list of friends, elements, and characters.
 */
public class User {
    private String username, password, email, helpWord;
    private List<String> friends;
    private List<Item> userItems;
    private List<Character> userCharacters;
    private Object _id;
    private int diamonds = 100, XP = 100, gold = 100, level = 1;


    /**
     * Instantiates a new User.
     *
     * @param username the username. Cannot be modified.
     * @param password the password
     * @param email    the user's email
     * @param helpWord the help word to remember the password
     */
    public User(String username, String password, String email, String helpWord) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.helpWord = helpWord;
        this.userItems = new ArrayList<>();
        this.userCharacters = new ArrayList<>();
        this.friends = new ArrayList<>();
        this.level = 1;
        userCharacters.add(new Character("resources/icons/character2.png",2,"Character 2",3));
        userCharacters.add(new Character("resources/icons/character3.png",4,"Character 3",10));
    }

    /**
     * Gets the ObjectId created by mongodb.
     *
     * @return the object
     */
    public Object get_id(){
        return _id;
    }

    /**
     * Gets the password associated with the user.
     *
     * @return the string
     */
    public String getPassword(){
        return password;
    }

    /**
     * Gets the username belonging to the user.
     *
     * @return the username
     */
    public String getUsername() { return username; }

    /**
     * Gets the email attributed to the user.
     *
     * @return the email
     */
    public String getEmail() { return email; }

    /**
     * Gets the help word.
     *
     * @return the help word
     */
    public String getHelpWord() { return helpWord; }

    /**
     * Set the username attribute.
     *
     * @param username the username
     */
    public void setUsername(String username){
        this.username = username;
    }

    /**
     * Set the email attribute.
     *
     * @param email the email
     */
    public void setEmail(String email){
        this.email = email;
    }

    /**
     * Set the help word.
     *
     * @param help the help word
     */
    public void setHelpWord(String help){
        this.helpWord = help;
    }

    /**
     * Set the password.
     *
     * @param password the password
     */
    public void setPassword(String password){
        this.password = password;
    }

    public String toString(){
        return username + " : " + password ;
    }

    /**
     * Add an item to the user's item list.
     *
     * @param i the item
     */
    public void addItem(Item i) {
        userItems.add(i);
    }

    /**
     * Gets the user's items.
     *
     * @return the items
     */
    public List<Item> getItems() {
        return userItems;
    }

    /**
     * Remove an item passed as a parameter.
     *
     * @param t the item to remove
     */
    public void removeItem(Item t){
        userItems.remove(t);
    }

    /**
     * Add a character to the user's character list.
     *
     * @param c the character to add
     */
    public void addCharacter(Character c) { userCharacters.add(c); }

    /**
     * Get the user's character list.
     *
     * @return the user's characters
     */
    public List<Character> getCharacters() { return userCharacters; }

    /**
     * Remove a character.
     *
     * @param c the character to remove
     */
    public void removeCharacter(Character c){
        userCharacters.remove(c);
    }

    /**
     * Gets diamonds.
     *
     * @return the diamonds
     */
    public int getDiamonds() {
        return diamonds;
    }

    /**
     * Sets diamonds.
     *
     * @param diamonds the diamonds
     */
    public void setDiamonds(int diamonds) {
        this.diamonds = diamonds;
    }

    /**
     * Gets xp.
     *
     * @return the xp
     */
    public int getXP() {
        return XP;
    }

    /**
     * Sets xp.
     *
     * @param XP the xp
     */
    public void setXP(int XP) {
        this.XP = XP;
    }

    /**
     * Gets gold.
     *
     * @return the gold
     */
    public int getGold() {
        return gold;
    }

    /**
     * Sets gold.
     *
     * @param gold the gold
     */
    public void setGold(int gold) { this.gold = gold; }

    /**
     * Gets level.
     *
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets level.
     *
     * @param level the level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Get the user's friends list.
     *
     * @return the list of friends
     */
    public List<String> getFriends(){
        return friends;
    }

    /**
     * Add a friend to the user's friend list.
     *
     * @param arg the friend to add
     */
    public void addFriend(String arg){
        friends.add(0,arg);
    }

    /**
     * Remove a friend from the user's friend list.
     *
     * @param arg the friend to remove
     */
    public void removeFriend(String arg){
        friends.remove(arg);
    }
}
