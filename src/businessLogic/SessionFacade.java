package businessLogic;

import persistence.AbstractFactory;
import persistence.dao.UserDAO;
import ui.interfaces.LoginInterface;
public class SessionFacade {
    private LoginInterface loginIF;
    private UserDAO dao;

    public SessionFacade(LoginInterface loginIF) {
        this.loginIF = loginIF;

    }

    public boolean login(String username, String password){


        return true;
    }
}
