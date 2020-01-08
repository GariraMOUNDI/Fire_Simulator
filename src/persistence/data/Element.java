package persistence.data;

import javafx.scene.paint.Color;

import java.util.Arrays;

public class Element {
    private Object _id;
    private String elementName;
    private int flammability;
    private String color;
    private boolean basic;
    private String username;
    private TypeElementEnum type;

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

    public TypeElementEnum getType(){
        return this.type;
    }

    public void setType(TypeElementEnum type){
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
