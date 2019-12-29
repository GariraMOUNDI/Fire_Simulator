package ui.controller.mapManagement;

import businessLogic.MapManagementFacade;
import businessLogic.SessionFacade;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import persistence.data.Matrice;
import persistence.data.Terrain;
import ui.interfaces.LoginInterface;
import ui.model.ApplicationUI;

import java.util.List;

public class createMapMenuController implements LoginInterface{
    MapManagementFacade MMF = MapManagementFacade.getInstance(this);
    SessionFacade SF = SessionFacade.getInstance(this);

    @FXML
    private TextField name_input, size_input;
    @FXML
    private Label error;
    

    public void toMapManagement(ActionEvent actionEvent) throws  Exception{
        ApplicationUI.MapManagementView(ApplicationUI.getStage());
    }


    public void createMap() throws Exception {
        if (name_input.getText().trim().isEmpty() || size_input.getText().trim().isEmpty()) {
            printResults("You must enter valid input");
        }
        else{
            MMF.createMap(name_input.getText(),new Matrice(Integer.parseInt(size_input.getText())), SF.getUser().getUsername());
            ApplicationUI.toMapMaker(ApplicationUI.getStage());
        }
    }

    public void printResults(Object arg) {
        if( arg instanceof Terrain)
            error.setText("Done !!!");
        else
            error.setText((String) arg);
    }
}
