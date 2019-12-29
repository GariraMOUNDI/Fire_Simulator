package persistence.data;

import javafx.scene.paint.Color;

public class Element {
    private Object _id;
    private String elementName;
    private int flammability;
    private String color;
    private boolean basic;
    private TypeElement type;



    private String username;

    public Element(String elementName, int flammability, String color,TypeElement type, String username){
        this.elementName = elementName;
        this.flammability = flammability;
        this.color = color;
        this.basic = false;
        this.type = type;
        this.username = username;
    }

    public Element getElement(){
        return this;
    }

    public boolean isBasic() {
        return basic;
    }

    public Object get_id() {
        return _id;
    }

    public String getElementName() {
        return elementName;
    }

    public int getFlammability() {
        return flammability;
    }

    public String getColor() {
        return color;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public void setFlammability(int flammability) {
        this.flammability = flammability;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public TypeElement getType(){
        return this.type;
    }

    public void setType(TypeElement type){
        this.type = type;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void set_id(Object _id) {
        this._id = _id;
    }

    public static String parseId(Object id) {
        String p = id.toString().split("=")[1];
        return p.substring(0,p.length()-1);
    }
}
