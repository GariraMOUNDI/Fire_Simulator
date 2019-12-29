package ui.controller;

import businessLogic.ElementFacade;
import businessLogic.SessionFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import persistence.data.TypeElementEnum;
import ui.interfaces.LoginInterface;

public class ElementController implements LoginInterface {

    ElementFacade EF = ElementFacade.getInstance(this);
    SessionFacade SF = SessionFacade.getInstance(this);



    @FXML
    private TextField ElementName_input, flammability_input, color_input, type_input;
    @FXML
    Button createElement;

    @Override
    public void printResults(Object arg) {

    }

    public void createElement(ActionEvent actionEvent){
    //Warning
        EF.createElement(ElementName_input.getText(),Integer.parseInt(flammability_input.getText()),color_input.getText(), TypeElementEnum.Water,SF.getUser().getUsername());
    }


}
