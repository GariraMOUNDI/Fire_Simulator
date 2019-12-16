package persistence;

import persistence.dao.*;
import persistence.interfaces.DAO;


public class AbstractFactory {


    protected DAO createDAO(DAOType type){
        DAO dao;
        switch (type){
            case User:
                dao =  new UserDAO();
                break;
            case Tournament:
                dao = new TournamentDAO();
                break;
            default:
                dao = null;
        }
        return dao;
    }
}
