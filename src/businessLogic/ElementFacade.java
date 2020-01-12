package businessLogic;

import persistence.data.Element;
import persistence.data.Terrain;
import persistence.data.TypeElementEnum;
import persistence.data.User;
import persistence.factories.DAOType;
import persistence.factories.MongoDBDAOFactory;
import persistence.interfaces.DAO;
import ui.interfaces.LoginInterface;

import java.util.List;

/**
 * The type Element facade.
 */
public class ElementFacade {
    private LoginInterface loginIF;
    private DAO dao;
    private Element currentElement;
    private static ElementFacade instance = null;
    private SessionFacade session;

    private ElementFacade(LoginInterface loginIF) {
        this.loginIF = loginIF;
        session = SessionFacade.getInstance(loginIF);
        dao = MongoDBDAOFactory.getInstance().createDAO(DAOType.Element);
    }

    /**
     * Get user logged in.
     *
     * @return the user
     */
    public User getUserLoggedIn(){
        return session.getUserLoggedIn();
    }

    /**
     * Get the instance of element facade.
     *
     * @param loginIF the login if
     * @return the element facade
     */
    public static ElementFacade getInstance(LoginInterface loginIF){
        if (instance==null){
            instance = new ElementFacade(loginIF);
        }
        return instance;
    }

    /**
     * Create element.
     *
     * @param elementName  the element name
     * @param flammability the flammability
     * @param color        the color
     * @param type         the type
     * @param username     the username of the creator
     */
    public void createElement(String elementName, int flammability, String color, TypeElementEnum type, String username){
        Element newElement = new Element(elementName,flammability,color,type, username);
        System.out.println(newElement.getElementName());
        dao.insertData(newElement);
    }

    /**
     * Gets current element.
     *
     * @return the current element
     */
    public Element getCurrentElement() {
        return currentElement;
    }

    /**
     * Sets current element.
     *
     * @param currentElement the current element
     */
    public void setCurrentElement(Element currentElement) {
        this.currentElement = currentElement;
    }

    /**
     * Get user elements list.
     *
     * @param username the username
     * @return the list
     */
    public List<Element> getUserElements(String username){
        return (List<Element>) dao.getDataById("username", username);
    }

    /**
     * Get basics elements list.
     *
     * @return the list
     */
    public List<Element> getBasicsElements(){
        return (List<Element>) dao.getDataById("basic", true);
    }

    /**
     * Get all elements list.
     *
     * @param username the username
     * @return the list
     */
    public List<Element> getAllElements(String username){
        List<Element> result = (List<Element>) dao.getDataById("username", username);
        result.addAll((List<Element>) dao.getDataById("basic", true));
        return result;
    }

    /**
     * Delete element.
     *
     * @param element the element
     */
    public void deleteElement(Element element) {
        dao.deleteData(element);
    }

    public void updateElement() {
        dao.updateData(currentElement);
    }
}
