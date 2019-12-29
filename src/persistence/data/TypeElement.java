package persistence.data;

import javafx.scene.paint.Color;

public class TypeElement {
    private int flammabilityMin;
    private int flammabilityMax;
    private String[] autorizedColors;

    public int getFlammabilityMax() {
        return flammabilityMax;
    }

    public void setFlammabilityMax(int flammabilityMax) {
        this.flammabilityMax = flammabilityMax;
    }

    public String[] getAutorizedColors() {
        return autorizedColors;
    }

    public void setAutorizedColors(String[] autorizedColors) {
        this.autorizedColors = autorizedColors;
    }

    public int getFlammabilityMin() {
        return flammabilityMin;
    }

    public void setFlammabilityMin(int flammabilityMin) {
        this.flammabilityMin = flammabilityMin;
    }

    public TypeElement(int fmin, int fmax, String[] colors){
        this.flammabilityMin = fmin;
        this.flammabilityMax = fmax;
        this.autorizedColors = colors;
    }
    public TypeElement(int fmin, int fmax, String colors){
        this.flammabilityMin = fmin;
        this.flammabilityMax = fmax;
        this.autorizedColors = new String[1];
        this.autorizedColors[0] = "colors";
    }
}
