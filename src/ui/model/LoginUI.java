package ui.model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class LoginUI extends Application {

    private static Stage primaryStage;

    public void start(Stage primaryStage) throws Exception{
        loginView(primaryStage);
    }

    public static void loginView(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(LoginUI.class.getResource("../views/LoginView.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        LoginUI.primaryStage = primaryStage;
    }



    public static void registerView(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(LoginUI.class.getResource("../views/RegisterView.fxml"));
        stage.setTitle("Register");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static Stage getStage(){
        return primaryStage;
    }

    public static void mainPageView(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(LoginUI.class.getResource("../views/MainPageView.fxml"));
        stage.setTitle("Main Menu");
        stage.setScene(new Scene(root));
        stage.show();
    }

}
