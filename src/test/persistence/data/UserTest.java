package test.persistence.data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import persistence.data.Character;
import persistence.data.Item;
import persistence.data.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/** 
* User Tester. 
* 
* @author <Authors name> 
* @since <pre>janv. 11, 2020</pre> 
* @version 1.0 
*/ 
public class UserTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: get_id() 
* 
*/ 
@Test
public void testGet_id() throws Exception {
    User u = new User("test","mypssd","test@test","lapin");
    assertNull(u.get_id());
} 

/** 
* 
* Method: getPassword()
* 
*/ 
@Test
public void testGetPassword() throws Exception {
    User u = new User("test","mypssd","test@test","lapin");
    String mypassword = u.getPassword();
    assertEquals("failure - Strings not equals","mypssd", mypassword);
} 

/** 
* 
* Method: getUsername() 
* 
*/ 
@Test
public void testGetUsername() throws Exception {
    User u = new User("test","mypssd","test@test","lapin");
    String myusername = u.getUsername();
    assertEquals("failure - Strings not equals","test",myusername);
} 

/** 
* 
* Method: getEmail() 
* 
*/ 
@Test
public void testGetEmail() throws Exception {
    User u = new User("test","mypssd","test@test","lapin");
    String email = u.getEmail();
    assertEquals("failure - Strings not equals","test@test",email);
} 

/** 
* 
* Method: getHelpWord() 
* 
*/ 
@Test
public void testGetHelpWord() throws Exception {
    User u = new User("test","mypssd","test@test","lapin");
    String myhelpword = u.getHelpWord();
    assertEquals("failure - Strings not equals","lapin",myhelpword);
} 

/** 
* 
* Method: setUsername(String username) 
* 
*/ 
@Test
public void testSetUsername() throws Exception {
    User u = new User("test","mypssd","test@test","lapin");
    u.setUsername("pinou");
    String newUsername = u.getUsername();
    assertEquals("failure - Strings not equals","pinou",newUsername);
} 

/** 
* 
* Method: setEmail(String email) 
* 
*/ 
@Test
public void testSetEmail() throws Exception {
    User u = new User("test","mypssd","test@test","lapin");
    u.setEmail("newemail@new");
    String newemail = u.getEmail();
    assertEquals("failure - Strings not equals","newemail@new",newemail);
} 

/** 
* 
* Method: setHelpWord(String help) 
* 
*/ 
@Test
public void testSetHelpWord() throws Exception {
    User u = new User("test","mypssd","test@test","lapin");
    u.setHelpWord("help");
    String newHelpword = u.getHelpWord();
    assertEquals("failure - Strings not equals","help",newHelpword);
} 

/** 
* 
* Method: setPassword(String password) 
* 
*/ 
@Test
public void testSetPassword() throws Exception {
    User u = new User("test","mypssd","test@test","lapin");
    u.setPassword("newpassword");
    String newPassword = u.getPassword();
    assertEquals("failure - Strings not equals","newpassword",newPassword);
}

/**
* 
* Method: addItem(Item i) 
* 
*/ 
@Test
public void testAddItem() throws Exception {
    User u = new User("test","mypssd","test@test","lapin");
    Item i = new Item("name",1,1,1,1,1,"url");
    u.addItem(i);
    assertTrue(u.getItems().contains(i));
} 

/** 
* 
* Method: getItems() 
* 
*/ 
@Test
public void testGetItems() throws Exception {
    User u = new User("test","mypssd","test@test","lapin");
    List<Item> items = u.getItems();
    List<Item> itemsTest = new ArrayList<>();
    assertArrayEquals(itemsTest.toArray(),items.toArray());
} 

/** 
* 
* Method: removeItem(Item t) 
* 
*/ 
@Test
public void testRemoveItem() throws Exception {
    User u = new User("test","mypssd","test@test","lapin");
    Item i = new Item("name",1,1,1,1,1,"url");
    u.addItem(i);
    u.removeItem(i);
    assertFalse(u.getItems().contains(i));
}



