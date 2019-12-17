package businessLogic;

import persistence.factories.DAOFactory;
import persistence.factories.DAOType;
import persistence.dao.UserDAO;
import persistence.data.User;
import ui.interfaces.LoginInterface;

public class SessionFacade {
    private LoginInterface loginIF;
    private UserDAO dao;
    private DAOFactory factory;
    private User userLoggedIn;
    private User temp;

    public SessionFacade(LoginInterface loginIF) {
        this.loginIF = loginIF;
        dao = (UserDAO) DAOFactory.getFactory().createDAO(DAOType.User);
    }

    public boolean login(String username, String password){
        temp = dao.getByUsername(username);
        return checkCredentials(temp, password);
    }

    private boolean checkCredentials(User user, String password) {
            if (user == null || !(user.getPassword().equals(password))){
                loginIF.getResult("Error");
                return false;
            }else {
                loginIF.getResult(temp);
                setUserLoggedIn(user);
                return true;
            }
    }

    private void setUserLoggedIn(User user) {
        this.userLoggedIn = user;
    }

    public User getUser(){
        return userLoggedIn;
    }

}
