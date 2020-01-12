package resources.styles;

import businessLogic.ElementFacade;
import javafx.scene.control.Button;
import persistence.data.Element;
import persistence.data.TypeElementEnum;
import ui.controller.ElementController;

public class ElementButtons extends Button {

    public ElementButtons(String text, ElementController controller, Element element){
        super(text);
        setOnAction(e -> {
            if (text.trim().equals("Modify")){
                    try {
                        controller.getFacade().setCurrentElement(element);
                        if (controller.getElementType() == TypeElementEnum.Rock)
                            controller.goToRockMaker();
                        if (controller.getElementType() == TypeElementEnum.Water)
                            controller.goToWaterMaker();
                        if (controller.getElementType() == TypeElementEnum.Vegetation)
                            controller.goToVegetationMaker();
                    }catch (Exception ex) {
                        ex.printStackTrace();
                    }

            } else
                if (text.trim().equals("Delete")) {
                    controller.deleteElement(element);
                    controller.initialize();
                }
        });
    }
}
