package persistence.factories;

import com.mongodb.MongoConfigurationException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import javafx.application.Application;
import persistence.dao.*;
import persistence.interfaces.DAO;
import ui.model.ApplicationUI;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The type MongoDB DAO factory.
 */
public class MongoDBDAOFactory {

    private Properties prop = new Properties();

    /**
     * The singleton factory instance.
     */
    static MongoDBDAOFactory fac = null;

    /**
     * Gets the instance of the factory.
     *
     * @return the instance
     */
    public static MongoDBDAOFactory getInstance() {
        if (fac == null) {
            fac = new MongoDBDAOFactory();
        }
        return fac;
    }

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection collection;


    /**
     * The private factory constructor. Also initializes the connection to the mongoDB database.
     */
    private MongoDBDAOFactory(){
        try (InputStream input = new FileInputStream("src/resources/database.properties")) {
            prop.load(input);
            mongoClient = MongoClients.create(prop.getProperty("DBconnectionURL"));
            database = mongoClient.getDatabase("FireBase");
        } catch (IOException ex) {
            System.out.println("Fichier de configuration inexistant !!!");
        } catch( MongoConfigurationException e){
            System.out.println("Erreur de connection !!!");
        }
    }

    /**
     * Create a DAO based on the type passed as a parameter.
     *
     * @param type the type of DAO of type DAOType
     * @return the newly created DAO
     */
    public DAO createDAO(DAOType type) {
        switch (type){
            case Element:
                return new MongoDBDAOElement(database);
            case Terrain:
                return new MongoDBDAOMapManagement(database);
            case User:
                return new MongoDBDAOUser(database);
            case Post:
                return new MongoDBDAOPost(database);
            case Item:
                return new MongoDBDAOItem(database);
            case Character:
                return new MongoDBDAOCharacter(database);
            default: return null;
        }
    }

    /**
     * Close the database connection connection.
     */
    public void closeConnection(){
        if (mongoClient != null)
            mongoClient.close();
    }

    /**
     * Connection exception.
     *
     * @param arg the arg
     */
    public void connectionException(Object arg) {

    }
}
