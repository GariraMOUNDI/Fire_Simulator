package ui.controller;


import businessLogic.SessionFacade;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import persistence.data.User;
import ui.interfaces.LoginInterface;
import ui.model.LoginUI;

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
        if(SF.login(username.getText(),password.getText())){
            LoginUI.mainPageView(LoginUI.getStage());
        };
    }

    public void toRegister (ActionEvent actionEvent) throws Exception {
        LoginUI.registerView(LoginUI.getStage());
    }

    public static void main(String[] args) {
        Application.launch(LoginUI.class);
    }

    @Override
    public void printResults(Object arg) {
        print.setText((String) arg);
    }
}
