package ui.model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RegisterUI extends Application{

    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../views/RegisterView.fxml"));
        primaryStage.setTitle("Register");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}