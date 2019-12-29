package persistence.data;

import java.util.ArrayList;

public class ColorElement {
    private static String colorsWater[] = {"#0000FF", "#0000A0", "#ADD8E6", "#00FFFF"};
    private static String colorsRock[] = {"#848482", "#837E7C", "#848482", "#B6B6B4"};
    private static String colorsVegetation[] = {"#254117", "#347C17", "#4AA02C", "#52D017","#7FE817","#347C2C", "#387C44","#228B22"};

    private void initColor(){}

    public static String[] getColors(TypeElementEnum type){
        if(type.equals(TypeElementEnum.Water)){
            return colorsWater;
        }
        else if(type.equals(TypeElementEnum.Rock)) {
            return colorsRock;
        }
        return colorsVegetation;
    }
}
