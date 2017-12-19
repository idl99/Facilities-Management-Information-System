package Application;

import javafx.scene.Group;
import javafx.scene.Node;

public class ScreenManager {

    public static Group root;

    public ScreenManager(Group root) {
        // Getting the root node of the scene graph
        this.root = root;
    }

    public static void changeScreen(Node base, int screen_width, int screen_height){
        changeScreen(base);
        root.getScene().getWindow().setWidth(screen_width);
        root.getScene().getWindow().setHeight(screen_height);
    }

    public static void changeScreen(Node base){
        root.getChildren().clear();
        root.getChildren().add(base);
    }

}
