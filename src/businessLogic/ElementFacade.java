package businessLogic;

import persistence.data.Element;
import persistence.data.Terrain;
import persistence.data.TypeElementEnum;
import persistence.factories.DAOType;
import persistence.factories.MongoDBDAOFactory;
import persistence.interfaces.DAO;
import ui.interfaces.LoginInterface;

import java.util.List;

public class ElementFacade {
    private LoginInterface loginIF;
    private DAO dao;
    private Element currentElement;
    private static ElementFacade instance = null;

    private ElementFacade(LoginInterface loginIF) {
        this.loginIF = loginIF;
        dao = MongoDBDAOFactory.getInstance().createDAO(DAOType.Element);
    }

    public static ElementFacade getInstance(LoginInterface loginIF){
        if (instance==null){
            instance = new ElementFacade(loginIF);
        }
        return instance;
    }

    public void createElement(String elementName, int flammability, String color, TypeElementEnum type, String username){
        Element newElement = new Element(elementName,flammability,color,type, username);
        dao.insertData(newElement);
    }

    public Element getCurrentElement() {
        return currentElement;
    }

    public void setCurrentElement(Element currentElement) {
        this.currentElement = currentElement;
    }

    public List<Element> getUserElements(String username){
        return (List<Element>) dao.getDataById("username", username);
    }

    public List<Element> getBasicsElements(){
        return (List<Element>) dao.getDataById("basic", true);
    }

    public void deleteElement(Element element) {
        dao.deleteData(element);
    }
}
