package ui.controller;


import businessLogic.SessionFacade;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import persistence.data.User;
import ui.interfaces.LoginInterface;
import ui.model.LoginUI;

public class LoginController implements LoginInterface {

    SessionFacade SF = new SessionFacade(this);
    @FXML
    Button sendLogin;
    @FXML
    TextField username, password;
    @FXML
    Label print;


    public void sendLogin(ActionEvent actionEvent) {
        SF.login(username.getText(),password.getText());
    }

    public static void main(String[] args) {
        Application.launch(LoginUI.class);
    }

    @Override
    public void printResults(Object arg) {
        if( arg instanceof User)
            print.setText("Done !!!");
        else
            print.setText((String) arg);
    }
}
