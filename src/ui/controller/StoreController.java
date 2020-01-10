package ui.controller;

import businessLogic.SessionFacade;
import businessLogic.StoreFacade;
import com.sun.glass.ui.Accessible;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import persistence.data.Character;
import persistence.data.Item;
import ui.interfaces.LoginInterface;
import ui.model.ApplicationUI;

import javax.swing.*;
import java.util.List;


public class StoreController implements LoginInterface {
    private StoreFacade SF = StoreFacade.getInstance(this);



//    @FXML
//    Button right, left, item_right, item_left;
    @FXML
    Label item_name, item_price, item_scope, item_damage, item_regen, character_name, character_price, character_scope, user_gold, user_diamond;
    @FXML
    ImageView item_image, character_image;
    @FXML
    BorderPane items, characters;
    @FXML
    Button item_purchase, character_purchase;
    @FXML
    public void initialize() {
        initCharacters(SF.getCharacter());
        initItems(SF.getItem());
        initCurrency(SF.getUser().getDiamonds(), SF.getUser().getGold());
    }

    @Override
    public void printResults(Object arg) {

    }

    public void backToMainPage() throws Exception {
        ApplicationUI.mainPageView(ApplicationUI.getStage());
    }

    private void initCharacters(Character c) {
        character_name.setText(c.getName());
        character_image.setImage(new Image(c.getImageURL()));
        character_price.setText(""+c.getPrice());
        character_scope.setText("Scope : "+c.getScope());
        character_purchase.setDisable(SF.userOwnsCharacter(c));
        character_purchase.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SF.handleCharacterPurchase(c);
                initialize();
            }
        });
    }

    private void initItems(Item i) {
        item_name.setText(i.getName());
        item_image.setImage(new Image(i.getImageURL()));
        item_price.setText(""+i.getPrice());
        item_scope.setText("Scope : "+i.getScope());
        item_damage.setText("Damage : "+i.getDamage());
        item_regen.setText("Regeneration time : "+i.getRegeneration());
        item_purchase.setDisable(SF.userOwnsItem(i));
        item_purchase.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SF.handleItemPurchase(i);
                initialize();
            }
        });
    }

    private void initCurrency(int diamond, int gold) {
        user_diamond.setText(""+diamond);
        user_gold.setText(""+gold);
    }

    public void nextItem() {
        initItems(SF.getNextItem());
    }

    public void prevItem() {
        initItems(SF.getPrevItem());
    }

    public void nextCharacter() {
        initCharacters(SF.getNextCharacter());
    }

    public void prevCharacter() {
        initCharacters(SF.getPrevCharacter());
    }
}
