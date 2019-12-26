package persistence.data;

import org.bson.types.ObjectId;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Post {
    private Object _id;
    private String username, date, content;
    private List<Post> comments;

    public Post(String username, String date, String content) {
        this.username = username;
        this.date = date;
        this.content = content;
        this.comments = new ArrayList<Post>();
    }

    public void modifyPost(String newContent) {
        this.content = newContent;
    }

    public Object getId() { return _id; }
    public void setId(Object id) { _id = id; }

    public String getUsername() {
        return username;
    }

    public String getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

    public void writeComment(String username, String content) {
      comments.add(new Post(username, new Date().toString(), content));
    }

    public List<Post> getComments() {
        return comments;
    }

    public Post getComment(int index) {
        return comments.get(index);
    }

    public static String parseId(Object id) {
        String p = id.toString().split("=")[1];
        return p.substring(0,p.length()-1);
    }
}
