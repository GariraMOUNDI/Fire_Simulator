package persistence.data;

import javafx.scene.paint.Color;

public class TypeElement {
    private int flammabilityMin;
    private int flammabilityMax;
    private Color[] autorizedColors;

    public TypeElement(int fmin, int fmax, Color[] colors){
        this.flammabilityMin = fmin;
        this.flammabilityMax = fmax;
        this.autorizedColors = colors;
    }
}
