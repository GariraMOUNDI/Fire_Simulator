package resources.styles;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import ui.controller.FriendsController;

public class FriendsButtons extends Button {

    public FriendsButtons(FriendsController controller, String image, String username){
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(40);
        imageView.setFitWidth(40);
        setGraphic(imageView);
        setCursor(Cursor.HAND);

        setOnAction((e) -> {
            if (image.contains("remove")){
                controller.getSession().removeFriendToUserLoggedIn(username);
                controller.refreshLists();
            }

            if (image.contains("add")){
                controller.getSession().addFriendToUserLoggedIn(username);
                controller.refreshLists();
            }

            if (image.contains("fight"))
                System.out.println("FIGTH !!!");
        });
    }
}
