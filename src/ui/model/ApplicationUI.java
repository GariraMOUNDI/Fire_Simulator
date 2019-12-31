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
    private static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private static FXMLLoader loader;

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
        uploadView(stage, "Login","../views/LoginView.fxml");
        ApplicationUI.primaryStage = stage;
    }

    public static void registerView(Stage stage) throws Exception{
        uploadView(stage, "Register", "../views/RegisterView.fxml");
    }

    public static void mainPageView(Stage stage) throws Exception{
        uploadView(stage, "Main","../views/MainPageView.fxml");
    }

    public static void storeView(Stage stage) throws Exception {
        uploadView(stage, "Store","../views/StoreView.fxml");
    }

    private static void uploadView(Stage stage,String title, String path) throws IOException {
        loader = new FXMLLoader(ApplicationUI.class.getResource(path));
        Parent root = loader.load();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
        stage.setX((dim.getWidth() - stage.getWidth())/2);
        stage.setY((dim.getHeight() - stage.getHeight())/2);
        stage.setResizable(false);
    }
}
