package persistence.dao;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import persistence.data.Element;
import persistence.data.Terrain;
import persistence.data.User;
import persistence.interfaces.DAO;

import java.util.ArrayList;
import java.util.List;

/**
 * The type MongoDB DAO element. This class is in charge of all connections to our MongoDB collection 'elements'
 */
public class MongoDBDAOElement implements DAO<Element> {

    private MongoCollection<Document> collection;
    private Gson gson = new Gson();
    private BasicDBObject query, up;

    /**
     * Instantiates a new MongoDB DAO element.
     *
     * @param database the database of project.
     */
    public MongoDBDAOElement(MongoDatabase database){
        collection = database.getCollection("elements");
    }
    @Override
    public List<Element> getAllData() {
        List<Element> allElements = new ArrayList<>();
        for(Document doc : collection.find(query)){
            Element e = gson.fromJson(doc.toJson(), Element.class);
            allElements.add(e);
        }
        return allElements;
    }



    @Override
    public Object getDataById(String key, Object value) {
        List<Element> elements = new ArrayList<>();
        if (key.equals("_id")) query = new BasicDBObject(key,new ObjectId(value.toString()));
        else query = new BasicDBObject(key,value);
        for (Document doc : collection.find(query)){
            Element e = gson.fromJson(doc.toJson(), Element.class);
            elements.add(e);
        }
        for (Element e : elements) {
            e.set_id(Element.parseId(e.get_id()));
        }
        return elements;
    }


    @Override
    public void updateData(Element arg) {
        query = new BasicDBObject("_id",new ObjectId((String) arg.get_id()));
        arg.set_id(null);
        up = new BasicDBObject("$set",Document.parse(gson.toJson(arg)));
        collection.findOneAndUpdate(query, up);
    }

    @Override
    public void deleteData(Element arg) {
        query = new BasicDBObject("_id", new ObjectId((String) arg.get_id()));
        collection.findOneAndDelete(query);
    }

    @Override
    public void insertData(Element arg) {
        collection.insertOne(Document.parse(gson.toJson(arg)));
    }
}
