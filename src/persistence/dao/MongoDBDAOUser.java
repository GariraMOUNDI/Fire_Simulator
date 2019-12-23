package persistence.dao;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BSONObject;
import org.bson.Document;
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

    public User getDataById(String key, Object value) {
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
        query = new BasicDBObject("username", arg.getUsername());
        query.append("password",arg.getPassword());

        up = new BasicDBObject();
        up.append("$set",Document.parse(gson.toJson(arg)));

        collection.findOneAndUpdate(query, up);
        System.out.println("Object updated !!!");
    }

    @Override
    public void deleteData(User arg) {
        query = new BasicDBObject("username",arg.getUsername());
        query.append("password",arg.getPassword());

        collection.findOneAndDelete(query);
        System.out.println("Object deleted !!!");
    }

    @Override
    public void insertData(User arg) {
        collection.insertOne(Document.parse(gson.toJson(arg)));
    }
}
