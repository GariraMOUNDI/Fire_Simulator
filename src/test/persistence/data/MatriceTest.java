package test.persistence.data; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import persistence.data.Matrice;

import static org.junit.Assert.*;

/** 
* Matrice Tester. 
* 
* @author <Authors name> 
* @since <pre>janv. 14, 2020</pre> 
* @version 1.0 
*/ 
public class MatriceTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getSize() 
* 
*/ 
@Test
public void testGetSize() throws Exception {
    Matrice m = new Matrice(4);
    assertEquals(4,m.getSize());
} 

/** 
* 
* Method: setSize(int size) 
* 
*/ 
@Test
public void testSetSize() throws Exception {
    Matrice m = new Matrice(4);
    m.setSize(6);
    assertEquals(6,m.getSize());
} 

/** 
* 
* Method: getBox(int x, int y) 
* 
*/ 
@Test
public void testGetBox() throws Exception {
    Matrice m = new Matrice(4);
    assertNotNull(m.getBox(1,1));
} 

} 
