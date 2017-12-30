package Application;

import Entities.Furniture.FurnitureItem;
import Entities.Furniture.FurnitureItemPurchase;
import Entities.Space.Space;

import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

public class Main extends javafx.application.Application{

    public static final Morphia morphia = new Morphia();
    public static final Datastore datastore = morphia.createDatastore(DatabaseConfig.CLIENT,"Cw03Database");

    public static Group root;

    public void start(Stage stage) throws Exception {

        morphia.map(FurnitureItem.class, Space.class, FurnitureItemPurchase.class);

        root = new Group();

        stage.setTitle("UoG Facilities Management Information System");
        stage.setScene(new Scene(root));
        stage.setResizable(false);

        changeScreen(FXMLLoader.load(getClass().getResource("/Login/LoginForm.fxml")));

        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }

    public static void changeScreen(Pane content){
        root.getChildren().clear();
        root.getChildren().add(content);
        root.getScene().getWindow().setWidth(content.getPrefWidth());
        root.getScene().getWindow().setHeight(content.getPrefHeight());
    }
}
