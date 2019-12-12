package ui.controller;

import com.sun.prism.paint.Color;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import ui.model.LoginUI;

public class LoginController {

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
}
