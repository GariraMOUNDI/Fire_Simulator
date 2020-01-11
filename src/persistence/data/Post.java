package persistence.data;

import org.bson.types.ObjectId;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The type Post. Contains all of the attributes related to a post.
 */
public class Post {
    private Object _id;
    private String username, date, content;
    private List<Post> comments;

    /**
     * Instantiates a new Post.
     *
     * @param username the username of the author
     * @param date     the date the post was created
     * @param content  the content of the post
     */
    public Post(String username, String date, String content) {
        this.username = username;
        this.date = date;
        this.content = content;
        this.comments = new ArrayList<Post>();
    }

    /**
     * Modify the post.
     *
     * @param newContent the modified content of the post
     */
    public void modifyPost(String newContent) {
        this.content = newContent;
    }

    /**
     * Gets the post id (set by mongodb).
     *
     * @return the post id
     */
    public Object getId() { return _id; }

    /**
     * Sets the post id.
     *
     * @param  id the post id
     */
    public void setId(Object id) { this._id = id; }

    /**
     * Gets username of the post's author.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the date of the post's creation.
     *
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Gets the content of the post.
     *
     * @return the post's content
     */
    public String getContent() {
        return content;
    }

    /**
     * Write a comment.
     *
     * @param username the username of the author
     * @param content  the content of the comment
     */
    public void writeComment(String username, String content) {
      comments.add(new Post(username, new Date().toString(), content));
    }

    /**
     * Gets the list of comments attributed to the post.
     *
     * @return the comments
     */
    public List<Post> getComments() {
        return comments;
    }

    /**
     * Gets the comment at a set index.
     *
     * @param index the index of the comment
     * @return the comment
     */
    public Post getComment(int index) {
        return comments.get(index);
    }

    /**
     * Static method that parses an id passed as an argument to be compatible with mongodb.
     *
     * @param id the id
     * @return the new id as a String
     */
    public static String parseId(Object id) {
        String p = id.toString().split("=")[1];
        return p.substring(0,p.length()-1);
    }
}
