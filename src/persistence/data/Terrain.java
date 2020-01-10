package persistence.data;

/**
 * The type Terrain.
 */
public class Terrain {

    private Object _id;
    private String name, username;
    private Matrice map;


    /**
     * Gets name of terrain.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Object get_id() {
        return _id;
    }

    /**
     * Sets id.
     *
     * @param _id the id
     */
    public void set_id(Object _id) {
        this._id = _id;
    }


    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets name of owner's terrain.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets name of owner's terrain.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets map.
     *
     * @param map the map
     */
    public void setMap(Matrice map) {
        this.map = map;
    }

    /**
     * Gets map.
     *
     * @return the map
     */
    public Matrice getMap() {
        return this.map;
    }


    /**
     * Instantiates a new Terrain.
     *
     * @param name     name of the terrain
     * @param map      Matrice associate to the terrain
     * @param username the username of the terrain's owner
     */
    public Terrain(String name, Matrice map, String username){
        this.name = name;
        this.map = map;
        this.username = username;
    }

    /**
     * Gets terrain.
     *
     * @return the terrain
     */
    public Terrain getTerrain() {
        return this;
    }

    /**
     * Method to put the ID in a usable field. (Because of MongoDB)
     *
     * @param id the id
     * @return the string
     */
    public static String parseId(Object id) {
        String p = id.toString().split("=")[1];
        return p.substring(0,p.length()-1);
    }
}
