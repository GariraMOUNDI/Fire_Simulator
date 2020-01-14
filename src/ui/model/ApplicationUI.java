package ui.model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import persistence.data.Element;
import persistence.data.TypeElementEnum;
import persistence.factories.MongoDBDAOFactory;
import ui.controller.ElementController;
import ui.controller.ElementMakerController;
import ui.controller.FriendsController;
import ui.controller.ProfileController;

import java.awt.*;
import java.io.IOException;


/**
 * The class Application ui. This class sets up all of the different views of the application and offers methods to deploy each of them
 */
public class ApplicationUI extends Application {

    private static Stage primaryStage;

    private static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();;

    private static FXMLLoader loader;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Application.launch(ApplicationUI.class);
    }

    public static void noConnectionVue(Stage stage) throws IOException {
        uploadView(stage, "Database", "../views/NoConnectionView.fxml");
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

    /**
     * Get the stage.
     *
     * @return the stage
     */
    public static Stage getStage(){
        return primaryStage;
    }

    /**
     * Go to login view.
     *
     * @param stage the stage
     * @throws Exception the exception
     */
    public static void loginView(Stage stage) throws Exception{
        uploadView(stage, "Login","../views/LoginView.fxml");
        ApplicationUI.primaryStage = stage;
    }

    /**
     * Go to register view.
     *
     * @param stage the stage
     * @throws Exception the exception
     */
    public static void registerView(Stage stage) throws Exception{
        uploadView(stage, "Register", "../views/RegisterView.fxml");
    }

    /**
     * Go to the map management view.
     *
     * @param stage the stage
     * @throws Exception the exception
     */
    public static void MapManagementView(Stage stage) throws Exception {
        uploadView(stage, "Map Management", "../views/MapManagement/MapManagement.fxml");
    }

    /**
     * Go to the solo menu.
     *
     * @param stage the stage
     * @throws Exception the exception
     */
    public static void toSoloMenu(Stage stage) throws Exception {
        uploadView(stage, "Solo Menu", "../views/soloMenu.fxml");
    }

    /**
     * Go to the map menu.
     *
     * @param stage the stage
     * @throws Exception the exception
     */
    public static void toMapmenu(Stage stage) throws Exception {
        uploadView(stage, "Map Menu", "../views/MapManagement/mapMenu.fxml");
    }

    /**
     * Go to the map maker view.
     *
     * @param stage the stage
     * @throws Exception the exception
     */
    public static void toMapMaker(Stage stage) throws Exception {
        uploadView(stage, "Map maker", "../views/MapManagement/mapMaker.fxml");
    }

    /**
     * Go to the element management view.
     *
     * @param stage the stage
     * @throws Exception the exception
     */
    public static void elementManagementView(Stage stage) throws Exception {
        uploadView(stage, "Element Menu", "../views/ElementManagementView.fxml");
        ((ElementController)loader.getController()).init(stage);
    }

    /**
     * Go to the element maker view.
     *
     * @param stage the stage
     * @throws Exception the exception
     */
    public static void toElementMaker(Stage stage, TypeElementEnum type, Element modify) throws Exception {
        uploadView(stage, "Element Maker", "../views/ElementMaker.fxml");
        ((ElementMakerController)loader.getController()).init(type, modify);
    }

    /**
     * Go to the main page view.
     *
     * @param stage the stage
     * @throws Exception the exception
     */
    public static void mainPageView(Stage stage) throws Exception {
        uploadView(stage, "Main","../views/MainPageView.fxml");
    }

    /**
     * Go to the friends view.
     *
     * @param stage the stage
     * @throws Exception the exception
     */
    public static void friendsView(Stage stage) throws Exception{
        uploadView(stage, "Friends", "../views/FriendsView.fxml");
        ((FriendsController)loader.getController()).init(stage);
    }

    /**
     * Go to the profile view.
     *
     * @param stage the stage
     * @throws Exception the exception
     */
    public static void profileView(Stage stage) throws Exception{
        uploadView(stage, "Profile","../views/ProfileView.fxml");
        ((ProfileController)loader.getController()).init(stage);
    }


    /**
     * Go to the store view.
     *
     * @param stage the stage
     * @throws Exception the exception
     */
    public static void storeView(Stage stage) throws Exception {
        uploadView(stage, "Store","../views/StoreView.fxml");
    }

    /**
     * Go to the post view.
     *
     * @param stage the stage
     * @throws Exception the exception
     */
    public static void postView(Stage stage) throws Exception {
        uploadView(stage, "Posts","../views/post/PostView.fxml");
    }

    /**
     * Go to the write post view.
     *
     * @param stage the stage
     * @throws Exception the exception
     */
    public static void writePostView(Stage stage) throws Exception {
        uploadView(stage, "Write Post","../views/post/WritePostView.fxml");
    }

    /**
     * Go to the modify post view.
     *
     * @param stage the stage
     * @throws Exception the exception
     */
    public static void modifyPostView(Stage stage) throws Exception {
        uploadView(stage, "Modify Post","../views/post/ModifyPostView.fxml");
    }

    /**
     * Go to the simulation view.
     *
     * @param stage the stage
     * @throws Exception the exception
     */
    public static void toSimulation(Stage stage) throws Exception {
        uploadView(stage, "Simulation","../views/Simulation.fxml");
    }
    public static void toSimulationMenu(Stage stage) throws IOException {
        uploadView(stage, "Simulation menu","../views/SimulationMenu.fxml");
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
