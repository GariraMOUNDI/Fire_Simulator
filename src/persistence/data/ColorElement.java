package persistence.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Color element.
 * ColorElement is constitute by 3 Arrays, every array contains basics values. We use HTML code of Color.
 */
public class ColorElement {
    /**
     * ColorWater contain few variations of blue ...
     */
    private static String[] colorsWater = {
            "#ADD8E6",
            "#E6E6FA",
            "#87CEFA",
            "#87CEEB",
            "#00BFFF",
            "#6495ED",
            "#1E90FF"
    };
    /**
         * ColorRock contain few variations of grey ...
    */
    private static String[] colorsRock =
        {
            "#D3D3D3","#DCDCDC","#C0C0C0","#A9A9A9","#808080",
            "#696969","#778899"
        };

    /**
     * ColorVegetation contain few variations of green ...
     */
    private static String[] colorsVegetation =
        {
            "#98FB98","#90EE90","#ADFF2F","#7CFC00","#32CD32",
            "#228B22"
            ,"#006400"
        };

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
