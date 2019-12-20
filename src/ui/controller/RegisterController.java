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
    TextField register_username, register_email, register_password, register_confirm_password, register_help;
    @FXML
    Label print;


    public void sendRegister(ActionEvent actionEvent) {
        if (!register_password.getText().equals(register_confirm_password.getText())) {
            print.setText("Passwords do not match");
        } else SF.register(register_username.getText(), register_password.getText(), register_email.getText(), register_help.getText());
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
