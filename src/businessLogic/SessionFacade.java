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

    public void login(String username, String password){
        temp = dao.getByUsername(username);
        checkCredentials(temp, password);
    }

    private void checkCredentials(User user, String password) {
            if (user == null )
                loginIF.printResults("This user is not registered !!!");
            else
                if (!(user.getPassword().equals(password)))
                    loginIF.printResults("Incorrect password !!!");
                else {
                    loginIF.printResults(temp);
                    setUserLoggedIn(user);
                }
    }

    private void setUserLoggedIn(User user) {
        this.userLoggedIn = user;
    }

    public User getUser(){
        return userLoggedIn;
    }

}
