package persistence.data;

/**
 * The type Character.
 */
public class Character {

    private String imageURl, name;
    private int scope, price;
    private Object _id;

    /**
     * Instantiates a new Character.
     *
     * @param imageURL the image url
     * @param scope    the scope of the character
     * @param name     the character name
     * @param price    the price
     */
    public Character(String imageURL, int scope, String name, int price){
        this.imageURl = imageURL;
        this.scope = scope;
        this.name = name;
        this.price = price;
    }

    /**
     * Get scope int.
     *
     * @return the int
     */
    public int getScope(){
        return scope;
    }

    /**
     * Get image url string.
     *
     * @return the string
     */
    public String getImageURL(){
        return this.imageURl;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Object getId() {
        return this._id;
    }

    /**
     * Parse id string.
     *
     * @param id the id
     * @return the string
     */
    public static String parseId(Object id) {
        String p = id.toString().split("=")[1];
        return p.substring(0,p.length()-1);
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public int getPrice() {
        return price;
    }
}
