package test.persistence.data; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import persistence.data.Character;

import static org.junit.Assert.*;

/** 
* Character Tester. 
* 
* @author <Authors name> 
* @since <pre>janv. 14, 2020</pre> 
* @version 1.0 
*/ 
public class CharacterTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getScope() 
* 
*/ 
@Test
public void testGetScope() throws Exception {
    Character c = new Character("url",1,"name",2);
    assertEquals(1,c.getScope());
} 

/** 
* 
* Method: getImageURL() 
* 
*/ 
@Test
public void testGetImageURL() throws Exception {
    Character c = new Character("url",1,"name",2);
    assertEquals("url",c.getImageURL());
} 

/** 
* 
* Method: getId() 
* 
*/ 
@Test
public void testGetId() throws Exception {
    Character c = new Character("url",1,"name",2);
    assertNull(c.getId());
}

/** 
* 
* Method: getName() 
* 
*/ 
@Test
public void testGetName() throws Exception {
    Character c = new Character("url",1,"name",2);
    assertEquals("name",c.getName());
} 

/** 
* 
* Method: getPrice() 
* 
*/ 
@Test
public void testGetPrice() throws Exception {
    Character c = new Character("url",1,"name",2);
    assertEquals(2,c.getPrice());
} 


} 
