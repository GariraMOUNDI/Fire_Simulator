package ui.controller;

import businessLogic.SessionFacade;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import persistence.data.User;
import resources.styles.FriendsButtons;
import resources.styles.UserLabels;
import ui.interfaces.LoginInterface;
import ui.model.ApplicationUI;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The type Friends controller.
 */
public class FriendsController implements LoginInterface {

    /**
     * The Session facade instance.
     */
    SessionFacade session = SessionFacade.getInstance(this);
    private RowConstraints row;

    @FXML
    private BorderPane friendsPane,unknowPane;

    @FXML
    private ScrollPane unknowScrollPane, friendsScrollPane;

    @FXML
    private GridPane friendsList, unknowList;

    @FXML
    private TextField searchForfriends, searchForUnknow;

    @Override
    public void printResults(Object arg) {

    }

    /**
     * The initialize method, called automatically.
     */
    @FXML
    public void initialize(){
        refreshLists();
    }

    /**
     * The init method, called from ApplicationUI.java.
     *
     * @param stage the stage
     */
    public void init(Stage stage){
        unknowPane.setPrefWidth(stage.getWidth() / 2.5);
        friendsPane.setPrefWidth(stage.getWidth() / 2.5);
    }

    /**
     * This method refresh the user's lists.
     */
    public void refreshLists(){
        row = new RowConstraints();
        row.setMinHeight(60);
        showList(friendsScrollPane, friendsList, null, session.getUserLoggedInFriends(), 2);
        showList(unknowScrollPane, unknowList, null, session.getOtherUser(), 1);
    }

    /**
     * The show list method initializes the list of users in fxml.
     *
     * @param scroll    the scroll
     * @param grid      the grid
     * @param arg       the arg
     * @param users     the users
     * @param nbButtons the nb buttons
     */
    public void showList(ScrollPane scroll, GridPane grid, String arg, List<String> users, int nbButtons){
        deleteRows(grid);
        UserLabels user;
        if (users.size() == 0){
            user = new UserLabels("\n\tYou need a friend to fight with !!!");
            user.setTextFill(Paint.valueOf("red"));
            user.setStyle("-fx-font-weight: regular;");
            grid.add(user,0,0,3,3);
        }else{
            int i = 0;
            boolean add;
            for (String name : users){
                if (arg == null)
                    add = true;
                else
                if (name.contains(arg))
                    add = true;
                else
                    add = false;

                if(add){
                    user = new UserLabels(name);
                    grid.getRowConstraints().add(row);
                    grid.add( user, 0, i );

                    if (nbButtons == 2){
                        grid.add(new FriendsButtons(this,"resources/icons/fight.png", name),1,i);
                        grid.add(new FriendsButtons(this,"resources/icons/remove.png", name),2,i);
                    }else
                    if (nbButtons == 1)
                        grid.add(new FriendsButtons(this,"resources/icons/add.png", name),2,i);

                    i++;
                }
            }
        }
        scroll.setContent(grid);
    }

    /**
     * Gets the session facade instance.
     *
     * @return the session
     */
    public SessionFacade getSession() {
        return session;
    }

    /**
     * Delete rows. This method is used to reset the grid pane.
     *
     * @param grid the grid
     */
    public void deleteRows(GridPane grid){
        Set<Node> deleteNodes = new HashSet<>();
        for (Node child : grid.getChildren()) {
            deleteNodes.add(child);
        }
        grid.getChildren().removeAll(deleteNodes);
        grid.getRowConstraints().clear();
    }

    /**
     * Print search results friends.
     */
    public void printSearchResultsFriends(){
        showList(friendsScrollPane, friendsList, searchForfriends.getText(), session.getUserLoggedInFriends(), 2);
    }

    /**
     * Print search results unknown.
     */
    public void printSearchResultsUnknown(){
        showList(unknowScrollPane, unknowList, searchForUnknow.getText(), session.getOtherUser(), 1);
    }

    /**
     * Back to main page.
     *
     * @throws Exception the exception
     */
    public void backToMainPage() throws Exception {
        session.updateUser();
        ApplicationUI.mainPageView(ApplicationUI.getStage());
    }

}
