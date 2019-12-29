package persistence.data;

import javafx.scene.paint.Color;

public class Element {
    private Object _id;
    private String elementName;
    private int flammability;
    private String color;
    private boolean basic;

    public Element(String elementName, int flammability, String color){
        this.elementName = elementName;
        this.flammability = flammability;
        this.color = color;
        this.basic = false;
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
}
