package persistence.data;

import javafx.scene.paint.Color;

public class Matrice {
    int size;
    Box [][] boxes;



    public Matrice(int size) {
        this.boxes = new Box[size][size];
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                this.boxes[i][j] = new Box(StateBox.intact, i, j, 100, new Element("BasicTree", 50, Color.web("#228B22")));;
            }
        }
    }

    public int[] getCoordonate(Box box){
        return new int[]{box.getX(), box.getY()};
    }
}
