package businessLogic;

import persistence.data.Character;
import persistence.data.Item;
import persistence.factories.MongoDBDAOFactory;
import persistence.data.User;
import persistence.factories.DAOType;
import persistence.interfaces.DAO;
import ui.interfaces.LoginInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Session facade.
 */
public class SessionFacade {
    private LoginInterface loginIF;
    private DAO dao;
    private User userLoggedIn;
    private List<String> others, friends;
    private List<Character> characters;
    private static SessionFacade instance = null;
    private int characterIndex;

    /**
     * Get instance session facade.
     *
     * @param loginIF the login if
     * @return the session facade
     */
    public static SessionFacade getInstance(LoginInterface loginIF){
        if (instance == null)
            instance = new SessionFacade(loginIF);
        return instance;
    }

    private SessionFacade(LoginInterface loginIF) {
        this.loginIF = loginIF;
        dao = MongoDBDAOFactory.getInstance().createDAO(DAOType.User);
    }

    /**
     * Exists. Checks if the user already exists in the database
     *
     * @param type       the type
     * @param credential the credential
     * @return the boolean
     */
    public boolean exists(String type, String credential) {
        return dao.getDataById(type, credential) instanceof User;
    }

    /**
     * Login.
     *
     * @param username the username
     * @param password the password
     * @return true or false depending on the success of the login
     */
    public boolean login(String username, String password){
        return checkCredentials((User) dao.getDataById("username", username), password);
    }

    /**
     * Logout.
     */
    public void logout() {
        userLoggedIn = null;
    }

    /**
     * Register.
     *
     * @param username the username
     * @param password the password
     * @param email    the email
     * @param helpWord the help word
     */
    public void register(String username, String password, String email, String helpWord) {

        dao.insertData(new User(username, password, email, helpWord));
        login(username,password);
    }

    private boolean checkCredentials(User user, String password) {
        if (user != null && user.getPassword().equals(password)){
            setUserLoggedIn(user);
            loginIF.printResults("Done !!!");
            return true;
        }
        else {
            loginIF.printResults("Incorrect username or password.");
            return false;
        }
    }

    /**
     * Sets user logged in.
     *
     * @param user the user
     */
    public void setUserLoggedIn(User user) {
        this.userLoggedIn = user;
    }

    /**
     * Get user logged in user.
     *
     * @return the user
     */
    public User getUserLoggedIn(){
        return userLoggedIn;
    }

    /**
     * Update user.
     */
// Methods for the management of friends
    public void updateUser(){
        dao.updateData(userLoggedIn);
    }

    /**
     * Get user logged in friends list.
     *
     * @return the list
     */
    public List<String> getUserLoggedInFriends(){
        return userLoggedIn.getFriends();
    }

    /**
     * Add friend to user logged in.
     *
     * @param arg the arg
     */
    public void addFriendToUserLoggedIn(String arg){
        userLoggedIn.addFriend(arg);
    }

    /**
     * Remove friend to user logged in.
     *
     * @param arg the arg
     */
    public void removeFriendToUserLoggedIn(String arg){
        userLoggedIn.removeFriend(arg);
    }

    /**
     * Get other user list.
     *
     * @return the list
     */
    public List<String> getOtherUser(){
        others = new ArrayList<>();
        friends = getUserLoggedInFriends();
        for(User other : (List<User>) dao.getAllData()){
            if (!friends.contains(other.getUsername()) && !other.getUsername().equals(userLoggedIn.getUsername()))
                others.add(other.getUsername());
        }
        return others;
    }

    /**
     * Get user logged in items list.
     *
     * @return the list
     */
    public List<Item> getUserLoggedInItems(){
        return userLoggedIn.getItems();
    }

    /**
     * Get character at specified index.
     *
     * @param characterIndex the character index
     * @return the located character, or null
     */
    public Character getCharacterAt(int characterIndex){
        try{
            return getCharacters().get(characterIndex);
        }catch(IndexOutOfBoundsException e){
            return null;
        }
    }

    /**
     * Get characters list.
     *
     * @return the list of characters
     */
    public List<Character> getCharacters(){
        return userLoggedIn.getCharacters();
    }

    /**
     * Get next character.
     *
     * @return the character
     */
    public Character nextCharacter() {
        characterIndex = (characterIndex + 1) % getCharacters().size();
        return getCharacterAt(characterIndex);
    }

    /**
     * Get previous character.
     *
     * @return the character
     */
    public Character previousCharacter() {
        characterIndex = (characterIndex - 1 + getCharacters().size()) % (getCharacters().size());
        return getCharacterAt(characterIndex);
    }

    /**
     * Delete account.
     */
    public void deleteAccount() {
        dao.deleteData(userLoggedIn);
        userLoggedIn = null;
    }
}
