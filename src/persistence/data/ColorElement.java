package persistence.data;

import java.util.ArrayList;

/**
 * The type Color element.
 * ColorElement is constitute by 3 Arrays, every array contains basics values. We use HTML code of Color.
 * ColorWater contain few variations of blue ...
 * ColorRock contain few variations of grey ...
 * ColorVegetation contain few variations of green ...
 */
public class ColorElement {
    private static String colorsWater[] = {"#0000FF", "#0000A0", "#ADD8E6", "#00FFFF"};
    private static String colorsRock[] = {"#848482", "#837E7C", "#848482", "#B6B6B4"};
    private static String colorsVegetation[] = {"#254117", "#347C17", "#4AA02C", "#52D017","#7FE817","#347C2C", "#387C44","#228B22"};


    /**
     * Get colors string [ ].
     * This method return the Array associate to the type parameter.
     * @param type the type
     * @return the string [ ]
     */
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
