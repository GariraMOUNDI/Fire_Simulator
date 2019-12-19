package persistence.factories;

import persistence.dao.MongoDBDAO;
import persistence.data.Tournament;
import persistence.data.User;
import persistence.interfaces.DAO;

public class DAOFactory {
    static DAOFactory fac = null;

    private DAOFactory() {}

    public static DAOFactory getInstance() {
        if (fac == null) {
            fac = new DAOFactory();
        }
        return fac;
    }

    public DAO createDAO(DAOType type) {
        switch (type){
            case User:
                return new MongoDBDAO<User>();
            case Tournament:
                return new MongoDBDAO<Tournament>();
            default: return null;
        }
    }
}
