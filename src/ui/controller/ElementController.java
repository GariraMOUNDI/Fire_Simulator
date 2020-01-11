package ui.controller;

import businessLogic.ElementFacade;
import businessLogic.SessionFacade;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import persistence.data.Element;
import persistence.data.Terrain;
import persistence.data.TypeElementEnum;
import resources.styles.ElementButtons;
import resources.styles.ElementLabels;
import ui.interfaces.LoginInterface;
import ui.model.ApplicationUI;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ElementController implements LoginInterface {

    ElementFacade EF = ElementFacade.getInstance(this);

    @FXML
    ScrollPane rockScrollPane, waterScrollPane, vegetationScrollPane;

    @FXML
    GridPane rockGridPane, vegetationGridPane, waterGridPane;

    private List<Element> elements;

    public void initialize() {
        elements = EF.getUserElements(EF.getUserLoggedIn().getUsername());
        showElements();
    }


    @Override
    public void printResults(Object arg) {  }

    public void deleteElement(Element element){
        EF.deleteElement(element);
    }

    public void backToSoloMenu() throws Exception {
        ApplicationUI.toSoloMenu(ApplicationUI.getStage());
    }

    public void goToElementMaker() throws Exception {
        ApplicationUI.toElementMaker(ApplicationUI.getStage());
    }

    public void showElements(){
        ElementLabels name_value, flammability_value,element_name,flammability;
        ElementButtons modify, delete;
        deleteRows(rockGridPane);
        deleteRows(waterGridPane);
        deleteRows(vegetationGridPane);

        RowConstraints row = new RowConstraints();
        row.setMinHeight(60);

        int i = 0, j=0, k=0;
        for ( Element e : elements){
            element_name = new ElementLabels("Name : ");
            flammability = new ElementLabels("Flammability : ");
            name_value = new ElementLabels(e.getElementName());
            flammability_value = new ElementLabels(""+e.getFlammability());
            modify = new ElementButtons("Modify",this,e);
            delete = new ElementButtons("Delete",this,e);
            if (e.getType().equals(TypeElementEnum.Rock)){

                rockGridPane.getRowConstraints().add(row);
                rockGridPane.add(element_name, 0,i);
                rockGridPane.add(name_value, 1, i);
                rockGridPane.add(flammability,2,i);
                rockGridPane.add(flammability_value,3,i);
                if (e.isBasic())
                    rockGridPane.add(modify,4,i,1,2);
                else{
                    rockGridPane.add(modify,4,i);
                    rockGridPane.add(delete,5,i);
                }
                i++;
            }
            else if (e.getType().equals(TypeElementEnum.Water)){
                waterGridPane.getRowConstraints().add(row);
                waterGridPane.add(element_name, 0,j);
                waterGridPane.add(name_value, 1, j);
                waterGridPane.add(flammability,2,j);
                waterGridPane.add(flammability_value,3,j);
                if (e.isBasic())
                    waterGridPane.add(modify,4,j,1,2);
                else{
                    waterGridPane.add(modify,4,j);
                    waterGridPane.add(delete,5,j);
                }
                j++;
            }
            else if (e.getType().equals(TypeElementEnum.Vegetation)){
                vegetationGridPane.getRowConstraints().add(row);
                vegetationGridPane.add(element_name, 0,k);
                vegetationGridPane.add(name_value, 1, k);
                vegetationGridPane.add(flammability,2,k);
                vegetationGridPane.add(flammability_value,3,k);
                if (e.isBasic())
                    vegetationGridPane.add(modify,4,k,2,1);
                else{
                    vegetationGridPane.add(modify,4,k);
                    vegetationGridPane.add(delete,5,k);
                }
                k++;
            }
        }

        rockScrollPane.setContent(rockGridPane);
        waterScrollPane.setContent(waterGridPane);
        vegetationScrollPane.setContent(vegetationGridPane);
    }

    private void deleteRows(GridPane grid){
        Set<Node> deleteNodes = new HashSet<>();
        for (Node child : grid.getChildren())
            deleteNodes.add(child);
        grid.getChildren().removeAll(deleteNodes);
        grid.getRowConstraints().clear();
    }

    public ElementFacade getFacade(){
        return EF;
    }
}
