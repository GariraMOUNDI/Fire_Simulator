package ui.controller;

import businessLogic.ElementFacade;
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
import persistence.data.Element;
import persistence.data.Terrain;
import persistence.data.TypeElementEnum;
import ui.interfaces.LoginInterface;
import ui.model.ApplicationUI;

import java.util.List;

public class ElementController implements LoginInterface {

    ElementFacade EF = ElementFacade.getInstance(this);
    SessionFacade SF = SessionFacade.getInstance(this);



    @FXML
    private TextField ElementName_input, flammability_input, color_input, type_input;
    @FXML
    Button createElement;
    @FXML
    ScrollPane rock_view, vegetation_view, water_view;


    public void initialize() {
        List<Element> elements;
        elements = EF.getBasicsElements();
        GridPane rock = new GridPane();
        GridPane water = new GridPane();
        GridPane vegetation = new GridPane();
        ColumnConstraints col = new ColumnConstraints();
        col.setHalignment(HPos.CENTER);
        rock.getColumnConstraints().add(col);
        rock.setPadding(new Insets(15, 12, 15, 12));
        rock.setVgap(20);

        water.getColumnConstraints().add(col);
        water.setPadding(new Insets(15, 12, 15, 12));
        water.setVgap(20);

        vegetation.getColumnConstraints().add(col);
        vegetation.setPadding(new Insets(15, 12, 15, 12));
        vegetation.setVgap(20);

        int i = 0;
        int j = 0;
        int k = 0;

        for (Element e : elements) {
            GridPane gPane = new GridPane();

            gPane.getColumnConstraints().add(col);
            gPane.setPadding(new Insets(20, 20, 20, 20));
            gPane.setHgap(20);

            Label element_name = new Label("Element name : " + e.getElementName());
            Label flammability = new Label("Flammability : " + String.valueOf(e.getFlammability()));
            Button modify = new Button("Modify");
            Button delete = new Button("Delete");


            gPane.add(element_name, 0, 0);
            gPane.add(flammability, 1, 0);
            gPane.add(modify, 2, 0);
            gPane.add(delete, 3, 0);

            if (e.getType().equals(TypeElementEnum.Rock)){
                rock.add(gPane, 0, i);
                i++;
            }
            else if (e.getType().equals(TypeElementEnum.Water)){
                water.add(gPane, 0, j);
                j++;
            }
            else if (e.getType().equals(TypeElementEnum.Vegetation)){
                vegetation.add(gPane, 0, k);
                k++;
            }


        }
        rock_view.setContent(rock);
        water_view.setContent(water);
        vegetation_view.setContent(vegetation);

    }


    @Override
    public void printResults(Object arg) {

    }

    public void createElement(ActionEvent actionEvent){
    //Warning
        EF.createElement(ElementName_input.getText(),Integer.parseInt(flammability_input.getText()),color_input.getText(), TypeElementEnum.Water,SF.getUser().getUsername());
    }


}
