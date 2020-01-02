package persistence.data;

public class Character {

    private String imageURl;
    private int scope;
    private String name;
    private double price;

    public Character(String imageURL, int scope, String name, double price){
        this.imageURl = imageURL;
        this.scope = scope;
        this.name = name;
        this.price = price;
    }

    public int getScope(){
        return scope;
    }

    public String getImage(){
        return this.imageURl;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
