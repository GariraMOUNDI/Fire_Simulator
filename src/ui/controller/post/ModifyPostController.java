package ui.controller.post;

import businessLogic.PostFacade;
import businessLogic.SessionFacade;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import ui.interfaces.LoginInterface;
import ui.model.ApplicationUI;

/**
 * The type Modify post controller.
 */
public class ModifyPostController implements LoginInterface {
    private PostFacade PF = PostFacade.getInstance(this);
    private SessionFacade SF = SessionFacade.getInstance(this);
    private static Object currentId;
    private static String cont;

    /**
     * The elements from the fxml.
     */
    @FXML
    private TextArea modify_content;

    /**
     * Initialize the fxml.
     */
    @FXML
    public void initialize() {
        modify_content.setText(cont);
    }

    /**
     * Back to posts page.
     *
     * @throws Exception the exception
     */
    public void backToPosts() throws Exception {
        ApplicationUI.postView(ApplicationUI.getStage());
    }

    /**
     * Modify post.
     *
     * @throws Exception the exception
     */
    public void modifyPost() throws Exception {
        PF.modifyPost(currentId, modify_content.getText());
        backToPosts();
    }

    /**
     * Sets the fxml to display the content.
     *
     * @param con the con
     * @param id  the id
     */
    public static void setup(String con, Object id) {
        currentId = id;
        cont = con;
    }

    @Override
    public void printResults(Object arg) {

    }
}
