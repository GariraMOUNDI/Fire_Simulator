package ui.controller;

import businessLogic.SessionFacade;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import persistence.data.User;

import ui.interfaces.LoginInterface;
import ui.model.ApplicationUI;

/**
 * The type Register controller.
 */
public class RegisterController implements LoginInterface {
    /**
     * The session facade instance.
     */
    SessionFacade SF = SessionFacade.getInstance(this);

    @FXML
    private TextField register_username, register_email, register_password, register_confirm_password, register_help;
    @FXML
    private Label error;


    /**
     * This method checks the user's input and delegates the register action to the session facade.
     *
     * @throws Exception the exception
     */
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

    /**
     * This method resets the fxml label.
     */
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

    /**
     * Go to the main page.
     *
     * @throws Exception the exception
     */
    public void goToMainPage() throws Exception {
        ApplicationUI.mainPageView(ApplicationUI.getStage());
    }

    /**
     * Go back to the login page.
     *
     * @throws Exception the exception
     */
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
