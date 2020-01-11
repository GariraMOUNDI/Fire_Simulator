package ui.controller;


import businessLogic.SessionFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ui.interfaces.LoginInterface;
import ui.model.ApplicationUI;

/**
 * The type Login controller.
 */
public class LoginController implements LoginInterface {

    /**
     * The session facade instance.
     */
    SessionFacade SF = SessionFacade.getInstance(this);

    @FXML
    private TextField username, password;

    @FXML
    private Label print;

    /**
     * Send login.
     *
     * @param actionEvent the action event
     * @throws Exception the exception
     */
    public void sendLogin(ActionEvent actionEvent) throws Exception {
        if (!username.getText().trim().isEmpty() || !password.getText().trim().isEmpty()) {
            if (SF.login(username.getText(), password.getText())) {
                ApplicationUI.mainPageView(ApplicationUI.getStage());
            }
        } else printResults("You must enter a username and password.");
    }

    /**
     * To register.
     *
     * @param actionEvent the action event
     * @throws Exception the exception
     */
    public void toRegister (ActionEvent actionEvent) throws Exception {
        ApplicationUI.registerView(ApplicationUI.getStage());
    }

    @Override
    public void printResults(Object arg) {
        print.setText((String) arg);
    }
}
