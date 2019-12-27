package persistence.dao;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import persistence.data.Element;
import persistence.interfaces.DAO;

import java.util.ArrayList;
import java.util.List;

public class MongoDBDAOElement implements DAO<Element> {

    private MongoCollection<Document> collection;
    private Gson gson = new Gson();
    private BasicDBObject query, up;

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
        return null;
    }

    @Override
    public void updateData(Element arg) {

    }

    @Override
    public void deleteData(Element arg) {
        query = new BasicDBObject("elementName", arg.getElementName());
        //append ?
        collection.findOneAndDelete(query);
    }

    @Override
    public void insertData(Element arg) {
        collection.insertOne(Document.parse(gson.toJson(arg)));
    }
}
