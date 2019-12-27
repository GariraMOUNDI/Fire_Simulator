package persistence.dao;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import persistence.data.Terrain;
import persistence.interfaces.DAO;

import java.util.ArrayList;
import java.util.List;

public class MongoDBDAOMapManagement implements DAO<Terrain> {

    private MongoCollection<Document> collection;
    private Gson gson = new Gson();
    private BasicDBObject query, up;

    public MongoDBDAOMapManagement(MongoDatabase database){
        collection = database.getCollection("maps");
    }



    @Override
    public List<Terrain> getAllData() {
        List<Terrain> allTerrain = new ArrayList<>();
        for (Document doc : collection.find()){
            allTerrain.add(gson.fromJson(doc.toJson(), Terrain.class));
        }
        return allTerrain;
    }

    @Override
    public Object getDataById(String key, Object value) {
        query = new BasicDBObject(key,value);
        for (Document doc : collection.find(query)){
            return gson.fromJson(doc.toJson(), Terrain.class);
        }
        return null;
    }

    @Override
    public void updateData(Terrain arg) {

    }

    @Override
    public void deleteData(Terrain arg) {

    }

    @Override
    public void insertData(Terrain arg) {
        collection.insertOne(Document.parse(gson.toJson(arg)));
    }

}
