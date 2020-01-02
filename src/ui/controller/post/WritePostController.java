package ui.controller.post;

import businessLogic.PostFacade;
import businessLogic.SessionFacade;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import ui.interfaces.LoginInterface;
import ui.model.ApplicationUI;

public class WritePostController implements LoginInterface {
    private PostFacade PF = PostFacade.getInstance(this);
    private SessionFacade SF = SessionFacade.getInstance(this);

    @FXML
    TextArea content;

    public void backToPosts() throws Exception {
        ApplicationUI.postView(ApplicationUI.getStage());
    }

    public void publishPost() throws Exception {
        if (!content.getText().isEmpty()) {
            PF.writePost(SF.getUser().getUsername(), content.getText());
            backToPosts();
        }
    }

    @Override
    public void printResults(Object arg) {

    }
}
