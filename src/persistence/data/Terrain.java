package persistence.data;

public class Terrain {

    private Object _id;
    private String name, username;
    private Matrice map;


    public String getName() {
        return name;
    }
    public Object get_id() {
        return _id;
    }

    public void set_id(Object _id) {
        this._id = _id;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMap(Matrice map) {
        this.map = map;
    }
    public Matrice getMap() {
        return this.map;
    }



    public Terrain(String name, Matrice map, String username){
        this.name = name;
        this.map = map;
        this.username = username;
    }

    public Terrain getTerrain() {
        return this;
    }

    public static String parseId(Object id) {
        String p = id.toString().split("=")[1];
        System.out.println(p.substring(0,p.length()-1));
        return p.substring(0,p.length()-1);
    }
}
