package resources.styles;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class UserLabels extends Label {

    public UserLabels(String text){
        super(text);
        setWidth(50);
        setHeight(30);
        setFont(Font.loadFont(getClass().getResourceAsStream("/resources/fonts/LucidaHandwriting.ttf"), 20));
        setStyle("-fx-font-weight: bold");
    }
}
