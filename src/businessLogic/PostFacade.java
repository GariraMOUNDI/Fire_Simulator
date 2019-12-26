package businessLogic;

import com.google.gson.Gson;
import javafx.geometry.Pos;
import org.bson.types.ObjectId;
import persistence.dao.MongoDBDAOPost;
import persistence.data.*;
import persistence.factories.*;
import persistence.interfaces.DAO;
import ui.interfaces.LoginInterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostFacade {
    private LoginInterface loginIF;
    private DAO dao;
    private Gson gson = new Gson();
    private static PostFacade instance = null;

    public static PostFacade getInstance(LoginInterface loginIF){
        if (instance == null)
            instance = new PostFacade(loginIF);
        return instance;
    }

    private PostFacade(LoginInterface loginIF) {
        this.loginIF = loginIF;
        dao = MongoDBDAOFactory.getInstance().createDAO(DAOType.Post);
    }

    public List<Post> getUserPosts(String username){
        return (List<Post>) dao.getDataById("username", username);
    }

    public List<Post> getAllPosts() { return (List<Post>) dao.getAllData(); }

    public void writePost(String username, String content) {
        dao.insertData(new Post(username, new Date().toString(), content));
    }

    public void writeComment(Object postId, String user, String content) {
        Post original = ((List<Post>) dao.getDataById("_id", new ObjectId(Post.parseId(postId)))).get(0);
        original.writeComment(user, content);
        dao.updateData(original);
    }

    public void modifyPost(Object postId, String content) {
        Post original = ((List<Post>) dao.getDataById("_id", new ObjectId(Post.parseId(postId)))).get(0);
        original.modifyPost(content);
        dao.updateData(original);
    }
}
