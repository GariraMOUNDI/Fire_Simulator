package ui.controller.mapManagement;
import businessLogic.ElementFacade;
import businessLogic.MapManagementFacade;
import businessLogic.SessionFacade;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import persistence.data.Box;
import persistence.data.Element;
import persistence.data.Terrain;
import ui.interfaces.LoginInterface;
import ui.model.ApplicationUI;

import java.util.List;

/**
 * The type Map maker controller, used by map Maker view.
 */
public class MapMakerController implements LoginInterface {

    /**
     * The Map management facade
     */
    MapManagementFacade MMF = MapManagementFacade.getInstance(this);
    /**
     * The Session facade
     */
    SessionFacade SF = SessionFacade.getInstance(this);
    /**
     * The Element facade.
     */
    ElementFacade EF = ElementFacade.getInstance(this);


    @FXML
    /**
     * This scroll pane will contain a GridPane who represent the Matrix of a Terrain.
     */
    private ScrollPane scrollPane;
    @FXML
    Button toMapManagement,
    saveMap;

    @FXML
    ScrollPane element_view;
    final Element[] currentElement = new Element[1];
    /**
     * Initialize method : This method will get the currentTerrain set in the façade,
     * and for each box of the matrix, it will fill the GridPane.
     * This method get all Elements of the current User (+ BasicsElements) and print them.
     */
    @FXML
    public void initialize() {
        Terrain terrain = MMF.getCurrentTerrain();
        GridPane matrice = new GridPane();


        for (int x = 0; x < terrain.getMap().getSize(); x++){
            for (int y = 0; y < terrain.getMap().getSize(); y++){
                Box currentBox = terrain.getMap().getBox(x,y);
                Shape cell =(Shape) new javafx.scene.shape.Rectangle(30,30);
                cell.setFill(Color.web(terrain.getMap().getBox(x,y).getElement().getColor()));
                cell.setOnMouseClicked(new EventHandler<MouseEvent>()
                {
                    /**
                     * This method check if currentElement is not null, if so, it set this element to
                     * the clicked box.
                     */
                    @Override
                    public void handle(MouseEvent t) {
                        if(currentElement[0] != null){
                            currentBox.setElement(currentElement[0]);
                            matrice.getScene().setCursor(Cursor.DEFAULT);
                            initialize();
                        }
                    }
                });
                matrice.add(cell, x, y);
                matrice.setHgap(1);
                matrice.setVgap(1);
            }
        }
        scrollPane.setContent(matrice);
        List<Element> elements = EF.getAllElements(SF.getUserLoggedIn().getUsername());
        GridPane element_pane = new GridPane();

        int i = elements.size()-1;
        for (Element e : elements){
            Pane pane = new Pane();
            Label name = new Label(e.getElementName());
            pane.setStyle("-fx-background-color: "+ e.getColor() + ";");
            pane.setOnMouseClicked(new EventHandler<MouseEvent>()
            {
                /**
                 * This method set the current Element
                 */
                @Override
                public void handle(MouseEvent t) {
                    pane.getScene().setCursor(Cursor.HAND);
                    currentElement[0] = e;
                }
            });
            pane.getChildren().add(name);
            element_pane.add(pane,0,i);
            i--;
        }
        element_pane.setHgap(1);
        element_pane.setVgap(1);

        element_view.setContent(element_pane);

    }

    /**
     * Save map on database, delegation to the facade.
     *
     * @throws Exception the exception
     */
    public void saveMap() throws Exception {
        MMF.saveMap(MMF.getCurrentTerrain());
        ApplicationUI.MapManagementView(ApplicationUI.getStage());
    }

    /**
     * this method call the Map management view. used by back button.
     *
     * @throws Exception the exception
     */
    public void backToMapManagement() throws  Exception{
        ApplicationUI.MapManagementView(ApplicationUI.getStage());
    }


    public void printResults(Object arg) {

    }
}
