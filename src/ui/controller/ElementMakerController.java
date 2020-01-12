package ui.controller;

import businessLogic.ElementFacade;
import businessLogic.SessionFacade;
import com.sun.javafx.collections.MappingChange;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import persistence.data.ColorElement;
import persistence.data.Element;
import persistence.data.Terrain;
import persistence.data.TypeElementEnum;
import ui.interfaces.LoginInterface;
import ui.model.ApplicationUI;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ElementMakerController implements LoginInterface {

    ElementFacade EF = ElementFacade.getInstance(this);

    @FXML
    private TextField ElementName_input, flammability_input;

    @FXML
    private Button buttonMaker;
    @FXML
    private MenuButton menu_color;

    @FXML
    private Pane colorView;
    private TypeElementEnum elementType;

    public void init(TypeElementEnum type, Element modify) {
        EF.setCurrentElement(modify);
        if (modify != null){
            ElementName_input.setText(modify.getElementName());
            flammability_input.setText(modify.getFlammability()+"");
            buttonMaker.setText("Modify");
        }

        this.elementType = type;
        if(elementType == TypeElementEnum.Rock){
            flammability_input.setDisable(true);
            flammability_input.setText("0");
        }

        menu_color.getItems().clear();
        Map colors = ColorElement.getColors(type);
        Iterator keys = colors.keySet().iterator();
        while(keys.hasNext()){
            MenuItem color = new MenuItem((String) keys.next());
            color.setOnAction(a->{
                menu_color.setText(color.getText());
                colorView.setStyle("-fx-background-color: "+colors.get(color.getText())+ "; -fx-background-radius: 40;");
            });
            menu_color.getItems().add(color);
        }
    }

    public void createElement() throws Exception {
        if (!ElementName_input.getText().trim().equals(""))
            try{
                int flammability = Integer.parseInt(flammability_input.getText().trim());
                if (buttonMaker.getText().equals("Create")){
                    if (elementType == TypeElementEnum.Rock){
                        EF.createElement(ElementName_input.getText(),0,menu_color.getText(), elementType ,EF.getUserLoggedIn().getUsername());
                        backToElementManagement();
                    } else
                        if (flammability <= 100 && flammability >= -100)
                            if( (flammability < 0 &&  elementType == TypeElementEnum.Water) || (flammability > 0 && elementType == TypeElementEnum.Vegetation)){
                                EF.createElement(ElementName_input.getText(),flammability,menu_color.getText(), elementType ,EF.getUserLoggedIn().getUsername());
                                backToElementManagement();
                            }else
                                showPopupAlert("The flammability must be a number :\n\t 0 an 100 for element of type Vegetation \n\t -100 an 0 for element of type Water.");
                    }else{
                        EF.getCurrentElement().setElementName(ElementName_input.getText());
                        EF.getCurrentElement().setFlammability(flammability);
                        EF.getCurrentElement().setColor(menu_color.getText());
                        EF.updateElement();
                        backToElementManagement();
                    }
                } catch (NumberFormatException e){
                showPopupAlert("The flammability must be a number \n\t 0 an 100 for element of type Vegetation \n\t -100 an 0 for element of type Water.");
            }
        else
            showPopupAlert("Enter a name for the element.");
    }

    private void showPopupAlert(String arg){
        Alert alert;
            alert = new Alert(Alert.AlertType.WARNING , arg, ButtonType.OK);
            alert.setHeaderText("Please check your inputs !!!");
            alert.setTitle("Input error");
            alert.show();
    }

    @Override
    public void printResults(Object arg) {

    }

    public void backToElementManagement() throws Exception{
        ElementName_input.setText("");
        flammability_input.setText("");
        buttonMaker.setText("Create");
        EF.setCurrentElement(null);
        ApplicationUI.elementManagementView(ApplicationUI.getStage());
    }

}
