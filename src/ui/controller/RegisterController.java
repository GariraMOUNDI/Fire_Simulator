package ui.controller;

import businessLogic.SessionFacade;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import persistence.data.User;
import ui.model.LoginUI;

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

public class RegisterController implements LoginInterface {
    SessionFacade SF = new SessionFacade(this);
    @FXML
    Button sendRegister;
    @FXML
    TextField username, email, confirm_email, password, confirm_password;
    @FXML
    Label print;


    public void sendRegister(ActionEvent actionEvent) {
        if (!email.getText().equals(confirm_email.getText())) {
            print.setText("Emails do not match");
        } else if (!password.getText().equals(confirm_password.getText())) {
            print.setText("Passwords do not match");
        } else SF.register(username.getText(),password.getText(), email.getText());
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
