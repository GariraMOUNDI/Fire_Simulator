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

import java.util.List;


public class StoreController implements LoginInterface {
    private StoreFacade SF = StoreFacade.getInstance(this);



//    @FXML
//    Button right, left, item_right, item_left;
    @FXML
    Label item_name, item_price, item_scope, item_damage, item_regen, character_name, character_price, character_scope;
    @FXML
    ImageView item_image, character_image;
    @FXML
    BorderPane items, characters;
    @FXML
    Button item_purchase, character_purchase;
    @FXML
    public void initialize() {
        initCharacters(SF.getNextCharacter());
        initItems(SF.getNextItem());
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
                character_purchase.setDisable(true);
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
                item_purchase.setDisable(true);
            }
        });

//        List<Item> items = SF.getStoreItems();
//        GridPane pane = new GridPane();
//        pane.setVgap(10);
//
//        int i = 0;
//        for (Item it : items) {
//            pane.add(createItem(it),0,i);
//            i++;
//        }
//        pane.setStyle("-fx-background-color: red");
////        store_items.setAlignment(Pos.CENTER);
////        store_items.setContent(pane);
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



    private GridPane createItem(Item i) {
        GridPane pane = new GridPane();
//        ColumnConstraints c = new ColumnConstraints(store_items.getMaxWidth());
//        c.setHalignment(HPos.CENTER);
//        pane.getColumnConstraints().add(c);
        pane.setHgap(10);
        pane.setStyle("-fx-background-color: #b0b0b0;-fx-padding: 10;-fx-background-radius: 20px");
        pane.add(new ImageView(new Image(i.getImageURL(),100,100,false,false)),0,0);
        Label name = new Label(i.getName());
        name.setPadding(new Insets(5,10,5,10));
        name.setStyle("-fx-font-size: 18;-fx-font: Arial Rounded MT Bold");
        pane.add(name,0,1);
        pane.add(new Label("Scope : "+i.getScope()),1,0);
        pane.add(new Label("Dammage : "+i.getDamage()),1,1);
        pane.add(new Label("Price : "+i.getPrice()),2,0);
        pane.add(new Label("Regeneration : "+i.getRegeneration()),2,1);

        Button button = new Button();
        button.setText("Purchase");
        button.setStyle("-fx-background-color: #a1caf1; -fx-background-radius: 30;-fx-cursor: hand");
        button.setDisable(SF.userOwnsItem(i));
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SF.handleItemPurchase(i);
                initialize();
            }
        });

        pane.add(button, 3, 0);

        return pane;
    }
}
