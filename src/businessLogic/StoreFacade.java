package businessLogic;

import com.google.gson.Gson;
import persistence.data.Character;
import persistence.data.Item;
import persistence.data.User;
import persistence.factories.DAOType;
import persistence.factories.MongoDBDAOFactory;
import persistence.interfaces.DAO;
import ui.interfaces.LoginInterface;

import java.util.ArrayList;
import java.util.List;

public class StoreFacade {
    private LoginInterface loginIF;
    private SessionFacade SF = null;
    private DAO daoItem, daoCharacter;
    private User userLoggedIn;
    private Gson gson = new Gson();
    private static StoreFacade instance = null;
    private List<Item> storeItems;
    private List<Character> storeCharacters;
    private int iIndex = -1, cIndex = -1;

    public static StoreFacade getInstance(LoginInterface loginIF){
        if (instance == null)
            instance = new StoreFacade(loginIF);
        return instance;
    }

    private StoreFacade(LoginInterface loginIF) {
        this.loginIF = loginIF;
        SF = SessionFacade.getInstance(loginIF);
        daoItem = MongoDBDAOFactory.getInstance().createDAO(DAOType.Item);
        daoCharacter = MongoDBDAOFactory.getInstance().createDAO(DAOType.Character);
        storeItems = (List<Item>) daoItem.getAllData();
        storeCharacters = (List<Character>) daoCharacter.getAllData();
    }

    public List<Item> getStoreItems() {
        return storeItems;
    }

    public List<Character> getStoreCharacters() {
        return storeCharacters;
    }

    public Item getNextItem() {
        iIndex = (iIndex + 1) % (storeItems.size());
        return getItem(iIndex);
    }
    public Item getPrevItem() {
        iIndex = (iIndex - 1 + storeItems.size()) % (storeItems.size());
        return getItem(iIndex);
    }

    public Item getItem(int index) {
        return storeItems.get(index);
    }


    public Character getNextCharacter() {
        cIndex = (cIndex + 1) % (storeCharacters.size());
        return getCharacter(cIndex);
    }
    public Character getPrevCharacter() {
        cIndex = (cIndex - 1 + storeCharacters.size()) % (storeCharacters.size());
        return getCharacter(cIndex);
    }

    public Character getCharacter(int index) {
        return storeCharacters.get(index);
    }

    public User getUser() {
        if (SF != null) {
            return SF.getUserLoggedIn();
        }
        return null;
    }

    public boolean userOwnsItem(Item i) {
        for (Item e : getUser().getItems()) {
            if (i.getName().equals(e.getName())) return true;
        }
        return false;
    }

    public void handleItemPurchase(Item i) {
        getUser().addItem(i);
        SF.updateUser();
    }
    public boolean userOwnsCharacter(Character c) {
        for (Character e : getUser().getCharacters()) {
            if (c.getName().equals(e.getName())) return true;
        }
        return false;
    }

    public void handleCharacterPurchase(Character c) {
        getUser().addCharacter(c);
        SF.updateUser();
    }

//    public void setUp() {
//        List<Character> l = new ArrayList<>();
//        l.add(new Character("resources/icons/character.png", 5, "Wario"));
//        l.add(new Character("resources/icons/character2.png", 7, "Ice guy"));
//        l.add(new Character("resources/icons/character3.png", 9, "Snake"));
//        l.add(new Character("resources/icons/character4.png", 5, "Ninja"));
//        for (Character c : l) {
//            daoCharacter.insertData(c);
//        }
//    }
}
