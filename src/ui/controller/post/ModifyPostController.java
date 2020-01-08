package ui.controller.post;

import businessLogic.PostFacade;
import businessLogic.SessionFacade;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import ui.interfaces.LoginInterface;
import ui.model.ApplicationUI;

public class ModifyPostController implements LoginInterface {
    private PostFacade PF = PostFacade.getInstance(this);
    private SessionFacade SF = SessionFacade.getInstance(this);
    private static Object currentId;
    private static String cont;

    @FXML
    TextArea modify_content;

    @FXML
    public void initialize() {
        modify_content.setText(cont);
    }

    public void backToPosts() throws Exception {
        ApplicationUI.postView(ApplicationUI.getStage());
    }

    public void modifyPost() throws Exception {
        PF.modifyPost(currentId, modify_content.getText());
        backToPosts();
    }

    public static void setup(String con, Object id) {
        currentId = id;
        cont = con;
    }

    @Override
    public void printResults(Object arg) {

    }
}
