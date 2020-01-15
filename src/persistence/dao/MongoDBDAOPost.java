package persistence.dao;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import persistence.data.Post;
import persistence.interfaces.DAO;
import java.util.ArrayList;
import java.util.List;

/**
 * The type MongoDB DAO post. This class is in charge of all connections to our MongoDB collection 'posts'
 */
public class MongoDBDAOPost implements DAO<Post> {
    private MongoCollection<Document> collection;
    private Gson gson = new Gson();
    private BasicDBObject query, up;

    /**
     * Instantiates a new MongoDB DAO post.
     *
     * @param database the database of the project
     */
    public MongoDBDAOPost(MongoDatabase database){
        collection = database.getCollection("posts");
    }

    @Override
    public List<Post> getAllData() {
        List<Post> allUser = new ArrayList<>();
        for (Document doc : collection.find()){
            Post g = gson.fromJson(doc.toJson(), Post.class);
            allUser.add(g);
        }
        return allUser;
    }


    @Override
    public List<Post> getDataById(String key, Object value) {
        List<Post> posts = new ArrayList<>();
        if (key.equals("_id")) query = new BasicDBObject(key,new ObjectId(value.toString()));
        else query = new BasicDBObject(key,value);
        for (Document doc : collection.find(query)){
            Post g = gson.fromJson(doc.toJson(), Post.class);
            posts.add(g);
        }
        for (Post p : posts) {
            p.setId(Post.parseId(p.getId()));
        }
        return posts;
    }

    @Override
    public void updateData(Post arg) {
        query = new BasicDBObject("_id", new ObjectId((String)arg.getId()));
        arg.setId(null);
        up = new BasicDBObject();
        up.append("$set",Document.parse(gson.toJson(arg)));

        collection.findOneAndUpdate(query, up);
    }

    @Override
    public void deleteData(Post arg) {
        query = new BasicDBObject("userId",arg.getUserId());
        query.append("content",arg.getContent());
        collection.findOneAndDelete(query);
    }

    @Override
    public void insertData(Post arg) {
        collection.insertOne(Document.parse(gson.toJson(arg)));
    }
}
