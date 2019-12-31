package ui.model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;


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
        stageDeploy(stage, root);
        ApplicationUI.primaryStage = stage;
    }

    public static void registerView(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(ApplicationUI.class.getResource("../views/RegisterView.fxml"));
        stage.setTitle("Register");
        stageDeploy(stage, root);
    }

    public static void MapManagementView(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(ApplicationUI.class.getResource("../views/MapManagement/MapManagement.fxml"));
        stage.setTitle("Map management");
        stageDeploy(stage, root);
    }

    public static void toSoloMenu(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(ApplicationUI.class.getResource("../views/soloMenu.fxml"));
        stage.setTitle("Solo Menu");
        stageDeploy(stage, root);
    }

    public static void toMapmenu(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(ApplicationUI.class.getResource("../views/MapManagement/mapMenu.fxml"));
        stage.setTitle("map Menu");
        stageDeploy(stage, root);
    }

    public static void toMapMaker(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(ApplicationUI.class.getResource("../views/MapManagement/mapMaker.fxml"));
        stage.setTitle("map maker");
        stageDeploy(stage, root);
    }


    public static void mainPageView(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(ApplicationUI.class.getResource("../views/MainPageView.fxml"));
        stage.setTitle("Main Menu");
        stageDeploy(stage, root);
    }

    public static void elementManagementView(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(ApplicationUI.class.getResource("../views/ElementManagementView.fxml"));
        stage.setTitle("Element Menu");
        stageDeploy(stage, root);
    }

    public static void toElementMaker(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(ApplicationUI.class.getResource("../views/ElementMaker.fxml"));
        stage.setTitle("Element Maker");
        stageDeploy(stage, root);
    }


    private static void stageDeploy(Stage stage, Parent root) {
        stage.setScene(new Scene(root));
        stage.show();
        stage.setResizable(false);
        stage.setX((dim.getWidth() - stage.getWidth())/2);
        stage.setY((dim.getHeight() - stage.getHeight())/2);
    }

}
