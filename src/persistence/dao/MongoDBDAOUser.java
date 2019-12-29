package persistence.dao;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import persistence.data.User;
import persistence.interfaces.DAO;

import java.util.ArrayList;
import java.util.List;

public class MongoDBDAOUser implements DAO<User> {

    private MongoCollection<Document> collection;
    private Gson gson = new Gson();
    private BasicDBObject query, up;

    public MongoDBDAOUser(MongoDatabase database){
        collection = database.getCollection("users");
    }

    public User getDataById(String key, String value) {
        query = new BasicDBObject(key,value);
        for (Document doc : collection.find(query)){
            return gson.fromJson(doc.toJson(), User.class);
        }
        return null;
    }

    @Override
    public List<User> getAllData() {
        List<User> allUser = new ArrayList<>();
        for (Document doc : collection.find()){
            allUser.add(gson.fromJson(doc.toJson(), User.class));
        }
        return allUser;
    }

    @Override
    public void updateData(User arg) {
        query = new BasicDBObject("_id",new ObjectId(parseId(arg)));
        up = new BasicDBObject("$set",Document.parse(gson.toJson(arg)));
        collection.findOneAndUpdate(query, up);
    }

    @Override
    public void deleteData(User arg) {
        query = new BasicDBObject("_id",new ObjectId(parseId(arg)));
        collection.findOneAndDelete(query);
    }

    @Override
    public void insertData(User arg) {
        collection.insertOne(Document.parse(gson.toJson(arg)));
    }

    private String parseId(User arg){
        String id = arg.get_id().toString().split("=")[1];
        id = id.substring(0,id.length()-1);
        return id;
    }
}
