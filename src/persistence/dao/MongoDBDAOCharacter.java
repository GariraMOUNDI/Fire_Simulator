package persistence.dao;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import persistence.data.Character;
import persistence.data.User;
import persistence.interfaces.DAO;

import java.util.ArrayList;
import java.util.List;

public class MongoDBDAOCharacter implements DAO<Character> {

    private MongoCollection<Document> collection;
    private Gson gson = new Gson();
    private BasicDBObject query, up;

    public MongoDBDAOCharacter(MongoDatabase database){
        collection = database.getCollection("characters");
    }

    @Override
    public List<Character> getAllData() {
        List<Character> allUser = new ArrayList<>();
        for (Document doc : collection.find()){
            allUser.add(gson.fromJson(doc.toJson(), Character.class));
        }
        return allUser;
    }

    @Override
    public Character getDataById(String key, Object value) {
        query = new BasicDBObject(key,value);
        for (Document doc : collection.find(query)){
            return gson.fromJson(doc.toJson(), Character.class);
        }
        return null;
    }

    @Override
    public void updateData(Character arg) {
        query = new BasicDBObject("_id", arg.getId());

        up = new BasicDBObject();
        up.append("$set",Document.parse(gson.toJson(arg)));

        collection.findOneAndUpdate(query, up);
    }

    @Override
    public void deleteData(Character arg) {
        query = new BasicDBObject("_id",arg.getId());

        collection.findOneAndDelete(query);
    }

    @Override
    public void insertData(Character arg) {
        collection.insertOne(Document.parse(gson.toJson(arg)));
    }
}
