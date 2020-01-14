package persistence.data;

import persistence.factories.DAOType;

import javax.swing.plaf.nimbus.State;

/**
 * The type Box.
 */
public class Box {
    private StateBox state;
    private int x;
    private int y;
    private int life;
    private Element element;


    /**
     * Instantiates a new Box. This box is used in Matrice. In a box, we can find some attributes
     * who are useful to run the algorithm of game.
     *
     * @param state   the state : this attribute can be burned, intact, etc ... this attribute is useful to know if
     *                the box can burn again or not ...
     *
     * @param x       the x : coordonate in the matrice (line)
     *
     * @param y       the y : coordonate in the matrice (column)
     *
     * @param life    the life : int between 0 and 100, this attribute will decrease if the box burn.
     *
     * @param element the element : Tree, rock, vegetation : inform about Color, type etc.
     */
    public Box(StateBox state, int x, int y, int life, Element element){
        this.state = state;
        this.x = x;
        this.y = y;
        this.life = life;
        this.element = element;
    }

    /**
     * Gets state.
     *
     * @return the state of the current Box
     */
    public StateBox getState() {
        return state;
    }

    /**
     * Sets state.
     *
     * @param state the state of the current Box
     */
    public void setState(StateBox state) {
        if (this.element.getType().equals(TypeElementEnum.Vegetation)) {
            this.state = state;
            if (state.equals(StateBox.burning)) {
                this.element.setColor("#FF0000");
            }
            if (state.equals(StateBox.scorched)) {
                this.element.setColor("#61380B");
            }
            if (state.equals(StateBox.dust)) {
                this.element.setColor("#190707");
            }
        }
    }

    /**
     * Gets x.
     *
     * @return the coordonate of the line of the box
     */
    public int getX() {
        return x;
    }

    /**
     * Sets x.
     *
     * @param x the x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets y.
     *
     * @return the column of the box.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets y.
     *
     * @param y the y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Gets life.
     *
     * @return the life
     */
    public int getLife() {
        return life;
    }

    /**
     * Sets life.
     *
     * @param life the life
     */
    public void setLife(int life) {
        this.life = life;
    }

    /**
     * Gets element.
     *
     * @return the element
     */
    public Element getElement() {
        return element;
    }

    /**
     * Sets element.
     *
     * @param element the element
     */
    public void setElement(Element element) {
        this.element = element;
    }
}
