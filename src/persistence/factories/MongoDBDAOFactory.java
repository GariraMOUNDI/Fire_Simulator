package persistence.factories;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import persistence.dao.MongoDBDAOUser;
import persistence.interfaces.DAO;
import persistence.interfaces.DAOType;
import persistence.interfaces.MethodFactory;

public class MongoDBDAOFactory implements MethodFactory {

    static MongoDBDAOFactory fac = null;
    public static MongoDBDAOFactory getInstance() {
        if (fac == null) {
            fac = new MongoDBDAOFactory();
        }
        return fac;
    }

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection collection;

    private MongoDBDAOFactory() {
        mongoClient = MongoClients.create("mongodb+srv://admin:FireSimulator2019@firesim-qcpoi.mongodb.net/test?retryWrites=true&w=majority");
        database = mongoClient.getDatabase("FireBase");
    }

    public DAO createDAO(DAOType type) {
        switch (type){
            case User:
                return new MongoDBDAOUser(database);
            default: return null;
        }
    }

    public void closeConnection(){
        mongoClient.close();
    }

    public void connectionException(Object arg) {

    }
}
