package persistence.dao;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import persistence.interfaces.DAO;
public class MongoDBDAO<Type> implements DAO<String> {

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> coll = null;
    private Gson gson = new Gson();
    private String collectionName;   // La méthode setType
    private BasicDBObject query;
    private Type data;

    public MongoDBDAO(){
        connectionToDatabase();
    }

    public Object getDataById(String key, Object value) {
        if (coll == null)
            getAllData();
        query = new BasicDBObject(key,value);
        for (Document doc : coll.find(query)){
            return doc.toJson();
        }
        return null;
    }

    @Override
    public Object getAllData() {
        coll = database.getCollection(collectionName);
        return null;
    }

    public void setCollectionName(String name){
        collectionName = name;
    }

    @Override
    public void connectionToDatabase() {
        mongoClient = MongoClients.create("mongodb+srv://admin:FireSimulator2019@firesim-qcpoi.mongodb.net/test?retryWrites=true&w=majority");
        System.out.println("Database connected !!!");
        database = mongoClient.getDatabase("FireBase");
        System.out.println("Database got !!!");
    }

    @Override
    public void updateData(String arg) {
        if (coll == null)
            getAllData();
    }

    @Override
    public void deleteData(Object arg) {
        if (coll == null)
            getAllData();
    }

    @Override
    public void insertData(String arg) {
        if (coll == null)
            getAllData();
        coll.insertOne(Document.parse(gson.toJson(arg)));
        System.out.println("Object Inserted !!!");
    }

    @Override
    public void closeConnection() {
        mongoClient.close();
    }

    @Override
    public void connectionException(Object arg) {

    }
}
