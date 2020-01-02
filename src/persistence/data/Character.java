package persistence.data;

public class Character {

    private String imageURl, name;
    private int scope, price;
    private Object _id;

    public Character(String imageURL, int scope, String name, int price){
        this.imageURl = imageURL;
        this.scope = scope;
        this.name = name;
        this.price = price;
    }

    public int getScope(){
        return scope;
    }

    public String getImageURL(){
        return this.imageURl;
    }

    public Object getId() {
        return this._id;
    }

    public static String parseId(Object id) {
        String p = id.toString().split("=")[1];
        return p.substring(0,p.length()-1);
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
