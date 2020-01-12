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
    private static Map<String, String> colorsWater =  new HashMap<>(){
        {
            put("Light Blue", "#ADD8E6");
            put("Lavender", "#E6E6FA");
            put("Light Sky Blue", "#87CEFA");
            put("Sky Blue", "#87CEEB");
            put("Deep Sky blue","#00BFFF");
            put("Conflower Blue","#6495ED");
            put("Dodger Blue", "#1E90FF");
        }
    };

    /**
         * ColorRock contain few variations of grey ...
    */
    private static Map<String, String> colorsRock =  new HashMap<>(){
        {
            put("Light Gray","#D3D3D3");
            put("Gainsboro","#DCDCDC");
            put("Silver","#C0C0C0");
            put("Dark Gray","#A9A9A9");
            put("Gray","#808080");
            put("Dim Gray","#696969");
            put("Lightslate Gray","#778899");
        }
    };

    /**
     * ColorVegetation contain few variations of green ...
     */
    private static Map<String, String> colorsVegetation = new HashMap<>(){
        {
            put("Pale Green", "#98FB98");
            put("Light Green", "#90EE90");
            put("Green Yellow", "#ADFF2F");
            put("Lawn Green", "#7CFC00");
            put("Lime green", "#32CD32");
            put("Forest Green", "#228B22");
            put("Dark Green", "#006400");
        }
    };

    /**
     * Get colors string [ ].
     * This method return the Array associate to the type parameter.
     * @param type the type
     * @return the string [ ]
     */
    public static Map getColors(TypeElementEnum type){
        if(type.equals(TypeElementEnum.Water)){
            return colorsWater;
        }
        else if(type.equals(TypeElementEnum.Rock)) {
            return colorsRock;
        }
        return colorsVegetation;
    }
}
