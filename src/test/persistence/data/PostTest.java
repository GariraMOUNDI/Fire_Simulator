package test.persistence.data; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import persistence.data.Post;
import persistence.data.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/** 
* Post Tester. 
* 
* @author <Authors name> 
* @since <pre>janv. 14, 2020</pre> 
* @version 1.0 
*/ 
public class PostTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: modifyPost(String newContent) 
* 
*/ 
@Test
public void testModifyPost() throws Exception {
    Post p = new Post("user","01/01/2020","content");
    p.modifyPost("new content");
    assertEquals("new content",p.getContent());
} 

/** 
* 
* Method: getId() 
* 
*/ 
@Test
public void testGetId() throws Exception {
    Post p = new Post("user","01/01/2020","content");
    assertNull(p.getId());
}

/** 
* 
* Method: setId(Object id) 
* 
*/ 
@Test
public void testSetId() throws Exception {
    Post p = new Post("user","01/01/2020","content");
    p.setId("the id");
    assertEquals("the id",p.getId());
} 

/** 
* 
* Method: getUsername() 
* 
*/ 
@Test
public void testGetUsername() throws Exception {
    Post p = new Post("user","01/01/2020","content");
    assertEquals("user",p.getUsername());
}

/** 
* 
* Method: getDate() 
* 
*/ 
@Test
public void testGetDate() throws Exception {
    Post p = new Post("user","01/01/2020","content");
    assertEquals("01/01/2020",p.getDate());
} 

/** 
* 
* Method: getContent() 
* 
*/ 
@Test
public void testGetContent() throws Exception {
    Post p = new Post("user","01/01/2020","content");
    assertEquals("content",p.getContent());
} 

/** 
* 
* Method: writeComment(String username, String content) 
* 
*/ 
@Test
public void testWriteComment() throws Exception {
    Post p = new Post("user","01/01/2020","content");
    p.writeComment("user2","the comment test");
    assertNotNull(p.getComments());
}

/**
*
* Method: getComments() 
* 
*/ 
@Test
public void testGetComments() throws Exception {
    Post p = new Post("user","01/01/2020","content");
    List<Post> comments = new ArrayList<>();
    assertArrayEquals(comments.toArray(),p.getComments().toArray());
} 

} 
