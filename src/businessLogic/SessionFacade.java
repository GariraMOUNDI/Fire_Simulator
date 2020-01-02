package businessLogic;

import com.google.gson.Gson;
import persistence.factories.MongoDBDAOFactory;
import persistence.data.User;
import persistence.factories.DAOType;
import persistence.interfaces.DAO;
import ui.interfaces.LoginInterface;

public class SessionFacade {
    private LoginInterface loginIF;
    private DAO dao;
    private User userLoggedIn;
    private Gson gson = new Gson();
    private static SessionFacade instance = null;

    public static SessionFacade getInstance(LoginInterface loginIF){
        if (instance == null)
            instance = new SessionFacade(loginIF);
        return instance;
    }

    private SessionFacade(LoginInterface loginIF) {
        this.loginIF = loginIF;
        dao = MongoDBDAOFactory.getInstance().createDAO(DAOType.User);
    }

    public boolean exists(String type, String credential) {
        return dao.getDataById(type, credential) instanceof User;
    }

    public boolean login(String username, String password){
        return checkCredentials((User) dao.getDataById("username", username), password);
    }

    public void logout() {
        userLoggedIn = null;
    }

    public void register(String username, String password, String email, String helpWord) {
        dao.insertData(new User(username, password, email, helpWord));
        login(username,password);
    }

    private boolean checkCredentials(User user, String password) {
        if (user != null && user.getPassword().equals(password)){
            setUserLoggedIn(user);
            loginIF.printResults("Done !!!");
            return true;
        }
        else {
            loginIF.printResults("Incorrect username or password.");
            return false;
        }
    }

    private void setUserLoggedIn(User user) {
        this.userLoggedIn = user;
    }

    public User getUser(){
        return userLoggedIn;
    }

    public void updateUser() {
        dao.updateData(getUser());
    }

}
