package ui.model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;


public class ApplicationUI extends Application {

    private static Stage primaryStage;
    private static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();;
    public static void main(String[] args) {
        Application.launch(ApplicationUI.class);
    }

    public void start(Stage primaryStage) throws Exception{
        ApplicationUI.loginView(primaryStage);
    }

    public static Stage getStage(){
        return primaryStage;
    }

    public static void loginView(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(ApplicationUI.class.getResource("../views/LoginView.fxml"));
        stage.setTitle("Login");
        stage.setScene(new Scene(root));
        stage.show();
        stage.setResizable(false);
        stage.setX((dim.getWidth() - stage.getWidth())/2);
        stage.setY((dim.getHeight() - stage.getHeight())/2);
        ApplicationUI.primaryStage = stage;
    }

    public static void registerView(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(ApplicationUI.class.getResource("../views/RegisterView.fxml"));
        stage.setTitle("Register");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setX((dim.getWidth() - stage.getWidth())/2);
        stage.setY((dim.getHeight() - stage.getHeight())/2);
        stage.show();
    }

    public static void mainPageView(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(ApplicationUI.class.getResource("../views/MainPageView.fxml"));
        stage.setTitle("Main Menu");
        stage.setScene(new Scene(root));
        stage.setX((dim.getWidth() - stage.getWidth())/2);
        stage.setY((dim.getHeight() - stage.getHeight())/2);
        stage.show();
    }
}
