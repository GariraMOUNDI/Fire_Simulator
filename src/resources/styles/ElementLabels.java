package resources.styles;


import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class ElementLabels extends Label {

    public ElementLabels(String text){
        super(text);
//        setFont(new Font("Arial Rounded MT Bold", 15));
        setFont(Font.loadFont(getClass().getResourceAsStream("/resources/fonts/ArialRoundedMTBold.ttf"), 15));
    }
}
