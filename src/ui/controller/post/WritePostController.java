package ui.controller.post;

import businessLogic.PostFacade;
import businessLogic.SessionFacade;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import ui.interfaces.LoginInterface;
import ui.model.ApplicationUI;

/**
 * The type Write post controller.
 */
public class WritePostController implements LoginInterface {
    private PostFacade PF = PostFacade.getInstance(this);
    private SessionFacade SF = SessionFacade.getInstance(this);

    /**
     * The text area to write the post.
     */
    @FXML
    private TextArea content;
    @FXML
    private Label writeLabel;

    /**
     * Go back to posts view.
     *
     * @throws Exception the exception
     */

    public void initialize(){
        writeLabel.setFont(Font.loadFont(getClass().getResourceAsStream("/resources/fonts/CenturySchoolbook.ttf"), 24));
    }
    public void backToPosts() throws Exception {
        ApplicationUI.postView(ApplicationUI.getStage());
    }

    /**
     * Publish the post.
     *
     * @throws Exception the exception
     */
    public void publishPost() throws Exception {
        if (!content.getText().isEmpty()) {
            PF.writePost(content.getText());
            backToPosts();
        }
    }

    @Override
    public void printResults(Object arg) {

    }
}
