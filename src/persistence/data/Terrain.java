package persistence.data;

public class Terrain {
    private Object _id;
    private String name, username;
    private Matrice map;

    public Terrain(String name, Matrice map){
        this.name = name;
        this.map = map;
    }

    public Terrain getMap() {
        return this;
    }


}
