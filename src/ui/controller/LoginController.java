package ui.controller;


import businessLogic.SessionFacade;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ui.interfaces.LoginInterface;
import ui.model.LoginUI;

public class LoginController implements LoginInterface {

    SessionFacade SF = new SessionFacade(this);
    @FXML
    Button sendLogin;
    @FXML
    TextField username, password;


    public void sendLogin(ActionEvent actionEvent) {
        if (SF.login(username.getText(),password.getText())){
            System.out.println("Connection OK");
        }
        else{
            System.out.println("Connection Echec");
        }

    }

    public static void main(String[] args) {
        Application.launch(LoginUI.class);
    }

    @Override
    public String sessionException(String arg) {
        return null;
    }
}
