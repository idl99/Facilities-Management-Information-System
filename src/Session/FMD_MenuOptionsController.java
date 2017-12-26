package Session;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FMD_MenuOptionsController {
    @FXML
    void recordFloorDetails(ActionEvent event) {
            //screen.changeScreen(FXMLLoader.load(getClass().getResource("../Forms/FloorForm.fxml")),480,520);
        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/Forms/FloorForm.fxml"))));
            stage.setResizable(false);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {

    }

}