/**
* 
* Method: addCharacter(Character c) 
* 
*/ 
@Test
public void testAddCharacter() throws Exception {
    User u = new User("test","mypssd","test@test","lapin");
    Character c = new Character("ulr", 1,"name",1);
    u.addCharacter(c);
    assertTrue(u.getCharacters().contains(c));
} 

/**
*
* Method: getCharacters()
*
*/
@Test
public void testGetCharacters() throws Exception {
    User u = new User("test","mypssd","test@test","lapin");
    assertFalse(u.getCharacters().isEmpty());
}

/** 
* 
* Method: removeCharacter(Character c) 
* 
*/ 
@Test
public void testRemoveCharacter() throws Exception {
    User u = new User("test","mypssd","test@test","lapin");
    Character c =new Character("url",1,"name",1);
    u.addCharacter(c);
    u.removeCharacter(c);
    assertFalse(u.getCharacters().contains(c));
} 

/** 
* 
* Method: getDiamonds() 
* 
*/ 
@Test
public void testGetDiamonds() throws Exception {
    User u = new User("username","pssd","email@email","help");
    assertTrue(u.getDiamonds()==0);
} 

/** 
* 
* Method: setDiamonds(int diamonds) 
* 
*/ 
@Test
public void testSetDiamonds() throws Exception {
    User u = new User("username","pssd","email@email","help");
    u.setDiamonds(4);
    assertTrue(u.getDiamonds()==4);
} 

/** 
* 
* Method: getXP() 
* 
*/ 
@Test
public void testGetXP() throws Exception {
    User u = new User("username","pssd","email@email","help");
    assertTrue(u.getXP()==0);
} 

/** 
* 
* Method: setXP(int XP) 
* 
*/ 
@Test
public void testSetXP() throws Exception {
    User u = new User("username","pssd","email@email","help");
    u.setXP(4);
    assertTrue(u.getXP()==4);
} 

/** 
* 
* Method: getGold() 
* 
*/ 
@Test
public void testGetGold() throws Exception {
    User u = new User("username","pssd","email@email","help");
    assertTrue(u.getGold()==0);
} 

/** 
* 
* Method: setGold(int gold) 
* 
*/ 
@Test
public void testSetGold() throws Exception {
    User u = new User("username","pssd","email@email","help");
    u.setGold(4);
    assertTrue(u.getGold()==4);
} 

/** 
* 
* Method: getLevel() 
* 
*/ 
@Test
public void testGetLevel() throws Exception {
    User u = new User("username","pssd","email@email","help");
    assertTrue(u.getLevel()==1);
} 

/** 
* 
* Method: setLevel(int level) 
* 
*/ 
@Test
public void testSetLevel() throws Exception {
    User u = new User("username","pssd","email@email","help");
    u.setLevel(4);
    assertTrue(u.getLevel()==4);
} 

/** 
* 
* Method: getFriends() 
* 
*/ 
@Test
public void testGetFriends() throws Exception {
    User u = new User("username","pssd","email@email","help");
    List<User> friends = new ArrayList<>();
    assertArrayEquals(friends.toArray(),u.getFriends().toArray());
} 

/** 
* 
* Method: addFriend(String arg) 
* 
*/ 
@Test
public void testAddFriend() throws Exception {
    User u = new User("username","pssd","email@email","help");
    User friend = new User("myfriend","pssd","e@email","ami");
    u.addFriend("myfriend");
    assertTrue(u.getFriends().contains("myfriend"));
} 

/** 
* 
* Method: removeFriend(String arg) 
* 
*/ 
@Test
public void testRemoveFriend() throws Exception {
    User u = new User("username","pssd","email@email","help");
    User friend = new User("myfriend","pssd","e@email","ami");
    u.addFriend("myfriend");
    u.removeFriend("myfriend");
    assertFalse(u.getFriends().contains("myfriend"));
} 


} 
