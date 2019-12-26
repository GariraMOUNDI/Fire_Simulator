package persistence.factories;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import persistence.dao.MongoDBDAOPost;
import persistence.dao.MongoDBDAOUser;
import persistence.interfaces.DAO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MongoDBDAOFactory {

    private Properties prop = new Properties();

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
        try (InputStream input = new FileInputStream("src/resources/database.properties")) {
            prop.load(input);
            mongoClient = MongoClients.create(prop.getProperty("DBconnectionURL"));
            database = mongoClient.getDatabase("FireBase");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public DAO createDAO(DAOType type) {
        switch (type){
            case User:
                return new MongoDBDAOUser(database);
            case Post:
                return new MongoDBDAOPost(database);
            default: return null;
        }
    }

    public void closeConnection(){
        mongoClient.close();
    }

    public void connectionException(Object arg) {

    }
}
