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

/**
 * The type Element controller.
 */
public class ElementController implements LoginInterface {

    /**
     * The element facade instance.
     */
    ElementFacade EF = ElementFacade.getInstance(this);
    /**
     * The Sf.
     */
    SessionFacade SF = SessionFacade.getInstance(this);


    /**
     * The Rock scroll pane.
     */
    @FXML
    ScrollPane rockScrollPane, /**
     * The Water scroll pane.
     */
    waterScrollPane, /**
     * The Vegetation scroll pane.
     */
    vegetationScrollPane;

    /**
     * The Rock grid pane.
     */
    @FXML
    GridPane rockGridPane, /**
     * The Vegetation grid pane.
     */
    vegetationGridPane, /**
     * The Water grid pane.
     */
    waterGridPane;

    private List<Element> elements;

    /**
     * This method initializes the fxml representation of the elements.
     */
    public void initialize() {
        elements = EF.getUserElements(EF.getUserLoggedIn().getUsername());
        showElements();
    }

    /**
     * Back to solo menu.
     *
     * @throws Exception the exception
     */
    public void backToSoloMenu() throws Exception {
        ApplicationUI.toSoloMenu(ApplicationUI.getStage());
    }

    /**
     * Go to element maker.
     *
     * @throws Exception the exception
     */
    public void goToElementMaker() throws Exception {
        ApplicationUI.toElementMaker(ApplicationUI.getStage());
    }

    /**
     * Show elements.
     */
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

    }


    @Override
    public void printResults(Object arg) {

    }


    /**
     * Delete element.
     *
     * @param element the element
     */
    public void deleteElement(Element element){
        EF.deleteElement(element);
    }

    /**
     * Create basic pane grid pane.
     *
     * @param e the e
     * @return the grid pane
     */
    public GridPane createBasicPane(Element e){
        GridPane gPane = new GridPane();
        ColumnConstraints col = new ColumnConstraints();
        col.setHalignment(HPos.CENTER);
        gPane.getColumnConstraints().add(col);
        gPane.setPadding(new Insets(20, 10, 20, 10));
        gPane.setHgap(20);

        Label element_name = new Label("Element name : " + e.getElementName());
        Label flammability = new Label("Flammability : " + String.valueOf(e.getFlammability()));
        Button modify = new Button("Modify");
        modify.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    EF.setCurrentElement(e);
                    ApplicationUI.toElementMaker(ApplicationUI.getStage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        gPane.add(element_name, 0, 0);
        gPane.add(flammability, 1, 0);
        gPane.add(modify, 2, 0);

        return gPane;
    }


    private void deleteRows(GridPane grid){
        Set<Node> deleteNodes = new HashSet<>();
        for (Node child : grid.getChildren())
            deleteNodes.add(child);
        grid.getChildren().removeAll(deleteNodes);
        grid.getRowConstraints().clear();
    }

    /**
     * Get facade element facade.
     *
     * @return the element facade
     */
    public ElementFacade getFacade(){
        return EF;
    }
}
