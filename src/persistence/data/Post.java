package persistence.data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The type Post. Contains all of the attributes related to a post.
 */
public class Post {
    private Object _id, userId;
    private String date, content;
    private List<Post> comments;

    /**
     * Instantiates a new Post.
     *
     * @param userId the foreign key of the user
     * @param date     the date the post was created
     * @param content  the content of the post
     */
    public Post(Object userId, String date, String content) {
        this.userId = userId;
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
     * @param content  the content of the comment
     */
    public void writeComment(Object userId, String content) {
      comments.add(new Post(userId, new Date().toString(), content));
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

    /**
     * Static method that parses an id passed as an argument to be compatible with mongodb.
     *
     * @param id the id
     * @return the new id as a String
     */
    public static String parseId(Object id) {
        if (id.toString().charAt(0) == '{') {
            String p = id.toString().split("=")[1];
            return p.substring(0,p.length()-1);
        }
        return id.toString();
    }

    /**
     * Gets the user's id
     * @return the user id
     */
    public Object getUserId() {
        return userId;
    }
}
