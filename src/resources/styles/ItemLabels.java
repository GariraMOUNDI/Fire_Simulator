package resources.styles;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;

public class ItemLabels extends Label {

    public ItemLabels(String text, String color){
        super(text);
        setTextFill(Paint.valueOf(color));
        setPadding(new Insets(0,10,0,0));
        //setFont(new Font());
    }
}
