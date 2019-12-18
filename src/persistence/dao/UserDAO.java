package persistence.dao;

import com.mongodb.client.MongoCollection;
import persistence.data.User;
import persistence.database.ConnectorIF;
import persistence.database.MongoDBConnector;
import persistence.factories.DAOFactory;
import persistence.factories.DAOType;
import persistence.interfaces.DAO;

import java.util.ArrayList;
import java.util.Optional;

public class UserDAO implements DAO<User> {

    private ConnectorIF connector;
    private MongoCollection coll;

    public UserDAO(){
        connector = MongoDBConnector.getMongoConnector();
    }
    public User getByUsername(String username) {
        this.getAll();
        return (User) connector.getDataById("username",username);
    }

    @Override
    public Optional<User> get(User obj) {
        return Optional.empty();
    }

    @Override
    public void save(User obj) {

    }

    @Override
    public void update(User obj) {

    }

    @Override
    public Object getAll() {
        return connector.getAllData("users");
    }

    @Override
    public void delete(User obj) {

    }

}
