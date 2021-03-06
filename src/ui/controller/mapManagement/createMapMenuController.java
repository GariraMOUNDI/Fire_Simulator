package ui.controller.mapManagement;

import businessLogic.MapManagementFacade;
import businessLogic.SessionFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import persistence.data.Matrice;
import persistence.data.Terrain;
import ui.interfaces.LoginInterface;
import ui.model.ApplicationUI;

/**
 * The type Create map menu controller, used by MapMenu view.
 */
public class createMapMenuController implements LoginInterface{
    /**
     * The Map management facade
     */
    private MapManagementFacade MMF = MapManagementFacade.getInstance(this);

    @FXML
    private TextField name_input, size_input;
    @FXML
    private Label error;


    /**
     * Change view to Map management
     *
     * @param actionEvent the action event
     * @throws Exception the exception
     */
    public void toMapManagement(ActionEvent actionEvent) throws  Exception{
        ApplicationUI.MapManagementView(ApplicationUI.getStage());
    }


    /**
     * Method called to create map. This method delegate the task to Map Management facade
     * Once the map created, the Map maker view is called.
     *
     * @throws Exception the exception
     */
    public void createMap() throws Exception {
        if (name_input.getText().trim().isEmpty() || size_input.getText().trim().isEmpty()) {
            printResults("You must enter valid input");
        }
        else{
            MMF.createMap(name_input.getText(),new Matrice(Integer.parseInt(size_input.getText())), MMF.getSession().getUserLoggedIn().getUsername());
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
