package ui.controller;


import businessLogic.SessionFacade;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ui.interfaces.LoginInterface;
import ui.model.ApplicationUI;

public class LoginController implements LoginInterface {

    SessionFacade SF = SessionFacade.getInstance(this);
    @FXML
    Button sendLogin;
    @FXML
    TextField username, password;
    @FXML
    Label print;
    @FXML
    Hyperlink toRegister;


    public void sendLogin(ActionEvent actionEvent) throws Exception {
        if (!username.getText().trim().isEmpty() && !password.getText().trim().isEmpty()) {
            if (SF.login(username.getText(), password.getText())) {
                ApplicationUI.mainPageView(ApplicationUI.getStage());
            }
        } else printResults("Incorrect username or password.");

    }

    public void toRegister (ActionEvent actionEvent) throws Exception {
        ApplicationUI.registerView(ApplicationUI.getStage());
    }

    @Override
    public void printResults(Object arg) {
        print.setText((String) arg);
    }
}
