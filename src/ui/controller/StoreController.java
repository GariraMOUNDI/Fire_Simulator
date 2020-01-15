package ui.controller;

import businessLogic.StoreFacade;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import persistence.data.Character;
import persistence.data.Item;
import ui.interfaces.LoginInterface;
import ui.model.ApplicationUI;
/**
 * The type Store controller.
 */
public class StoreController implements LoginInterface {
    private StoreFacade SF = StoreFacade.getInstance(this);


    /**
     * The Item name.
     */
    @FXML
    private Label item_name, /**
     * The Item price.
     */
    item_price, /**
     * The Item scope.
     */
    item_scope, /**
     * The Item damage.
     */
    item_damage, /**
     * The Item regeneration time.
     */
    item_regen, /**
     * The Character name.
     */
    character_name, /**
     * The Character price.
     */
    character_price, /**
     * The Character scope.
     */
    character_scope, /**
     * The User gold.
     */
    user_gold, /**
     * The User diamond.
     */
    user_diamond;
    /**
     * The Item image view.
     */
    @FXML
    private ImageView item_image, /**
     * The Character image.
     */
    character_image;
    /**
     * The Items border pane.
     */
    @FXML
    private BorderPane items, /**
     * The Characters border pane.
     */
    characters;
    /**
     * The purchase Item button.
     */
    @FXML
    private Button item_purchase, /**
     * The purchase Character button.
     */
    character_purchase;

    /**
     * The initialize method. This method sets up the characters, items, and user's gold and diamonds
     */
    @FXML
    public void initialize() {
        initCharacters(SF.getCharacter());
        initItems(SF.getItem());
        initCurrency(SF.getUser().getDiamonds(), SF.getUser().getGold());
    }

    @Override
    public void printResults(Object arg) {

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
     * This methods sets each attribute of the character passed as a parameter to the fxml view
     * @param c the character to visualize
     */
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

    /**
     * This methods sets each attribute of the item passed as a parameter to the fxml view
     * @param i the item to visualize
     */
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

    /**
     * This methods sets the user's diamonds and gold passed as parameters to the fxml view
     * @param diamond the user's diamonds
     * @param gold the user's gold
     */
    private void initCurrency(int diamond, int gold) {
        user_diamond.setText(""+diamond);
        user_gold.setText(""+gold);
    }

    /**
     * Go to the next item.
     */
    public void nextItem() {
        initItems(SF.getNextItem());
    }

    /**
     * Go to the previous item.
     */
    public void prevItem() {
        initItems(SF.getPrevItem());
    }

    /**
     * Go to the next character.
     */
    public void nextCharacter() {
        initCharacters(SF.getNextCharacter());
    }

    /**
     * Go to the previous character.
     */
    public void prevCharacter() {
        initCharacters(SF.getPrevCharacter());
    }
}
