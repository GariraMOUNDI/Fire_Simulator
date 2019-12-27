package persistence.data;

import persistence.factories.DAOType;

public class Box {
    private StateBox state;
    private int x;
    private int y;
    private int life;
    private Element element;


    public Box(StateBox state, int x, int y, int life, Element element){
        this.state = state;
        this.x = x;
        this.y = y;
        this.life = life;
        this.element = element;
    }

    public StateBox getState() {
        return state;
    }

    public void setState(StateBox state) {
        this.state = state;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public void changeState(){

    }

}
