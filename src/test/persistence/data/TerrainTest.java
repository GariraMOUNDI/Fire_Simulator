package test.persistence.data; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import persistence.data.Matrice;
import persistence.data.Terrain;

import static org.junit.Assert.*;

/** 
* Terrain Tester. 
* 
* @author <Authors name> 
* @since <pre>janv. 14, 2020</pre> 
* @version 1.0 
*/ 
public class TerrainTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getName() 
* 
*/ 
@Test
public void testGetName() throws Exception {
    Matrice map = new Matrice(4);
    Terrain t = new Terrain("terrainTest", map,"user");
    assertEquals("terrainTest",t.getName());
} 

/** 
* 
* Method: get_id() 
* 
*/ 
@Test
public void testGet_id() throws Exception {
    Matrice map = new Matrice(4);
    Terrain t = new Terrain("terrainTest", map,"user");
    assertNull(t.get_id());
} 

/** 
* 
* Method: set_id(Object _id) 
* 
*/ 
@Test
public void testSet_id() throws Exception {
    Matrice map = new Matrice(4);
    Terrain t = new Terrain("terrainTest", map,"user");
    t.set_id("id");
    assertEquals("id",t.get_id());
} 

/** 
* 
* Method: setName(String name) 
* 
*/ 
@Test
public void testSetName() throws Exception {
    Matrice map = new Matrice(4);
    Terrain t = new Terrain("terrainTest", map,"user");
    t.setName("second");
    assertEquals("second",t.getName());
}

/** 
* 
* Method: getUsername() 
* 
*/ 
@Test
public void testGetUsername() throws Exception {
    Matrice map = new Matrice(4);
    Terrain t = new Terrain("terrainTest", map,"user");
    assertEquals("user",t.getUsername());
} 

/** 
* 
* Method: setUsername(String username) 
* 
*/ 
@Test
public void testSetUsername() throws Exception {
    Matrice map = new Matrice(4);
    Terrain t = new Terrain("terrainTest", map,"user");
    t.setUsername("userTwo");
    assertEquals("userTwo",t.getUsername());
} 

/** 
* 
* Method: setMap(Matrice map) 
* 
*/ 
@Test
public void testSetMap() throws Exception {
    Matrice map = new Matrice(4);
    Terrain t = new Terrain("terrainTest", map,"user");
    Matrice map2 = new Matrice(2);
    t.setMap(map2);
    assertEquals(map2,t.getMap());
} 

/** 
* 
* Method: getMap() 
* 
*/ 
@Test
public void testGetMap() throws Exception {
    Matrice map = new Matrice(4);
    Terrain t = new Terrain("terrainTest", map,"user");
    assertEquals(map,t.getMap());
} 

/** 
* 
* Method: getTerrain() 
* 
*/ 
@Test
public void testGetTerrain() throws Exception {
    Matrice map = new Matrice(2);
    Terrain t = new Terrain("terrainTest", map,"user");
    assertSame(t,t.getTerrain());
}

} 
