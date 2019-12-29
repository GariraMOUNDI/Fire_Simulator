package ui.controller;

import businessLogic.SessionFacade;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import persistence.data.User;
import ui.interfaces.LoginInterface;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class ProfileController implements LoginInterface {

    private SessionFacade session = SessionFacade.getInstance(this);

    @FXML
    TabPane profileTabPane;
    @FXML
    Label usernameLabel,emailLabel,hwLabel;
    @FXML
    TextField usernameField,emailField,formerPasswordField,newPasswordField,confirmPasswordField,hwField;
    @FXML
    Button saveButton;

    @Override
    public void printResults(Object arg) {

    }

    public void init(Stage stage){
        refreshInfo();
        profileTabPane.setTabMinWidth(profileTabPane.getWidth() / 3.2);
    }

    private void refreshInfo(){
        usernameLabel.setText(session.getUserLoggedIn().getUsername());
        emailLabel.setText(session.getUserLoggedIn().getEmail());
        String help = session.getUserLoggedIn().getHelpWord().trim().equals("")
                ? "You didn't save a help word" : session.getUserLoggedIn().getHelpWord();
        hwLabel.setText(help);
        usernameField.setText("");
        emailField.setText("");
        formerPasswordField.setText("");
        newPasswordField.setText("");
        confirmPasswordField.setText("");
        hwField.setText("");
    }

    public void saveChanges(){
        checkFields();
    }

    private void checkFields(){
        if (usernameField.getText().trim().length() == 0 && emailField.getText().trim().length() == 0
                && confirmPasswordField.getText().trim().length() == 0 && formerPasswordField.getText().trim().length() == 0
                && newPasswordField.getText().trim().length() == 0 && hwField.getText().trim().length() == 0)
            showPopupAlert(Alert.AlertType.INFORMATION,"Please check the entered information !!!","You must enter any information.");
        else{
            User user = session.getUserLoggedIn();
            String username = usernameField.getText(),
                    email = emailField.getText(),
                    former = formerPasswordField.getText(),
                    newPassword = newPasswordField.getText(),
                    confirmPassword = confirmPasswordField.getText(),
                    help = hwField.getText();
            if (!username.trim().equals(""))
                user.setUsername(username);
            if (!email.trim().equals(""))
                user.setEmail(email);
            if (!help.trim().equals(""))
                user.setHelpWord(help);
            boolean check = true;
            if (!former.trim().equals("") || !newPassword.trim().equals("") || !confirmPassword.trim().equals(""))
                if (former.equals(user.getPassword())) {
                    if (newPassword.equals(confirmPassword))
                        user.setPassword(newPassword);
                    else
                        check = showPopupAlert(Alert.AlertType.INFORMATION, "Please check the entered information !!!", "The confirmation password doesn't match.");
                }else
                        check = showPopupAlert(Alert.AlertType.INFORMATION,"Please check the entered information !!!","The former password doesn't match !!!");

            if (check)
                if (showPopupAlert(Alert.AlertType.CONFIRMATION, "Do you want to update these informations ?", "")){
                    session.setUserLoggedIn(user);
                    session.updateUser();
                    refreshInfo();
                }
        }

    }

    private boolean showPopupAlert(Alert.AlertType type, String header, String arg){
        Alert alert;
        boolean choice = false;
        if (type == Alert.AlertType.CONFIRMATION){
            alert = new Alert(type, arg, ButtonType.YES,ButtonType.NO);
            alert.setHeaderText(header);
            alert.setTitle("Updating ... ");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.orElse(ButtonType.NO) == ButtonType.YES)
                choice = true;
            else
                choice = false;
        }else{
            alert = new Alert(type, arg);
            alert.setHeaderText(header);
            alert.setTitle("Updating ... ");
            alert.show();
        }
        //a.setGraphic(new ImageView("resources/icons/add.png"));
        return choice;
    }

}
