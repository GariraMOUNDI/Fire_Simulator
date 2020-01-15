package businessLogic;

import org.bson.types.ObjectId;
import persistence.data.*;
import persistence.factories.*;
import persistence.interfaces.DAO;
import ui.interfaces.LoginInterface;
import java.util.Date;
import java.util.List;

/**
 * The type Post facade.
 */
public class PostFacade {
    private LoginInterface loginIF;
    private DAO dao;
    private static PostFacade instance = null;
    private SessionFacade SF = null;

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
        SF = SessionFacade.getInstance(loginIF);
    }

    /**
     * Get user posts list.
     *
     * @return the list
     */
    public List<Post> getUserPosts(){
        return (List<Post>) dao.getDataById("userId", new ObjectId(Post.parseId(SF.getUserLoggedIn().get_id())));
    }

    public String getUsernameFromId(Object id) {
        return SF.getUsernameFromId(id);
    }

    public Object getUserId() {
        return SF.getUserLoggedIn().get_id();
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
     * @param content  the content of the post
     */
    public void writePost(String content) {
        dao.insertData(new Post(SF.getUserLoggedIn().get_id(), new Date().toString(), content));
    }

    /**
     * Write comment.
     *
     * @param postId  the post id
     * @param content the content of the comment
     */
    public void writeComment(Object postId, String content) {
        Post original = ((List<Post>) dao.getDataById("_id", new ObjectId(Post.parseId(postId)))).get(0);
        original.writeComment(SF.getUserLoggedIn().get_id(), content);
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
