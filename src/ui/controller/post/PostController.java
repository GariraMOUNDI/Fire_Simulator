package ui.controller.post;

import businessLogic.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import persistence.data.Post;
import ui.interfaces.LoginInterface;
import ui.model.ApplicationUI;

//import java.awt.*;
import javafx.geometry.Insets;

import java.util.List;

/**
 * The type Post controller.
 */
public class PostController implements LoginInterface {
    private PostFacade PF = PostFacade.getInstance(this);
//    private SessionFacade SF = SessionFacade.getInstance(this);

    private Image image = new Image("resources/icons/gear.png",20, 20, false, false);

    /**
     * The main scroll pane.
     */
    @FXML
    private ScrollPane post_view;
    /**
     * The Root border pane.
     */
    @FXML
    private BorderPane root;
    /**
     * The My posts checkbox.
     */
    @FXML
    private CheckBox my_posts;

    @FXML
    private Label postLabel;

    /**
     * Initialize the fxml.
     * This method gets the posts from the PostFacade and creates fxml elements to represent them graphically
     */
    @FXML
    public void initialize() {
        List<Post> posts;
        postLabel.setFont(Font.loadFont(getClass().getResourceAsStream("/resources/fonts/CenturySchoolbook.ttf"), 24));
        if (my_posts.isSelected()) posts = PF.getUserPosts();
        else posts = PF.getAllPosts();

        GridPane pane = new GridPane();
        ColumnConstraints col = new ColumnConstraints();

        col.setHalignment(HPos.CENTER);
        pane.getColumnConstraints().add(col);
        pane.setVgap(20);

        int i = posts.size()-1;
        for (Post p : posts) {
            pane.add(createPost(p),0,i);
            i--;
        }
        post_view.setContent(pane);
    }

    /**
     * Go back to the main page view.
     *
     * @throws Exception the exception
     */
    public void backToMainPage() throws Exception {
        ApplicationUI.mainPageView(ApplicationUI.getStage());
    }

    /**
     * Go to the write post view.
     *
     * @throws Exception the exception
     */
    public void toWritePost() throws Exception {
        ApplicationUI.writePostView(ApplicationUI.getStage());
    }

    /**
     * Go to the modify post view.
     *
     * @throws Exception the exception
     */
    public void toModifyPost() throws Exception {
        ApplicationUI.modifyPostView(ApplicationUI.getStage());
    }

    @Override
    public void printResults(Object arg) {

    }

    /**
     * This method creates a javafx border pane that is the graphical view of post passed as a parameter.
     * @param post the post to be created
     * @return the border pane
     */
    private BorderPane createPost(Post post) {
        BorderPane pane = new BorderPane();
        GridPane top = new GridPane();
        TextFlow center = new TextFlow();
        Label user = new Label(PF.getUsernameFromId(post.getUserId()));
        Label date = new Label(post.getDate());
        Text cont = new Text(post.getContent());

        pane.prefWidthProperty().bind(root.widthProperty());

        pane.setStyle("-fx-border: none;-fx-background-color: white; -fx-background-radius: 5px;-fx-border-radius: 5px");
        if (PF.getUserId().equals(post.getUserId())) {
            MenuButton menuButton = new MenuButton();
            menuButton.setGraphic(new ImageView(image));
            menuButton.setStyle("-fx-background-color: #e6f0ff");

            top.setStyle("-fx-background-color: #8ab9ff;-fx-background-radius: 5px 5px 0px 0px;");
            MenuItem edit = new MenuItem("Edit...");
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

            MenuItem delete = new MenuItem("Delete post");
            delete.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    try {
                        PF.deletePost(post.getId());
                        initialize();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            menuButton.getItems().addAll(edit, delete);
            top.setHalignment(menuButton, HPos.RIGHT);
            top.add(menuButton,2,0);
        } else {
            top.setStyle("-fx-background-color: #c9c9c9;-fx-background-radius: 5px 5px 0px 0px;");
        }
        top.prefWidthProperty().bind(root.widthProperty());
        double width = root.getPrefWidth();
        top.getColumnConstraints().add(new ColumnConstraints(width/2.5));
        top.getColumnConstraints().add(new ColumnConstraints(width/3));
        top.getColumnConstraints().add(new ColumnConstraints(width - width/2.5 - width/3 - 50));
        top.setPadding(new Insets(2.5,2.5,2.5,2.5));

        center.setStyle("-fx-padding: 15;-fx-border-color: #dbdbdb;-fx-border-width: 0 0 1 0");
        center.prefWidthProperty().bind(root.widthProperty());

        user.setPadding(new Insets(5, 15, 5, 15));
        user.setStyle("-fx-font-size: 18;");

        date.setPadding(new Insets(0, 15, 0, 15));
        date.setStyle("-fx-font-size: 10");

        cont.setWrappingWidth(500);

        top.add(user,0,0);
        top.add(date,1,0);
        center.getChildren().add(cont);
        pane.setTop(top);
        pane.setCenter(center);
        pane.setBottom(initComments(post));

        return pane;
    }

    /**
     * This method creates a javafx grid pane that is the graphical view of the comments attached to the post passed as a parameter.
     * @param post the post
     * @return the javafx grid pane containing the comments
     */
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
                    PF.writeComment(post.getId(), field.getText());
                    initialize();
                }
            }
        });
        GridPane commentPane = new GridPane();
        commentPane.prefWidthProperty().bind(root.widthProperty());
        commentPane.setPadding(new Insets(5,5,5,5));
        commentPane.setHgap(5);
        commentPane.add(field,0,0);
        commentPane.add(button,1,0);
        pane.add(commentPane,0,i);
        return pane;
    }

    /**
     * This method creates a javafx grid pane that is the graphical view of a comment passed as a parameter.
     * @param c the comment (which is of type Post)
     * @return the javafx grid pane that is the graphical view of the comment
     */
    private GridPane createComment(Post c) {
        GridPane pane = new GridPane();
        pane.getRowConstraints().add(new RowConstraints(10));
        pane.prefWidthProperty().bind(root.widthProperty());
        if (PF.getUserId().equals(c.getUserId())) {
            pane.setStyle("-fx-border-color: #dbdbdb;-fx-border-width: 0 0 1 0;-fx-padding: 10;-fx-background-color: #f0f6ff");
        } else pane.setStyle("-fx-border-color: #dbdbdb;-fx-border-width: 0 0 1 0;-fx-padding: 10;-fx-background-color: #f2f2f2");
        pane.getChildren().add(new Label(PF.getUsernameFromId(c.getUserId())+ " : "+c.getContent()));

        return pane;
    }
}
