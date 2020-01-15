package persistence.dao;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import persistence.data.Item;
import persistence.interfaces.DAO;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Mongodao item. This class is in charge of all connections to our MongoDB collection 'items'
 */
public class MongoDBDAOItem implements DAO<Item> {

    private MongoCollection<Document> collection;
    private Gson gson = new Gson();
    private BasicDBObject query, up;

    /**
     * Instantiates a new MongoDB DAO item.
     *
     * @param database the database of the project
     */
    public MongoDBDAOItem(MongoDatabase database){
        collection = database.getCollection("items");
    }

    @Override
    public List<Item> getAllData() {
        List<Item> allUser = new ArrayList<>();
        for (Document doc : collection.find()){
            allUser.add(gson.fromJson(doc.toJson(), Item.class));
        }
        return allUser;
    }

    @Override
    public Item getDataById(String key, Object value) {
        query = new BasicDBObject(key,value);
        for (Document doc : collection.find(query)){
            return gson.fromJson(doc.toJson(), Item.class);
        }
        return null;
    }

    @Override
    public void updateData(Item arg) {
        query = new BasicDBObject("_id", arg.getId());

        up = new BasicDBObject();
        up.append("$set",Document.parse(gson.toJson(arg)));

        collection.findOneAndUpdate(query, up);
    }

    @Override
    public void deleteData(Item arg) {
        query = new BasicDBObject("_id",arg.getId());

        collection.findOneAndDelete(query);
    }

    @Override
    public void insertData(Item arg) {
        collection.insertOne(Document.parse(gson.toJson(arg)));
    }
}
