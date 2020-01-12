package ui.controller;

import businessLogic.ElementFacade;
import businessLogic.SessionFacade;
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

import java.util.List;

/**
 * The type Element maker controller.
 */
public class ElementMakerController implements LoginInterface {
    /**
     * The element facade instance.
     */
    ElementFacade EF = ElementFacade.getInstance(this);
    /**
     * The session facade instance.
     */
    SessionFacade SF = SessionFacade.getInstance(this);

    @FXML
    private TextField ElementName_input, flammability_input;

    @FXML
    private Button createElement;
    /**
     * The Menu color.
     */
    @FXML
    public MenuButton menu_color;


    /**
     * Initialize.
     */
    public void initialize() {
        Element element = EF.getCurrentElement();
        String colors[] = ColorElement.getColors(element.getType());
        for (int i = 0; i<colors.length; i++){
            MenuItem color = new MenuItem();
            color.setStyle("-fx-background-color: "+ colors[i] + ";");
            color.setText(colors[i]);
            color.setOnAction(a->{
                menu_color.setText(color.getText());
            });
            menu_color.getItems().add(color);
        }
    }

    /**
     * Create element.
     *
     * @param actionEvent the action event
     * @throws Exception the exception
     */
    public void createElement(ActionEvent actionEvent) throws Exception {
        EF.createElement(ElementName_input.getText(),Integer.parseInt(flammability_input.getText()),menu_color.getText(), EF.getCurrentElement().getType(),SF.getUserLoggedIn().get_id());
        backToElementManagement();
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
        ApplicationUI.elementManagementView(ApplicationUI.getStage());
    }
}
