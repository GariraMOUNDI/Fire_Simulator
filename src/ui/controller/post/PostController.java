package ui.controller.post;

import businessLogic.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import persistence.data.Post;
import ui.interfaces.LoginInterface;
import ui.model.ApplicationUI;

//import java.awt.*;
import javafx.geometry.Insets;

import java.util.List;

public class PostController implements LoginInterface {
    private PostFacade PF = PostFacade.getInstance(this);
    private SessionFacade SF = SessionFacade.getInstance(this);

    @FXML
    ScrollPane post_view;
    @FXML
    BorderPane root;

    @FXML
    public void initialize() {
        List<Post> posts = PF.getAllPosts();

        GridPane pane = new GridPane();
        ColumnConstraints col = new ColumnConstraints();

        col.setHalignment(HPos.CENTER);

        pane.getColumnConstraints().add(col);
        pane.setPadding(new Insets(15, 12, 15, 12));
        pane.setVgap(20);

        int i = posts.size()-1;
        for (Post p : posts) {
            pane.add(createPost(p),0,i);
            i--;
        }

        post_view.setContent(pane);
    }

    public void backToMainPage() throws Exception {
        ApplicationUI.mainPageView(ApplicationUI.getStage());
    }

    public void toWritePost() throws Exception {
        ApplicationUI.writePostView(ApplicationUI.getStage());
    }

    public void toModifyPost() throws Exception {
        ApplicationUI.modifyPostView(ApplicationUI.getStage());
    }

    @Override
    public void printResults(Object arg) {

    }

    private BorderPane createPost(Post post) {
        BorderPane pane = new BorderPane();
        GridPane top = new GridPane();
        TextFlow center = new TextFlow();
        Label user = new Label(post.getUsername());
        Label date = new Label(post.getDate());
        Text cont = new Text(post.getContent());

        pane.prefWidthProperty().bind(root.widthProperty());
        pane.setStyle("-fx-border-color: black;-fx-background-color: white; -fx-background-radius: 5px;-fx-border-radius: 5px");

        if (SF.getUser().getUsername().equals(post.getUsername())) {
            top.setStyle("-fx-background-color: #8ab9ff;-fx-background-radius: 5px 5px 0px 0px;");
            Hyperlink edit = new Hyperlink("Edit...");
            edit.setStyle("-fx-text-fill: blue");
            edit.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    try {
                        ModifyPostController.setup(post.getContent(), post.getId());
                        toModifyPost();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            top.add(edit,2,0);
        } else {
            top.setStyle("-fx-background-color: #c9c9c9;-fx-background-radius: 5px 5px 0px 0px;");
        }
        top.prefWidthProperty().bind(root.widthProperty());

        center.setStyle("-fx-padding: 15;-fx-border-color: #dbdbdb;-fx-border-width: 0 0 1 0");
        center.prefWidthProperty().bind(root.widthProperty());

        user.setPadding(new Insets(0, 15, 0, 15));
        user.setStyle("-fx-font-size: 16;");

        date.setPadding(new Insets(0, 15, 0, 15));
        date.setStyle("-fx-text-alignment: right;");

        cont.setWrappingWidth(500);

        top.add(user,0,0);
        top.add(date,1,0);
        center.getChildren().add(cont);
        pane.setTop(top);
        pane.setCenter(center);
        pane.setBottom(initComments(post));

        return pane;
    }

    private GridPane initComments(Post post) {
        GridPane pane = new GridPane();
        int i = 0;
        if (post.getComments() != null) {
            for (Post c : post.getComments()) {
                pane.add(createComment(c),0,i);
                i++;
            }
        }
        Button button = new Button();
        TextField field = new TextField();
        field.setPromptText("Write your comment here...");
        button.setText("Publish");
        button.setId(""+post.getId());
        button.setStyle("-fx-background-color: #91bdff");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!field.getText().isEmpty()) {
                    PF.writeComment(post.getId(), SF.getUser().getUsername(), field.getText());
                    initialize();
                }
            }
        });
        GridPane commentPane = new GridPane();
        commentPane.setPadding(new Insets(5,5,5,5));
        commentPane.setHgap(5);
        commentPane.getColumnConstraints().add(new ColumnConstraints(415));
        commentPane.getColumnConstraints().add(new ColumnConstraints(125));
        commentPane.add(field,0,0);
        commentPane.add(button,1,0);
        pane.add(commentPane,0,i);
        return pane;
    }

    private GridPane createComment(Post c) {
        GridPane pane = new GridPane();
        pane.getRowConstraints().add(new RowConstraints(10));
        if (SF.getUser().getUsername().equals(c.getUsername())) {
            pane.setStyle("-fx-border-color: gray;-fx-border-width: 0 0 1 0;-fx-padding: 10;-fx-background-color: #f0f6ff");
        } else pane.setStyle("-fx-border-color: gray;-fx-border-width: 0 0 1 0;-fx-padding: 10;-fx-background-color: #f2f2f2");
        pane.getChildren().add(new Label(c.getUsername()+ " : "+c.getContent()));

        return pane;
    }
}
