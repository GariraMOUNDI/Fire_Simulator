package persistence.data;

import persistence.factories.ItemType;

/**
 * The type Item.
 */
public class Item {
    private Object _id;
    private ItemType type;
    private String name;
    private int scope;
    private float damage;
    private int level, price;
    private int regeneration;
    private String imageURL;

    /**
     * Instantiates a new Item.
     *
     * @param name         the name
     * @param scope        the scope
     * @param damage       the damage
     * @param price        the price
     * @param level        the level
     * @param regeneration the time it will take for the item to be reused by the player
     * @param imageURL     the image url
     */
    public Item(String name, int scope, float damage, int price, int level, int regeneration, String imageURL) {
        this.type = null;
        this.name = name;
        this.scope = scope;
        this.damage = damage;
        this.price = price;
        this.level = level;
        this.regeneration = regeneration;
        this.imageURL = imageURL;
    }

    /**
     * Gets regeneration time.
     *
     * @return the regeneration time
     */
    public int getRegeneration() {
        return regeneration;
    }

    /**
     * Sets regeneration time.
     *
     * @param regeneration the regeneration time
     */
    public void setRegeneration(int regeneration) {
        this.regeneration = regeneration;
    }

    /**
     * Gets damage.
     *
     * @return the damage
     */
    public float getDamage() {
        return damage;
    }

    /**
     * Sets damage.
     *
     * @param damage the damage
     */
    public void setDamage(float damage) {
        this.damage = damage;
    }

    /**
     * Gets scope.
     *
     * @return the scope
     */
    public int getScope() {
        return scope;
    }

    /**
     * Sets scope.
     *
     * @param scope the scope
     */
    public void setScope(int scope) {
        this.scope = scope;
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
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public ItemType getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(ItemType type) {
        this.type = type;
    }

    /**
     * Gets level.
     *
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets level.
     *
     * @param level the level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Object getId() {
        return parseId(_id);
    }

    /**
     * Gets image url.
     *
     * @return the image url
     */
    public String getImageURL() {
        return imageURL;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Static method that parses the id passed as an argument to be compatible with mongodb.
     *
     * @param id the id to be parsed
     * @return the parsed id
     */
    public static String parseId(Object id) {
        String p = id.toString().split("=")[1];
        return p.substring(0,p.length()-1);
    }
}
