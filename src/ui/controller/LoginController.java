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
        System.out.println(username.getText() + " "+ password.getText());
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
    public void getResult(Object arg) {
        if( arg instanceof User)
            print.setText("Done !!!");
        else
            print.setText("Error !!!");
    }

    @Override
    public String sessionException(String arg) {
        return null;
    }
}
