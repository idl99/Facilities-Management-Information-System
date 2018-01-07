package Session;

import Application.DataEntryForm;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class FmdMenuController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void recordFloorDetails(ActionEvent event) {
        new DataEntryForm("/Forms/FloorForm.fxml");
    }

    @FXML
    void recordRoomSpace(ActionEvent event){
        new DataEntryForm("/Forms/SpaceForm.fxml");
    }

    @FXML
    void recordFurnitureItem(ActionEvent event){
        new DataEntryForm("/Forms/FurnitureForm.fxml");
    }

    @FXML
    void modifyFurnitureItem(ActionEvent event){
        new DataEntryForm("/Forms/ModifyFurnitureForm.fxml");
    }

    @FXML
    void scheduleMoveRequest(ActionEvent event){
        new DataEntryForm("/Forms/ScheduleMoveForm.fxml");
    }

    @FXML
    void actionMaintenanceCalls(ActionEvent event){
        new DataEntryForm("/Forms/ActionMaintenanceForm.fxml");
    }

    @FXML
    void viewFloorPlans(ActionEvent event){
        new DataEntryForm("/Forms/ViewFloorPlan.fxml");
    }

}

