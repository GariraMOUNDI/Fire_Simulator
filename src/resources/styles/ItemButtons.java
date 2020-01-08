package resources.styles;

import javafx.scene.control.Button;
import javafx.scene.paint.Paint;

public class ItemButtons extends Button {

    public ItemButtons(String text, String color){
        super(text);
        setTextFill(Paint.valueOf("white"));
        setStyle("-fx-background-color: "+color+"; -fx-font-size: 9;");
    }
}
