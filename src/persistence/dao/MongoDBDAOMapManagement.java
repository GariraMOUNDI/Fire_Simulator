package persistence.dao;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
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
        List<Terrain> maps = new ArrayList<>();
        if (key.equals("_id")) query = new BasicDBObject(key,new ObjectId(value.toString()));
        else query = new BasicDBObject(key,value);
        for (Document doc : collection.find(query)){
            Terrain g = gson.fromJson(doc.toJson(), Terrain.class);
            maps.add(g);
        }
        for (Terrain m : maps) {
            m.set_id(Terrain.parseId(m.get_id()));
        }
        return maps;
    }

    @Override
    public void updateData(Terrain arg) {
        query = new BasicDBObject("_id", new ObjectId((String)arg.get_id()));
        arg.set_id(null);
        up = new BasicDBObject();
        up.append("$set",Document.parse(gson.toJson(arg)));
        collection.findOneAndUpdate(query, up);
    }

    @Override
    public void deleteData(Terrain arg) {
        query = new BasicDBObject("_id", new ObjectId((String) arg.get_id()));
        collection.findOneAndDelete(query);
    }

    @Override
    public void insertData(Terrain arg) {
        collection.insertOne(Document.parse(gson.toJson(arg)));
    }

}
