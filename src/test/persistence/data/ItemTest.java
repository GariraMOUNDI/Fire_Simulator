package test.persistence.data; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import persistence.data.Item;
import persistence.factories.ItemType;

import static org.junit.Assert.*;

/** 
* Item Tester. 
* 
* @author <Authors name> 
* @since <pre>janv. 14, 2020</pre> 
* @version 1.0 
*/ 
public class ItemTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getRegeneration() 
* 
*/ 
@Test
public void testGetRegeneration() throws Exception {
    Item i = new Item("item",1,2,1,1,4,"url");
    assertEquals(4,i.getRegeneration());
} 

/** 
* 
* Method: setRegeneration(int regeneration) 
* 
*/ 
@Test
public void testSetRegeneration() throws Exception {
    Item i = new Item("item",1,2,1,1,4,"url");
    i.setRegeneration(5);
    assertEquals(5,i.getRegeneration());
} 

/** 
* 
* Method: getDamage() 
* 
*/ 
@Test
public void testGetDamage() throws Exception {
    Item i = new Item("item",1,2,1,1,4,"url");
    assertTrue(i.getDamage()==2);
} 

/** 
* 
* Method: setDamage(float damage) 
* 
*/ 
@Test
public void testSetDamage() throws Exception {
    Item i = new Item("item",1,2,1,1,4,"url");
    i.setDamage(3);
    assertTrue(i.getDamage()==3);
} 

/** 
* 
* Method: getScope() 
* 
*/ 
@Test
public void testGetScope() throws Exception {
    Item i = new Item("item",1,2,3,5,4,"url");
    assertEquals(1,i.getScope());
} 

/** 
* 
* Method: setScope(int scope) 
* 
*/ 
@Test
public void testSetScope() throws Exception {
    Item i = new Item("item",1,2,3,5,4,"url");
    i.setScope(6);
    assertEquals(6,i.getScope());
} 

/** 
* 
* Method: getName() 
* 
*/ 
@Test
public void testGetName() throws Exception {
    Item i = new Item("item",1,2,3,5,4,"url");
    assertEquals("item",i.getName());
} 

/** 
* 
* Method: setName(String name) 
* 
*/ 
@Test
public void testSetName() throws Exception {
    Item i = new Item("item",1,2,3,5,4,"url");
    i.setName("name two");
    assertEquals("name two",i.getName());
} 

/** 
* 
* Method: getType() 
* 
*/ 
@Test
public void testGetType() throws Exception {
    Item i = new Item("item",1,2,3,5,4,"url");
    assertNull(i.getType());
} 

/** 
* 
* Method: setType(ItemType type) 
* 
*/ 
@Test
public void testSetType() throws Exception {
    Item i = new Item("item",1,2,3,5,4,"url");
    i.setType(ItemType.Water);
    assertEquals(ItemType.Water,i.getType());
} 

/** 
* 
* Method: getLevel() 
* 
*/ 
@Test
public void testGetLevel() throws Exception {
    Item i = new Item("item",1,2,3,5,4,"url");
    assertEquals(5,i.getLevel());
}

/** 
* 
* Method: setLevel(int level) 
* 
*/ 
@Test
public void testSetLevel() throws Exception {
    Item i = new Item("item",1,2,3,5,4,"url");
    i.setLevel(10);
    assertEquals(10,i.getLevel());
} 

/** 
* 
* Method: getImageURL() 
* 
*/ 
@Test
public void testGetImageURL() throws Exception {
    Item i = new Item("item",1,2,3,5,4,"url");
    assertEquals("url",i.getImageURL());
} 

/** 
* 
* Method: getPrice() 
* 
*/ 
@Test
public void testGetPrice() throws Exception {
    Item i = new Item("item",1,2,3,5,4,"url");
    assertEquals(3,i.getPrice());
} 

/** 
* 
* Method: setPrice(int price) 
* 
*/ 
@Test
public void testSetPrice() throws Exception {
    Item i = new Item("item",1,2,3,5,4,"url");
    i.setPrice(9);
    assertEquals(9,i.getPrice());
} 

} 
