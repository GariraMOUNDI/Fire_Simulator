package persistence.data;

public class Terrain {
    private String name;
    private Matrice map;

    public Terrain(String name){
        this.name = name;
    }

    public Terrain getMap() {
        return this;
    }


}
