package persistence.database;

import com.google.gson.Gson;
import com.mongodb.*;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.model.CreateCollectionOptions;
import com.mongodb.client.model.ValidationOptions;
import org.bson.Document;
import persistence.data.User;


import java.util.List;

public class MongoDBConnector implements ConnectorIF {

    MongoClient mongoClient;
    MongoDatabase database;
    MongoCollection<Document> coll;
    Gson gson = new Gson();
    BasicDBObject query;
    User data;

    private static MongoDBConnector mongo = null;
    public static MongoDBConnector getMongoConnector(){
        if (mongo == null)
            mongo = new MongoDBConnector();
        return mongo;
    }

    private MongoDBConnector() {
        mongoClient = MongoClients.create("mongodb+srv://admin:FireSimulator2019@firesim-qcpoi.mongodb.net/test?retryWrites=true&w=majority");
        System.out.println("Database connected !!!");
        database = mongoClient.getDatabase("FireBase");
        System.out.println("Database got !!!");
    }

    @Override
    public Object getAllData(Object arg) {
        coll = database.getCollection((String) arg);
        System.out.println("Collection got !!!");
        return coll;
    }

    public Object getDataById(String key,Object value) {
        query = new BasicDBObject(key,value);

        System.out.println("Connector : "+key+"  "+ value);

        for (Document doc : coll.find(query)){
            System.out.println(doc.toJson());
            data = gson.fromJson(doc.toJson(),User.class);
        }
        System.out.println(data);

        return data;
    }

    @Override
    public void updateData(Object arg) {

    }

    @Override
    public void deleteData(Object arg) {

    }

    @Override
    public void insertData(Object arg) {
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
