package MainScreen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

import static Application.Application.screen;

public class FMD_MenuOptionsController {
    @FXML
    void recordFloorDetails(ActionEvent event) {
        try {
            screen.changeScreen(FXMLLoader.load(getClass().getResource("../Forms/FloorForm.fxml")),480,420);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {

    }

}

