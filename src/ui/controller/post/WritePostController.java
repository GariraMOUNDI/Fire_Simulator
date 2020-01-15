package ui.controller.post;

import businessLogic.PostFacade;
import businessLogic.SessionFacade;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
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

    /**
     * Go back to posts view.
     *
     * @throws Exception the exception
     */
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
