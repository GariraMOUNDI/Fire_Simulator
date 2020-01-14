package ui.controller;

import businessLogic.ElementFacade;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import persistence.data.ColorElement;
import persistence.data.Element;
import persistence.data.TypeElementEnum;
import ui.interfaces.LoginInterface;
import ui.model.ApplicationUI;

import java.awt.*;

/**
 * The type Element maker controller.
 */
public class ElementMakerController implements LoginInterface {

    /**
     * The element facade instance.
     */
    ElementFacade EF = ElementFacade.getInstance(this);

    @FXML
    private TextField ElementName_input, flammability_input;

    @FXML
    private Button buttonMaker;

    @FXML
    private MenuButton menu_color;

    private String[] colors;
    /**
     * The Menu color.
     */
    @FXML
    private Pane colorView;
    private TypeElementEnum elementType;
    private int currentColor;

    public void init(TypeElementEnum type, Element modify) {
        colors = ColorElement.getColors(type);
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

        currentColor = 0;
        for (int i=0; i< colors.length ; i++){
            MenuItem color = new MenuItem("\t\t");
            int j = i;
            color.setOnAction(a->{
                actionColor(j);
            });
            color.setStyle("-fx-background-color: "+ colors[j] + ";");

            menu_color.getItems().add(color);
        }
    }

    private void actionColor(int j){
        colorView.setStyle("-fx-background-color: "+ colors[j] + "; -fx-background-radius: 40;");
        currentColor = j;
    }
    /**
     * Create element.
     *
     * @throws Exception the exception
     */
    public void createElement() throws Exception {
        if (!ElementName_input.getText().trim().equals(""))
            try{
                int flammability = Integer.parseInt(flammability_input.getText().trim());
                if (buttonMaker.getText().equals("Create")){
                    if (elementType == TypeElementEnum.Rock){
                        EF.createElement(ElementName_input.getText(),0,colors[currentColor], elementType ,EF.getUserLoggedIn().getUsername());
                        backToElementManagement();
                    } else
                        if (flammability <= 100 && flammability >= -100)
                            if( (flammability < 0 &&  elementType == TypeElementEnum.Water) || (flammability > 0 && elementType == TypeElementEnum.Vegetation)){
                                EF.createElement(ElementName_input.getText(),flammability,colors[currentColor], elementType ,EF.getUserLoggedIn().getUsername());
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

    /**
     * Back to element management.
     *
     * @throws Exception the exception
     */
    public void backToElementManagement() throws Exception{
        ElementName_input.setText("");
        flammability_input.setText("");
        buttonMaker.setText("Create");
        EF.setCurrentElement(null);
        ApplicationUI.elementManagementView(ApplicationUI.getStage());
    }
}
