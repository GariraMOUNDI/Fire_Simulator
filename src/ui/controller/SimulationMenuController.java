package ui.controller;
import businessLogic.MapManagementFacade;
import businessLogic.SessionFacade;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import persistence.data.Matrice;
import persistence.data.Terrain;
import persistence.data.User;
import ui.interfaces.LoginInterface;
import ui.model.ApplicationUI;

import javax.swing.*;
import java.util.List;


public class SimulationMenuController implements LoginInterface{
    /**
     * The map management facade instance.
     */
    MapManagementFacade MMF = MapManagementFacade.getInstance(this);
    /**
     * The session facade instance.
     */
    SessionFacade SF = SessionFacade.getInstance(this);


    @FXML
    ScrollPane maps_view;

    /**
     * The initialize method prepares all of the javafx for this view.
     */
    @FXML
    public void initialize() {
        List<Terrain> maps;
        maps = MMF.getUserMaps(SF.getUserLoggedIn().getUsername());
        if (!maps.isEmpty()) {
            GridPane pane = new GridPane();
            ColumnConstraints col = new ColumnConstraints();

            col.setHalignment(HPos.CENTER);
            pane.getColumnConstraints().add(col);
            pane.setPadding(new Insets(15, 12, 15, 12));
            pane.setVgap(20);
            int i = maps.size() - 1;
            for (Terrain m : maps) {
                GridPane gPane = new GridPane();
                Label map_name = new Label("Map name : "+ m.getName());
                Label map_size = new Label("Size : " + String.valueOf(m.getMap().getSize()));
                Button run = new Button("Run simulation");

                gPane.add(map_name, 0, 0);
                gPane.add(map_size, 1, 0);
                gPane.add(run, 2, 0);
                run.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        try {
                            MMF.setCurrentTerrain(m);
                            ApplicationUI.toSimulation(ApplicationUI.getStage());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                gPane.setHgap(50);
                gPane.setVgap(50);
                pane.add(gPane, 0, i);
                i--;
            }
            maps_view.setContent(pane);
        }
    }
    @Override
    public void printResults(Object arg) {

    }

    public void backToMainPage(MouseEvent mouseEvent) throws Exception {
        ApplicationUI.mainPageView(ApplicationUI.getStage());
    }
}