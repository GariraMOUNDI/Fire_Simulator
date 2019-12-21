package ui.controller;

import businessLogic.SessionFacade;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import persistence.data.User;

import ui.interfaces.LoginInterface;
import ui.model.LoginUI;

public class RegisterController implements LoginInterface {
    private boolean alreadyExists = false;
    SessionFacade SF = SessionFacade.getInstance(this);

    @FXML
    Button sendRegister;
    @FXML
    Hyperlink backToLogin;
    @FXML
    TextField register_username, register_email, register_password, register_confirm_password, register_help;
    @FXML
    Label error;


    public void sendRegister(ActionEvent actionEvent) {
        if (register_username.getText().trim().isEmpty() || register_password.getText().trim().isEmpty() || register_email.getText().trim().isEmpty()) {
            error.setText("You must enter valid credentials");
        } else if (!register_password.getText().equals(register_confirm_password.getText())) {
            error.setText("Passwords do not match");
        } else if (!checkExisting()) {
            SF.register(register_username.getText(), register_password.getText(), register_email.getText(), register_help.getText());
        }
    }

    public void resetError() {
        error.setText("");
    }

    private boolean checkExisting() {
        if (!register_username.getText().trim().isEmpty()) {
            if (SF.exists("username", register_username.getText())) {
                printResults("This username is already taken!");
                return true;
            }
        }
        if (!register_email.getText().trim().isEmpty()) {
            if (SF.exists("email", register_email.getText())) {
                printResults("This email is already taken!");
                return true;
            }
        }
        return false;
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
