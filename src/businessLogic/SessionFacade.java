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

public class SessionFacade {
    private LoginInterface loginIF;
    private DAO dao;
    private User userLoggedIn;
    private List<String> others, friends;
    private List<Character> characters;
    private static SessionFacade instance = null;
    private int characterIndex;

    public static SessionFacade getInstance(LoginInterface loginIF){
        if (instance == null)
            instance = new SessionFacade(loginIF);
        return instance;
    }

    private SessionFacade(LoginInterface loginIF) {
        this.loginIF = loginIF;
        dao = MongoDBDAOFactory.getInstance().createDAO(DAOType.User);
    }

    public boolean exists(String type, String credential) {
        return dao.getDataById(type, credential) instanceof User;
    }

    public boolean login(String username, String password){
        return checkCredentials((User) dao.getDataById("username", username), password);
    }

    public void logout() {
        userLoggedIn = null;
    }

    public void register(String username, String password, String email, String helpWord) {
        User user = new User(username, password, email, helpWord);
        dao.insertData(user);
        setUserLoggedIn((User) dao.getDataById("username",username));
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

    public void setUserLoggedIn(User user) {
        this.userLoggedIn = user;
    }

    public User getUserLoggedIn(){
        return userLoggedIn;
    }

    // Methods for the management of friends
    public void updateUser(){
        dao.updateData(userLoggedIn);
    }

    public List<String> getUserLoggedInFriends(){
        return userLoggedIn.getFriends();
    }

    public void addFriendToUserLoggedIn(String arg){
        userLoggedIn.addFriend(arg);
    }

    public void removeFriendToUserLoggedIn(String arg){
        userLoggedIn.removeFriend(arg);
    }

    public List<String> getOtherUser(){
        others = new ArrayList<>();
        friends = getUserLoggedInFriends();
        for(User other : (List<User>) dao.getAllData()){
            if (!friends.contains(other.getUsername()) && !other.getUsername().equals(userLoggedIn.getUsername()))
                others.add(other.getUsername());
        }
        return others;
    }

    public List<Item> getUserLoggedInItems(){
        return userLoggedIn.getItems();
    }

    public Character getCharacterAt(int characterIndex){
        try{
            return getCharacters().get(characterIndex);
        }catch(IndexOutOfBoundsException e){
            return null;
        }
    }

    public List<Character> getCharacters(){
        return userLoggedIn.getCharacters();
    }

    public Character nextCharacter() {
        characterIndex = (characterIndex + 1) % getCharacters().size();
        return getCharacterAt(characterIndex);
    }

    public Character previousCharacter() {
        characterIndex = (characterIndex - 1 + getCharacters().size()) % (getCharacters().size());
        return getCharacterAt(characterIndex);
    }

    public void deleteAccount() {
        dao.deleteData(userLoggedIn);
        userLoggedIn = null;
    }
}
