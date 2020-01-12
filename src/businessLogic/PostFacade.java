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

/**
 * The type Post facade.
 */
public class PostFacade {
    private LoginInterface loginIF;
    private DAO dao;
    private Gson gson = new Gson();
    private static PostFacade instance = null;

    /**
     * Get the instance of post facade.
     *
     * @param loginIF the login interface
     * @return the post facade
     */
    public static PostFacade getInstance(LoginInterface loginIF){
        if (instance == null)
            instance = new PostFacade(loginIF);
        return instance;
    }

    private PostFacade(LoginInterface loginIF) {
        this.loginIF = loginIF;
        dao = MongoDBDAOFactory.getInstance().createDAO(DAOType.Post);
    }

    /**
     * Get user posts list.
     *
     * @param username the username
     * @return the list
     */
    public List<Post> getUserPosts(String username){
        return (List<Post>) dao.getDataById("username", username);
    }

    /**
     * Gets all posts.
     *
     * @return the all posts
     */
    public List<Post> getAllPosts() { return (List<Post>) dao.getAllData(); }

    /**
     * Write post.
     *
     * @param username the username of the author
     * @param content  the content of the post
     */
    public void writePost(String username, String content) {
        dao.insertData(new Post(username, new Date().toString(), content));
    }

    /**
     * Write comment.
     *
     * @param postId  the post id
     * @param user    the user
     * @param content the content of the comment
     */
    public void writeComment(Object postId, String user, String content) {
        Post original = ((List<Post>) dao.getDataById("_id", new ObjectId(Post.parseId(postId)))).get(0);
        original.writeComment(user, content);
        dao.updateData(original);
    }

    /**
     * Modify post.
     *
     * @param postId  the post id to modify
     * @param content the modified content
     */
    public void modifyPost(Object postId, String content) {
        Post original = ((List<Post>) dao.getDataById("_id", new ObjectId(Post.parseId(postId)))).get(0);
        original.modifyPost(content);
        dao.updateData(original);
    }

    /**
     * Delete post.
     *
     * @param postId the post id
     */
    public void deletePost(Object postId) {
        Post original = ((List<Post>) dao.getDataById("_id", new ObjectId(Post.parseId(postId)))).get(0);
        dao.deleteData(original);
    }
}
