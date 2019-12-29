package ui.controller;

import businessLogic.SessionFacade;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import persistence.data.User;
import resources.styles.FriendsButtons;
import resources.styles.UserLabels;
import ui.interfaces.LoginInterface;
import ui.model.ApplicationUI;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FriendsController implements LoginInterface {

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

    @FXML
    public void initialize(){
        refreshLists();
    }

    public void init(Stage stage){
        unknowPane.setPrefWidth(stage.getWidth() / 2.5);
        friendsPane.setPrefWidth(stage.getWidth() / 2.5);
    }

    public void refreshLists(){
        row = new RowConstraints();
        row.setMinHeight(60);
        showList(friendsScrollPane, friendsList, null, session.getUserLoggedInFriends(), 2);
        showList(unknowScrollPane, unknowList, null, session.getOtherUser(), 1);
    }

    public void showList(ScrollPane scroll, GridPane grid, String arg, List<String> users, int nbButtons){
        deleteRows(grid);
        UserLabels user;
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
        scroll.setContent(grid);
    }

    public SessionFacade getSession() {
        return session;
    }

    public void deleteRows(GridPane grid){
        Set<Node> deleteNodes = new HashSet<>();
        for (Node child : grid.getChildren()) {
            deleteNodes.add(child);
        }
        grid.getChildren().removeAll(deleteNodes);
        grid.getRowConstraints().clear();
    }

    public void printSearchResultsFriends(){
        showList(friendsScrollPane, friendsList, searchForfriends.getText(), session.getUserLoggedInFriends(), 2);
    }

    public void printSearchResultsUnknow(){
        showList(unknowScrollPane, unknowList, searchForUnknow.getText(), session.getOtherUser(), 1);
    }

    public void backToMainPage() throws Exception {
        session.updateUser();
        ApplicationUI.mainPageView(ApplicationUI.getStage());
    }

}
