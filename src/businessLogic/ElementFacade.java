package businessLogic;

import persistence.factories.DAOType;
import persistence.factories.MongoDBDAOFactory;
import persistence.interfaces.DAO;
import ui.interfaces.LoginInterface;

public class ElementFacade {
    private LoginInterface loginIF;
    private DAO dao;
    private static ElementFacade instance = null;

    public ElementFacade(LoginInterface loginIF) {
        this.loginIF = loginIF;
        dao = MongoDBDAOFactory.getInstance().createDAO(DAOType.Element);
    }

    public static ElementFacade getInstance(LoginInterface loginIF){
        if (instance==null){
            instance = new ElementFacade(loginIF);
        }
        return instance;
    }
}
