package persistence.factories;

import persistence.dao.*;
import persistence.database.MongoDBConnector;
import persistence.database.PostgreSQLConnector;
import persistence.interfaces.DAO;


public class AbstractFactory {


    public Object createDAO(DAOType type){
        Object val = null;
        switch (type){
            case User:
                val =  new UserDAO();
                break;
            case Tournament:
                val = new TournamentDAO();
                break;
            case Mongo:
                val = MongoDBConnector.getMongoConnector();
                break;
            case Postgre:
                val = new PostgreSQLConnector();
                break;
        }
        return val;
    }
}
