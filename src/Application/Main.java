package Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends javafx.application.Application{

    public static Group root;

    public void start(Stage stage) throws Exception {
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
