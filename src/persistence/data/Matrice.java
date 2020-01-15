package persistence.data;

/**
 * The type Matrice.
 */
public class Matrice {
    /**
     * This attribute is the size of the matrix, here we have always a square matrix.
     */
    private int size;
    /**
     * All cell in this matrice will be a Box.
     */
    private Box [][] boxes;

    /**
     * Gets size.
     *
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * Sets size.
     *
     * @param size the size
     */
    public void setSize(int size) {
        this.size = size;
    }


    /**
     * Instantiates a new Matrice. Every cells are Boxes, more specifically the BasicTree.
     *
     * @param size the size
     */
    public Matrice(int size) {
        this.size = size;
        this.boxes = new Box[size][size];
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                this.boxes[i][j] = new Box(StateBox.intact, i, j, 100, new Element("BasicTree", 50, "#228B22", TypeElementEnum.Vegetation ,"Admin"));;
            }
        }
    }

    /**
     * Get box
     *
     * @param x the line coordinate
     * @param y the column coordinate
     * @return the box
     */
    public Box getBox(int x, int y){
        return this.boxes[x][y];
    }


    public void setBox(int x, int y, Box box){
        this.boxes[x][y] = box;
    }

    /**
     * Get coordinate send Box if we parameters x and y.
     *
     * @param box box
     * @return x and y in a Array dim 2.
     */
    public int[] getCoordonate(Box box){
        return new int[]{box.getX(), box.getY()};
    }


}
