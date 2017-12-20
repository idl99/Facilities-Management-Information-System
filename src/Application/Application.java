package Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Application extends javafx.application.Application{

    public static ScreenManager screen;

    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("LoginForm.fxml"));
        Group root = new Group(new AnchorPane());
        screen = new ScreenManager(root);
        screen.changeScreen(FXMLLoader.load(getClass().getResource("/Login/LoginForm.fxml")));

        primaryStage.setTitle("UoG Facilities Management Information System");
        primaryStage.setScene(new Scene(root,800,400));

        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public static void main(String[] args){
        launch(args);
    }

}
