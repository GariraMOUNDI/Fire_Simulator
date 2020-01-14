package test.persistence.data; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import persistence.data.Element;
import persistence.data.TypeElementEnum;

import static org.junit.Assert.*;

/** 
* Element Tester. 
* 
* @author <Authors name> 
* @since <pre>janv. 14, 2020</pre> 
* @version 1.0 
*/ 
public class ElementTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getElement() 
* 
*/ 
@Test
public void testGetElement() throws Exception {
    Element e = new Element("name",50,"RED", TypeElementEnum.Vegetation,"user");
    assertEquals(e,e.getElement());
} 

/** 
* 
* Method: isBasic() 
* 
*/ 
@Test
public void testIsBasic() throws Exception {
    Element e = new Element("name",50,"RED", TypeElementEnum.Vegetation,"user");
    assertFalse(e.isBasic());
} 

/** 
* 
* Method: get_id() 
* 
*/ 
@Test
public void testGet_id() throws Exception {
    Element e = new Element("name",50,"RED", TypeElementEnum.Vegetation,"user");
    assertNull(e.get_id());
} 

/** 
* 
* Method: getElementName() 
* 
*/ 
@Test
public void testGetElementName() throws Exception {
    Element e = new Element("name",50,"RED", TypeElementEnum.Vegetation,"user");
    assertEquals("name",e.getElementName());
} 

/** 
* 
* Method: getFlammability() 
* 
*/ 
@Test
public void testGetFlammability() throws Exception {
    Element e = new Element("name",50,"RED", TypeElementEnum.Vegetation,"user");
    assertEquals(50,e.getFlammability());
} 

/** 
* 
* Method: getColor() 
* 
*/ 
@Test
public void testGetColor() throws Exception {
    Element e = new Element("name",50,"RED", TypeElementEnum.Vegetation,"user");
    assertEquals("RED",e.getColor());
} 

/** 
* 
* Method: setElementName(String elementName) 
* 
*/ 
@Test
public void testSetElementName() throws Exception {
    Element e = new Element("name",50,"RED", TypeElementEnum.Vegetation,"user");
    e.setElementName("name two");
    assertEquals("name two",e.getElementName());
} 

/** 
* 
* Method: setFlammability(int flammability) 
* 
*/ 
@Test
public void testSetFlammability() throws Exception {
    Element e = new Element("name",50,"RED", TypeElementEnum.Vegetation,"user");
    e.setFlammability(70);
    assertEquals(70,e.getFlammability());
} 

/** 
* 
* Method: setColor(String color) 
* 
*/ 
@Test
public void testSetColor() throws Exception {
    Element e = new Element("name",50,"RED", TypeElementEnum.Vegetation,"user");
    e.setColor("GREEN");
    assertEquals("GREEN",e.getColor());
} 

/** 
* 
* Method: getType() 
* 
*/ 
@Test
public void testGetType() throws Exception {
    Element e = new Element("name",50,"RED", TypeElementEnum.Vegetation,"user");
    assertEquals(TypeElementEnum.Vegetation,e.getType());
}

/** 
* 
* Method: setType(TypeElementEnum type) 
* 
*/ 
@Test
public void testSetType() throws Exception {
    Element e = new Element("name",0,"RED", TypeElementEnum.Vegetation,"user");
    e.setType(TypeElementEnum.Water);
    assertEquals(TypeElementEnum.Water,e.getType());
} 

/** 
* 
* Method: getUsername() 
* 
*/ 
@Test
public void testGetUsername() throws Exception {
    Element e = new Element("name",50,"RED", TypeElementEnum.Vegetation,"user");
    assertEquals("user",e.getUsername());
}

/** 
* 
* Method: setUsername(String username) 
* 
*/ 
@Test
public void testSetUsername() throws Exception {
    Element e = new Element("name",50,"RED", TypeElementEnum.Vegetation,"user");
    e.setUsername("user two");
    assertEquals("user two",e.getUsername());
} 

/** 
* 
* Method: set_id(Object _id) 
* 
*/ 
@Test
public void testSet_id() throws Exception {
    Element e = new Element("name",50,"RED", TypeElementEnum.Vegetation,"user");
    e.set_id("id");
    assertEquals("id",e.get_id());
} 

} 
