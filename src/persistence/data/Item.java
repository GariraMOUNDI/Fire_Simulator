package persistence.data;

import persistence.factories.ItemType;

public class Item {
    private Object _id;
    private ItemType type;
    private String name;
    private int scope;
    private float damage, price;
    private int level;
    private int regeneration;
    private String imageURL = "";

    public Item(String name, int scope, float damage, float price, int level, int regeneration, String imageURL) {
        this.type = null;
        this.name = name;
        this.scope = scope;
        this.damage = damage;
        this.price = price;
        this.level = level;
        this.regeneration = regeneration;
        this.imageURL = imageURL;
    }

    public int getRegeneration() {
        return regeneration;
    }

    public void setRegeneration(int regeneration) {
        this.regeneration = regeneration;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public int getScope() {
        return scope;
    }

    public void setScope(int scope) {
        this.scope = scope;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Object getId() {
        return _id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
