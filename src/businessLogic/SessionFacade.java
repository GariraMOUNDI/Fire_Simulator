package businessLogic;

import persistence.AbstractFactory;
import persistence.DAOFactory;
import persistence.dao.UserDAO;
import persistence.data.User;
import ui.interfaces.LoginInterface;

public class SessionFacade {
    private LoginInterface loginIF;
    private UserDAO dao;
    private DAOFactory factory;
    private User userLoggedIn;

    public SessionFacade(LoginInterface loginIF) {
        this.loginIF = loginIF;
    }

    public boolean login(String username, String password){
        User tempUser = dao.getByUsername(username);
        return checkCredentials(tempUser, password);
    }

    private boolean checkCredentials(User user, String password) {
        if (user.getPassword().equals(password)){
            setUserLoggedIn(user);
            return true;
        }
        return false;
    }

    private void setUserLoggedIn(User user) {
        this.userLoggedIn = user;
    }

    public User getUser(){
        return userLoggedIn;
    }
}
