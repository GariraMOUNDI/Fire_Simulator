package persistence.data;

public class Matrice {
    int size;
    Box [][] boxes;

    public Matrice(int size) {
        this.boxes = new Box[size][size];
    }

    public int[] getCoordonate(Box box){
        return new int[]{box.getX(), box.getY()};
    }
}
