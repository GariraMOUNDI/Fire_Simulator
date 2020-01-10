package persistence.data;

import javafx.scene.paint.Color;

import java.util.Arrays;

/**
 * The type Element.
 */
public class Element {
    private Object _id;
    private String elementName;
    private int flammability;
    private String color;
    private boolean basic;
    private String username;
    private TypeElementEnum type;

    /**
     * Instantiates a new Element.
     *
     * @param elementName  the element name
     * @param flammability the flammability : attribute use in a method in an other class who can calculate the probability
     *                     to which neighbours will burn or not.
     * @param color        the color : Will appear in design of a map.
     * @param type         the type : Rock, water, vegeation ...
     * @param username     This attribute represent the name of the owner of the element.
     */
    public Element(String elementName, int flammability, String color,TypeElementEnum type, String username){
        if(check(flammability,color,type)){
            this.elementName = elementName;
            this.flammability = flammability;
            this.color = color;
            this.basic = false;
            this.type = type;
            this.username = username;
        }
    }

    /**
     * Get element element.
     *
     * @return the element
     */
    public Element getElement(){
        return this;
    }

    /**
     * Is basic boolean.
     *
     * @return the boolean
     */
    public boolean isBasic() {
        return basic;
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
     * Gets element name.
     *
     * @return the element name
     */
    public String getElementName() {
        return elementName;
    }

    /**
     * Gets flammability.
     *
     * @return the flammability
     */
    public int getFlammability() {
        return flammability;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * Sets element name.
     *
     * @param elementName the element name
     */
    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    /**
     * Sets flammability.
     *
     * @param flammability the flammability
     */
    public void setFlammability(int flammability) {
        this.flammability = flammability;
    }

    /**
     * Sets color.
     *
     * @param color the color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Get type type element enum.
     *
     * @return the type element enum
     */
    public TypeElementEnum getType(){
        return this.type;
    }

    /**
     * Set type.
     *
     * @param type the type
     */
    public void setType(TypeElementEnum type){
        this.type = type;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
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
     * Method to put the ID in a usable field. (Because of MongoDB)
     *
     * @param id the id
     * @return the string
     */
    public static String parseId(Object id) {
        String p = id.toString().split("=")[1];
        return p.substring(0,p.length()-1);
    }

    /**
     * This boolean method verifies if conditions to make an element are compliant.
     * for example, a rock can't have a flammability > 0, and can't be green !
     *
     * @param flammability the flammability
     * @param color        the color
     * @param type         the type
     * @return the boolean
     */
    public boolean check(int flammability, String color, TypeElementEnum type){
        String colors[] = ColorElement.getColors(type);

        if(type.equals(TypeElementEnum.Water) || type.equals(TypeElementEnum.Rock)){
            return (flammability == 0 && Arrays.asList(colors).contains(color));
        }
        else if(type.equals(TypeElementEnum.Vegetation)){
            return (flammability > 0 && flammability < 100 && Arrays.asList(colors).contains(color) );
        }
        return false;
    }
}
