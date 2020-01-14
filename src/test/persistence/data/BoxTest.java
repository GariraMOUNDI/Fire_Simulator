package test.persistence.data; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import persistence.data.Box;
import persistence.data.Element;
import persistence.data.StateBox;
import persistence.data.TypeElementEnum;

import static org.junit.Assert.*;

/** 
* Box Tester. 
* 
* @author <Authors name> 
* @since <pre>janv. 14, 2020</pre> 
* @version 1.0 
*/ 
public class BoxTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getState() 
* 
*/ 
@Test
public void testGetState() throws Exception {
    Box b = new Box(StateBox.intact,1,1,2,new Element("name",1,"BLUE", TypeElementEnum.Water,"user"));
    assertEquals(StateBox.intact, b.getState());
} 

/** 
* 
* Method: setState(StateBox state) 
* 
*/ 
@Test
public void testSetState() throws Exception {
    Box b = new Box(StateBox.intact,1,1,2,new Element("name",1,"BLUE", TypeElementEnum.Water,"user"));
    b.setState(StateBox.scorched);
    assertEquals(StateBox.scorched,b.getState());
} 

/** 
* 
* Method: getX() 
* 
*/ 
@Test
public void testGetX() throws Exception {
    Box b = new Box(StateBox.intact,1,2,2,new Element("name",1,"BLUE", TypeElementEnum.Water,"user"));
    assertEquals(1,b.getX());
} 

/** 
* 
* Method: setX(int x) 
* 
*/ 
@Test
public void testSetX() throws Exception {
    Box b = new Box(StateBox.intact,1,2,2,new Element("name",1,"BLUE", TypeElementEnum.Water,"user"));
    b.setX(3);
    assertEquals(3,b.getX());
} 

/** 
* 
* Method: getY() 
* 
*/ 
@Test
public void testGetY() throws Exception {
    Box b = new Box(StateBox.intact,1,3,2,new Element("name",1,"BLUE", TypeElementEnum.Water,"user"));
    assertEquals(3,b.getY());
} 

/** 
* 
* Method: setY(int y) 
* 
*/ 
@Test
public void testSetY() throws Exception {
    Box b = new Box(StateBox.intact,1,2,2,new Element("name",1,"BLUE", TypeElementEnum.Water,"user"));
    b.setY(6);
    assertEquals(6,b.getY());
} 

/** 
* 
* Method: getLife() 
* 
*/ 
@Test
public void testGetLife() throws Exception {
    Box b = new Box(StateBox.intact,1,2,10,new Element("name",1,"BLUE", TypeElementEnum.Water,"user"));
    assertEquals(10,b.getLife());
}

/** 
* 
* Method: setLife(int life) 
* 
*/ 
@Test
public void testSetLife() throws Exception {
    Box b = new Box(StateBox.intact,1,2,2,new Element("name",1,"BLUE", TypeElementEnum.Water,"user"));
    b.setLife(20);
    assertEquals(20,b.getLife());
} 

/** 
* 
* Method: getElement() 
* 
*/ 
@Test
public void testGetElement() throws Exception {
    Element e = new Element("name",1,"BLUE", TypeElementEnum.Water,"user");
    Box b = new Box(StateBox.intact,1,2,2,e);
    assertEquals(e,b.getElement());
} 

/** 
* 
* Method: setElement(Element element) 
* 
*/ 
@Test
public void testSetElement() throws Exception {
    Box b = new Box(StateBox.intact,1,2,2,new Element("name",1,"BLUE", TypeElementEnum.Water,"user"));
    Element e = new Element("name 2",50,"GREEN", TypeElementEnum.Vegetation,"user 2");
    b.setElement(e);
    assertSame(e,b.getElement());
} 


} 
