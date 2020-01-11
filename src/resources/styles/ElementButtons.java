package resources.styles;

import businessLogic.ElementFacade;
import javafx.scene.control.Button;
import persistence.data.Element;
import ui.controller.ElementController;

public class ElementButtons extends Button {

    public ElementButtons(String text, ElementController controller, Element element){
        super(text);
        setOnAction(e -> {
            if (text.trim().equals("Modify")){

            }else
                if (text.trim().equals("Delete")) {
                    controller.deleteElement(element);
                    controller.initialize();
                }
        });
    }
}
