package ui.controller;

import businessLogic.SessionFacade;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import persistence.data.User;

import ui.interfaces.LoginInterface;
import ui.model.LoginUI;

public class RegisterController implements LoginInterface {

    SessionFacade SF = SessionFacade.getInstance(this);

    @FXML
    Button sendRegister, backToLogin;
    @FXML
    TextField register_username, register_email, register_password, register_confirm_password, register_help;
    @FXML
    Label error;


    public void sendRegister(ActionEvent actionEvent) {
        if (!register_password.getText().equals(register_confirm_password.getText())) {
            error.setText("Passwords do not match");
        } else if (register_username.getText().trim().isEmpty() || register_password.getText().trim().isEmpty() || register_email.getText().trim().isEmpty()) {
            error.setText("You must enter valid credentials");
        } else SF.register(register_username.getText(), register_password.getText(), register_email.getText(), register_help.getText());
    }

    public void resetError() {
        error.setText("");
    }


    @Override
    public void printResults(Object arg) {
        if( arg instanceof User)
            error.setText("Done !!!");
        else
            error.setText((String) arg);
    }

    public void backToLogin(ActionEvent actionEvent) throws Exception {
        LoginUI.loginView(LoginUI.getStage());
    }
}
