package persistence.dao;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import persistence.data.User;
import persistence.interfaces.DAO;

import java.util.Optional;

public class MongoDBDAO<T> implements DAO<T> {

    @Override
    public Optional<T> get(T obj) {
        return Optional.empty();
    }

    @Override
    public T getDataById(String key, Object value) {
        return null;
    }

    @Override
    public void save(T obj) {

    }

    @Override
    public void update(T obj) {

    }

    @Override
    public T getAll() {
        return null;
    }

    @Override
    public void delete(T obj) {

    }
}
