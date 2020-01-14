package ui.controller;

import businessLogic.SessionFacade;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import ui.interfaces.LoginInterface;
import ui.model.ApplicationUI;

import java.util.concurrent.ExecutionException;

/**
 * The type Main page controller.
 */
public class MainPageController implements LoginInterface {
    /**
     * The session facade instance.
     */
    SessionFacade SF = SessionFacade.getInstance(this);

    @FXML
    Button button_main, button_main2;

    @FXML
    public void initialize() {
        if (button_main != null && button_main2 != null) {
            button_main.setFont(Font.loadFont(getClass().getResourceAsStream("/resources/fonts/BritannicBold.ttf"),25));
            button_main2.setFont(Font.loadFont(getClass().getResourceAsStream("/resources/fonts/BritannicBold.ttf"),25));
        }
    }

    /**
     * Log out.
     *
     * @throws Exception the exception
     */
    public void logOut() throws Exception {
        SF.logout();
        backToLogin();
    }

    /**
     * To posts.
     *
     * @throws Exception the exception
     */
    public void toPosts() throws Exception {
        ApplicationUI.postView(ApplicationUI.getStage());
    }

    /**
     * Back to login.
     *
     * @throws Exception the exception
     */
    public void backToLogin() throws Exception {
        ApplicationUI.loginView(ApplicationUI.getStage());
    }

    /**
     * To map management.
     *
     * @throws Exception the exception
     */
    public void toMapManagement() throws Exception {
        ApplicationUI.MapManagementView(ApplicationUI.getStage());
    }

    /**
     * To solo menu.
     *
     * @throws Exception the exception
     */
    public void toSoloMenu() throws Exception {
        ApplicationUI.toSoloMenu(ApplicationUI.getStage());
    }

    /**
     * Back to main menu.
     *
     * @throws Exception the exception
     */
    public void backToMainMenu() throws Exception {
        ApplicationUI.mainPageView(ApplicationUI.getStage());
    }

    /**
     * Element management view.
     *
     * @throws Exception the exception
     */
    public void elementManagementView() throws Exception{
        ApplicationUI.elementManagementView(ApplicationUI.getStage());
    }

    /**
     * To store.
     *
     * @throws Exception the exception
     */
    public void toStore() throws Exception {
        ApplicationUI.storeView(ApplicationUI.getStage());
    }

    @Override
    public void printResults(Object arg) {

    }

    /**
     * Go to friends window.
     *
     * @throws Exception the exception
     */
    public void goToFriendsWindow() throws Exception {
        ApplicationUI.friendsView(ApplicationUI.getStage());
    }

    /**
     * Go to profile page.
     *
     * @throws Exception the exception
     */
    public void goToProfilePage() throws Exception{
        ApplicationUI.profileView(ApplicationUI.getStage());
    }

    public void toSimulation(ActionEvent actionEvent) throws Exception {
        ApplicationUI.toSimulation(ApplicationUI.getStage());
    }
    public void toSimulationMenu(ActionEvent actionEvent) throws Exception {
        ApplicationUI.toSimulationMenu(ApplicationUI.getStage());
    }
}
