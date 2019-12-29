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

public class MapMakerController implements LoginInterface {

    MapManagementFacade MMF = MapManagementFacade.getInstance(this);
    SessionFacade SF = SessionFacade.getInstance(this);


    @FXML
    private ScrollPane scrollPane;
    @FXML
    Button toMapManagement;
    @FXML
    public void initialize() {
        Terrain terrain = MMF.getCurrentTerrain();
        GridPane matrice = new GridPane();
        for (int x = 0; x < terrain.getMap().getSize(); x++){
            for (int y = 0; y < terrain.getMap().getSize(); y++){
                Pane cell = new Pane();
                cell.setPrefSize(30,30);
                cell.setStyle("-fx-background-color: "+ terrain.getMap().getBox(x,y).getElement().getColor() + ";");
                matrice.add(cell, x, y);
                matrice.setHgap(1);
                matrice.setVgap(1);
            }
        }
        scrollPane.setContent(matrice);
    }

    public void toMapManagement(ActionEvent actionEvent) throws  Exception{
        ApplicationUI.MapManagementView(ApplicationUI.getStage());
    }


    public void printResults(Object arg) {

    }
}
