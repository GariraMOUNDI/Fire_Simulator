package ui.model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import persistence.factories.MongoDBDAOFactory;
import ui.controller.FriendsController;
import ui.controller.ProfileController;

import java.awt.*;
import java.io.IOException;


public class ApplicationUI extends Application {

    private static Stage primaryStage;

    private static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();;

    private static FXMLLoader loader;

    public static void main(String[] args) {
        Application.launch(ApplicationUI.class);
    }



    public void start(Stage primaryStage) throws Exception{
        ApplicationUI.loginView(primaryStage);
    }

    @Override
    public void stop() throws Exception {
        Object controller = loader.getController();
        if ( controller instanceof FriendsController)
            ((FriendsController) controller).getSession().updateUser();
        MongoDBDAOFactory.getInstance().closeConnection();
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
  
    public static void MapManagementView(Stage stage) throws Exception {
        uploadView(stage, "Map Management", "../views/MapManagement/MapManagement.fxml");
    }

    public static void toSoloMenu(Stage stage) throws Exception {
        uploadView(stage, "Solo Menu", "../views/soloMenu.fxml");
    }

    public static void toMapmenu(Stage stage) throws Exception {
        uploadView(stage, "Map Menu", "../views/MapManagement/mapMenu.fxml");
    }

    public static void toMapMaker(Stage stage) throws Exception {
        uploadView(stage, "Map maker", "../views/MapManagement/mapMaker.fxml");
    }
  
   public static void elementManagementView(Stage stage) throws Exception {
        uploadView(stage, "Element Menu", "../views/ElementManagementView.fxml");
    }

    public static void toElementMaker(Stage stage) throws Exception {
        uploadView(stage, "Element Maker", "../views/ElementMaker.fxml");
    }

    public static void mainPageView(Stage stage) throws Exception {
        uploadView(stage, "Main","../views/MainPageView.fxml");
    }

    public static void friendsView(Stage stage) throws Exception{
        uploadView(stage, "Friends", "../views/FriendsView.fxml");
        ((FriendsController)loader.getController()).init(stage);
    }

    public static void profileView(Stage stage) throws Exception{
        uploadView(stage, "Profile","../views/ProfileView.fxml");
        ((ProfileController)loader.getController()).init(stage);
    }

    public static void storeView(Stage stage) throws Exception {
        uploadView(stage, "Store","../views/StoreView.fxml");
    }

    public static void postView(Stage stage) throws Exception {
        uploadView(stage, "Posts","../views/post/PostView.fxml");
    }

    public static void writePostView(Stage stage) throws Exception {
        uploadView(stage, "Write Post","../views/post/WritePostView.fxml");
    }

    public static void modifyPostView(Stage stage) throws Exception {
        uploadView(stage, "Modify Post","../views/post/ModifyPostView.fxml");
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
