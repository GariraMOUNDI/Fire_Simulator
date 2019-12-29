package persistence.dao;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import persistence.data.Item;
import persistence.interfaces.DAO;

public class MongoDBDAOItem implements DAO<Item> {

    private MongoCollection<Document> collection;
    private Gson gson = new Gson();
    private BasicDBObject query, up;

    public MongoDBDAOItem(MongoDatabase database){
        collection = database.getCollection("items");
    }

    @Override
    public Object getAllData() {
        return null;
    }

    @Override
    public Object getDataById(String key, String value) {
        return null;
    }

    @Override
    public void updateData(Item arg) {

    }

    @Override
    public void deleteData(Item arg) {

    }

    @Override
    public void insertData(Item arg) {

    }
}
