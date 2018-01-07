package Application;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DataEntryForm {

    private Stage stage;

    public DataEntryForm(String fxmlResource){
        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(fxmlResource))));
            stage.setResizable(false);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void closeFormOnSubmit(ActionEvent event){
        Stage stage = (Stage)((Node)(event.getSource())).getScene().getWindow();
        stage.close();
    }
}
