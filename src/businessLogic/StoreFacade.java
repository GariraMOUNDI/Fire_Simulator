package businessLogic;

import com.google.gson.Gson;
import javafx.scene.control.Alert;
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
    private int iIndex = 0, cIndex = 0;

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
        userLoggedIn = SF.getUserLoggedIn();
    }

    public List<Item> getStoreItems() {
        return storeItems;
    }

    public List<Character> getStoreCharacters() {
        return storeCharacters;
    }

    public Item getNextItem() {
        iIndex = (iIndex + 1) % (storeItems.size());
        return getItem();
    }
    public Item getPrevItem() {
        iIndex = (iIndex - 1 + storeItems.size()) % (storeItems.size());
        return getItem();
    }

    public Item getItem() {
        return storeItems.get(iIndex);
    }


    public Character getNextCharacter() {
        cIndex = (cIndex + 1) % (storeCharacters.size());
        return getCharacter();
    }
    public Character getPrevCharacter() {
        cIndex = (cIndex - 1 + storeCharacters.size()) % (storeCharacters.size());
        return getCharacter();
    }

    public Character getCharacter() {
        return storeCharacters.get(cIndex);
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
        if (userLoggedIn.getGold() >= i.getPrice()) {
            userLoggedIn.setGold(userLoggedIn.getGold() - i.getPrice());
            getUser().addItem(i);
            SF.updateUser();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("You do not have enough gold to purchase this item!");
            alert.showAndWait();
        }
    }
    public boolean userOwnsCharacter(Character c) {
        for (Character e : getUser().getCharacters()) {
            if (c.getName().equals(e.getName())) return true;
        }
        return false;
    }

    public void handleCharacterPurchase(Character c) {
        if (userLoggedIn.getGold() >= c.getPrice()) {
            userLoggedIn.setGold(userLoggedIn.getGold() - c.getPrice());
            getUser().addCharacter(c);
            SF.updateUser();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("You do not have enough gold to purchase this character!");
            alert.showAndWait();
        }
    }
}
