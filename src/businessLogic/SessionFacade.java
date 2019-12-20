package businessLogic;

import com.google.gson.Gson;
import persistence.factories.MongoDBDAOFactory;
import persistence.data.User;
import persistence.interfaces.DAOType;
import persistence.interfaces.DAO;
import ui.interfaces.LoginInterface;

public class SessionFacade {
    private LoginInterface loginIF;
    private DAO dao;
    private User userLoggedIn;
    private Gson gson = new Gson();

    public SessionFacade(LoginInterface loginIF) {
        this.loginIF = loginIF;
        dao = MongoDBDAOFactory.getInstance().createDAO(DAOType.User);
    }

    public void login(String username, String password){
        checkCredentials((User) dao.getDataById("username", username), password);
    }

    public void register(String username, String password, String email, String helpWord) {
        dao.insertData(new User(username, password, email, helpWord));
    }

    private void checkCredentials(User user, String password) {
        if (user != null && user.getPassword().equals(password)){
            setUserLoggedIn(user);
            loginIF.printResults("Done !!!");
        }
        else loginIF.printResults("Incorrect username or password.");
    }

    private void setUserLoggedIn(User user) {
        this.userLoggedIn = user;
    }

    public User getUser(){
        return userLoggedIn;
    }

}
