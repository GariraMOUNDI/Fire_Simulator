package persistence.dao;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.sun.glass.ui.Application;
import org.bson.Document;
import org.bson.types.ObjectId;
import persistence.data.User;
import persistence.interfaces.DAO;
import ui.model.ApplicationUI;

import java.util.ArrayList;
import java.util.List;

/**
 * The type MongoDB DAO user. This class is in charge of all connections to our MongoDB collection 'users'
 */
public class MongoDBDAOUser implements DAO<User> {

    private MongoCollection<Document> collection;
    private Gson gson = new Gson();
    private BasicDBObject query, up;

    /**
     * Instantiates a new MongoDB DAO user.
     *
     * @param database the database of the project
     */
    public MongoDBDAOUser(MongoDatabase database){
        if(database != null) {
            collection = database.getCollection("users");
        }
    }

    public User getDataById(String key, Object value) {
        if (collection != null){
            query = new BasicDBObject(key,value);
            for (Document doc : collection.find(query)){
                return gson.fromJson(doc.toJson(), User.class);
            }
            return null;
        }else{
            try {
                ApplicationUI.noConnectionVue(ApplicationUI.getStage());
            } catch (Exception e) {
                e.printStackTrace();
            }
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
