package ui.controller;

import com.sun.prism.paint.Color;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import ui.interfaces.LoginInterface;
import ui.model.LoginUI;

public class LoginController implements LoginInterface {

    @FXML
    Button bonjour;
    @FXML
    TextArea text;

    public void clikedButton(ActionEvent actionEvent) {
        System.out.println("Button clicked");
        text.setText(text.getText() + "\nParfait");
    }

    public static void main(String[] args) {
        Application.launch(LoginUI.class);
    }

    @Override
    public String sessionException(String arg) {
        return null;
    }
}
