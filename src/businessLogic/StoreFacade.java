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

/**
 * The type Store facade.
 */
public class StoreFacade {
    private LoginInterface loginIF;
    private SessionFacade SF = null;
    private DAO daoItem, daoCharacter;
    private User userLoggedIn;
    private static StoreFacade instance = null;
    private List<Item> storeItems;
    private List<Character> storeCharacters;
    private int iIndex = 0, cIndex = 0;

    /**
     * Get the instance of store facade.
     *
     * @param loginIF the login interface
     * @return the store facade instance
     */
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

    /**
     * Gets store items.
     *
     * @return the store items
     */
    public List<Item> getStoreItems() {
        return storeItems;
    }

    /**
     * Gets store characters.
     *
     * @return the store characters
     */
    public List<Character> getStoreCharacters() {
        return storeCharacters;
    }

    /**
     * Gets next item.
     *
     * @return the next item
     */
    public Item getNextItem() {
        iIndex = (iIndex + 1) % (storeItems.size());
        return getItem();
    }

    /**
     * Gets prev item.
     *
     * @return the prev item
     */
    public Item getPrevItem() {
        iIndex = (iIndex - 1 + storeItems.size()) % (storeItems.size());
        return getItem();
    }

    /**
     * Gets item located at the saved index.
     *
     * @return the item
     */
    public Item getItem() {
        return storeItems.get(iIndex);
    }


    /**
     * Gets next character.
     *
     * @return the next character
     */
    public Character getNextCharacter() {
        cIndex = (cIndex + 1) % (storeCharacters.size());
        return getCharacter();
    }

    /**
     * Gets prev character.
     *
     * @return the prev character
     */
    public Character getPrevCharacter() {
        cIndex = (cIndex - 1 + storeCharacters.size()) % (storeCharacters.size());
        return getCharacter();
    }

    /**
     * Gets character.
     *
     * @return the character located at the saved index
     */
    public Character getCharacter() {
        return storeCharacters.get(cIndex);
    }

    /**
     * Gets the current user.
     *
     * @return the user
     */
    public User getUser() {
        if (SF != null) {
            return SF.getUserLoggedIn();
        }
        return null;
    }

    /**
     * User owns item.
     *
     * @param i the item
     * @return true or false
     */
    public boolean userOwnsItem(Item i) {
        for (Item e : getUser().getItems()) {
            if (i.getName().equals(e.getName())) return true;
        }
        return false;
    }

    /**
     * Handle item purchase.
     *
     * @param i the item
     */
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

    /**
     * User owns character boolean.
     *
     * @param c the character
     * @return true or false
     */
    public boolean userOwnsCharacter(Character c) {
        for (Character e : getUser().getCharacters()) {
            if (c.getName().equals(e.getName())) return true;
        }
        return false;
    }

    /**
     * Handle character purchase.
     *
     * @param c the character
     */
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
