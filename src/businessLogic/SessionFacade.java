package businessLogic;

import persistence.factories.DAOFactory;
import persistence.dao.MongoDBDAO;
import persistence.data.User;
import persistence.factories.DAOType;
import persistence.interfaces.DAO;
import ui.interfaces.LoginInterface;

public class SessionFacade {
    private LoginInterface loginIF;
    private DAO dao;
    private User userLoggedIn;

    public SessionFacade(LoginInterface loginIF) {
        this.loginIF = loginIF;
        this.dao = DAOFactory.getInstance().createDAO(DAOType.User);
    }

    public void login(String username, String password){
        checkCredentials((User) dao.getDataById("username", username), password);
    }

    public void register(String username, String password, String email) {

    }

    private void checkCredentials(User user, String password) {
        if (user != null && user.getPassword().equals(password)) setUserLoggedIn(user);
        else loginIF.printResults("Incorrect username or password.");
    }

    private void setUserLoggedIn(User user) {
        this.userLoggedIn = user;
    }

    public User getUser(){
        return userLoggedIn;
    }

}
