package persistence.data;

import persistence.factories.DAOType;

public class Box {
    private StateBox state;
    private int x;
    private int y;
    private int life;


    public Box(StateBox state, int x, int y, int life){
        this.state = state;
        this.x = x;
        this.y = y;
        this.life = life;
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

    public void placeElement(){

    }

    public void changeState(){

    }

}
