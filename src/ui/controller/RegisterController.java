package ui.controller;

import businessLogic.SessionFacade;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import persistence.data.User;

import ui.interfaces.LoginInterface;
import ui.model.ApplicationUI;

public class RegisterController implements LoginInterface {
    SessionFacade SF = SessionFacade.getInstance(this);

    @FXML
    private Button sendRegister;
    @FXML
    private Hyperlink backToLogin;
    @FXML
    private TextField register_username, register_email, register_password, register_confirm_password, register_help;
    @FXML
    private Label error;
    @FXML
    private ImageView logo_img;


    public void sendRegister() throws Exception {
        if (register_username.getText().trim().isEmpty() || register_password.getText().trim().isEmpty() || register_email.getText().trim().isEmpty()) {
            printResults("You must enter valid credentials");
        } else if (!register_password.getText().equals(register_confirm_password.getText())) {
            printResults("Passwords do not match");
        } else if (!checkExisting()) {
            SF.register(register_username.getText(), register_password.getText(), register_email.getText(), register_help.getText());
            goToMainPage();
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

    public void goToMainPage() throws Exception {
        ApplicationUI.mainPageView(ApplicationUI.getStage());
    }
    public void backToLogin() throws Exception {
        ApplicationUI.loginView(ApplicationUI.getStage());
    }

    @Override
    public void printResults(Object arg) {
        if( arg instanceof User)
            error.setText("Done !!!");
        else
            error.setText((String) arg);
    }
}
