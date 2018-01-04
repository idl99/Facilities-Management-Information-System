package Session;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StaffMenuController implements Initializable{

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void makeRelocationRequest(){
        newFormWindow("/Forms/MoveRequestForm.fxml");
    }

    @FXML
    void makeMaintenanceRequest(){
        newFormWindow("/Forms/MaintenanceRequestForm.fxml");
    }

    @FXML
    void modifyMoveRequest(){
        newFormWindow("/Forms/ModifyMoveForm.fxml");
    }

    @FXML
    void modifyMaintenanceRequest(){
        newFormWindow("/Forms/ModifyMaintenanceForm.fxml");
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
