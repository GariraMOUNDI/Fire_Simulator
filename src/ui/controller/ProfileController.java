package ui.controller;

import businessLogic.SessionFacade;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import persistence.data.Character;
import persistence.data.Item;
import persistence.data.User;
import resources.styles.ItemButtons;
import resources.styles.ItemLabels;
import ui.interfaces.LoginInterface;
import ui.model.ApplicationUI;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ProfileController implements LoginInterface {

    private SessionFacade session = SessionFacade.getInstance(this);

    @FXML
    TabPane profileTabPane;
    @FXML
    Label usernameLabel, emailLabel, hwLabel, scopeCharacter, xpLabel, goldLabel, diamondsLabel, levelLabel;
    @FXML
    TextField usernameField, emailField, formerPasswordField, newPasswordField, confirmPasswordField, hwField;
    @FXML
    Button saveButton, forwardCharacter, backwardCharacter;
    @FXML
    GridPane itemGridPane;
    @FXML
    ScrollPane itemScrollPane;
    @FXML
    ImageView characterImage;

    @Override
    public void printResults(Object arg) {

    }

    public void init(Stage stage){
        profileTabPane.setTabMinWidth(profileTabPane.getWidth() / 3.2);
        refreshInfo();
        printLevels();
        itemGridPane.setVgap(10);
        deleteRows(itemGridPane);
        showItems();
        showChacracter(0);
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

    public void deleteAccount() throws Exception {
        boolean delete = showPopupAlert(Alert.AlertType.CONFIRMATION, "Do you really want to delete your Account ???", "Once deleted, you will lose all your progress until this day !!! ");
        if (delete){
            session.deleteAccount();
            ApplicationUI.getStage().close();
        }
    }

    public void backToLoginPage() throws Exception {
        ApplicationUI.loginView(ApplicationUI.getStage());
    }

    public void printLevels(){
        xpLabel.setText(session.getUserLoggedIn().getXP()+"");
        goldLabel.setText(session.getUserLoggedIn().getGold()+"");
        diamondsLabel.setText(session.getUserLoggedIn().getDiamonds()+"");
        levelLabel.setText(session.getUserLoggedIn().getLevel()+"");
    }

    private void createItemPane(Item item, int rowIndex){
        GridPane itemPane = new GridPane();
        ItemLabels scope = new ItemLabels("Scope :","black")
                ,name = new ItemLabels("Name : "+item.getName(),"black")
                ,damage = new ItemLabels("Damage :","black")
                ,level = new ItemLabels("Level :","black")
                ,regeneration = new ItemLabels("Regeneration :","black")
                ,damageValue = new ItemLabels(String.valueOf(item.getDamage()),"green")
                ,levelValue = new ItemLabels(String.valueOf(item.getLevel()),"green")
                ,regenerationValue = new ItemLabels(String.valueOf(item.getRegeneration()),"green")
                ,scopeValue = new ItemLabels(String.valueOf(item.getScope()),"green");
        ItemButtons upgrade = new ItemButtons("Upgrade","green");
        ItemButtons drop = new ItemButtons("Drop","red");

        itemPane.setMinHeight(120);
        ColumnConstraints col1 = new ColumnConstraints()
                ,col2 = new ColumnConstraints()
                ,col3 = new ColumnConstraints();
        col1.setPercentWidth(40);
        col1.setHalignment(HPos.CENTER);
        itemPane.getColumnConstraints().add(col1);  // Add the first column
        col2.setPercentWidth(30);
        col2.setHalignment(HPos.RIGHT);
        itemPane.getColumnConstraints().add(col2);   // Add the second column
        col3.setHalignment(HPos.LEFT);
        itemPane.getColumnConstraints().add(col3);   // Add the third column

        ImageView itemImage = new ImageView(item.getImageURL());
        itemImage.setFitWidth(90);
        //itemImage.setFitHeight(100);
        itemImage.setPreserveRatio(true);
        itemPane.add(itemImage,0,0,1,4);

        for (int i=0 ; i<5; i++) {
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(20);
           itemPane.getRowConstraints().add(row);
        }


        itemPane.add(scope,1,0);
        itemPane.add(damage,1,1);
        itemPane.add(level,1,2);
        itemPane.add(regeneration,1,3);
        itemPane.add(scopeValue,2,0);
        itemPane.add(damageValue,2,1);
        itemPane.add(levelValue,2,2);
        itemPane.add(regenerationValue,2,3);
        itemPane.add(name,0,4);

        FlowPane buttonPane = new FlowPane();
        buttonPane.getChildren().add(upgrade);
        itemPane.add(buttonPane,1,4);

        buttonPane = new FlowPane();
        buttonPane.getChildren().add(drop);
        buttonPane.setStyle("-fx-alignment: center");
        itemPane.add(buttonPane,2,4);

        itemPane.setStyle("-fx-background-color: #a1caf1; -fx-background-radius: 15");

        itemGridPane.add(itemPane,0,rowIndex);
    }

    private void deleteRows(GridPane grid){
        Set<Node> deleteNodes = new HashSet<>();
        for (Node child : grid.getChildren())
            deleteNodes.add(child);
        grid.getChildren().removeAll(deleteNodes);
        grid.getRowConstraints().clear();
    }

    private void showItems(){
        int i = 0;
        List<Item> items = session.getUserLoggedInItems();
        if (items.size() != 0)
            for(Item item : items){
                createItemPane(item,i);
                i++;
            }
        else
            itemGridPane.add(new ItemLabels("You don't have any items.","red"),0,0,3,4);
    }

    private void showChacracter(int characterIndex) {
        Character character = session.getCharacterAt(characterIndex);
        if (character != null) {
            characterImage.setImage(new Image(character.getImageURL()));
            scopeCharacter.setText(String.valueOf(character.getScope()));
        }
        else
            /*   */ ;
    }

    public void previousCharacter(){
        Character character = session.previousCharacter();
        characterImage.setImage(new Image(character.getImageURL()));
        scopeCharacter.setText(String.valueOf(character.getScope()));
    }

    public void nextCharacter(){
        Character character = session.nextCharacter();
        characterImage.setImage(new Image(character.getImageURL()));
        scopeCharacter.setText(String.valueOf(character.getScope()));
    }

    public void backToMainPage() throws Exception {
        ApplicationUI.mainPageView(ApplicationUI.getStage());
    }
}