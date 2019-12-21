package ui.controller;


import businessLogic.SessionFacade;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ui.interfaces.LoginInterface;
import ui.model.LoginUI;

public class LoginController implements LoginInterface {

    SessionFacade SF = SessionFacade.getInstance(this);
    @FXML
    Button sendLogin;
    @FXML
    Button registerView;
    @FXML
    TextField username, password;
    @FXML
    Label print;


    public void sendLogin(ActionEvent actionEvent) {
        SF.login(username.getText(),password.getText());
    }

    public void registerView (ActionEvent actionEvent) throws Exception {LoginUI.registerView(LoginUI.getStage()); }

    public static void main(String[] args) {
        Application.launch(LoginUI.class);
    }

    @Override
    public void printResults(Object arg) {
        print.setText((String) arg);
    }
}
