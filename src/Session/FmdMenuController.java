package Session;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FmdMenuController {
    @FXML
    void recordFloorDetails(ActionEvent event) {
        newFormWindow("/Forms/FloorForm.fxml");
    }

    @FXML
    void recordRoomSpace(ActionEvent event){
        newFormWindow("/Forms/SpaceForm.fxml");
    }

    @FXML
    void recordFurnitureItem(ActionEvent event){
        newFormWindow("/Forms/FurnitureForm.fxml");
    }

    @FXML
    void modifyFurnitureItem(ActionEvent event){
        newFormWindow("/Forms/ModifyFurnitureForm.fxml");
    }

    @FXML
    void initialize() {

    }

    void newFormWindow(String fxmlResource){
        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(fxmlResource))));
            stage.setResizable(false);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

