package Session;

import Application.DataEntryForm;
import Entities.BuildingFloor;
import Entities.FmoReport;
import Forms.MessageDialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StaffMenuController implements Initializable{

    @FXML
    private Button btnGenerateFmoReport;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (SessionController.sessionUser.getRole().equals("Superior"))
                btnGenerateFmoReport.setVisible(true);
    }

    @FXML
    void makeRelocationRequest(){
        new DataEntryForm("/Forms/MoveRequestForm.fxml");
    }

    @FXML
    void makeMaintenanceRequest(){
        new DataEntryForm("/Forms/MaintenanceRequestForm.fxml");
    }

    @FXML
    void modifyMoveRequest(){
        new DataEntryForm("/Forms/ModifyMoveForm.fxml");
    }

    @FXML
    void modifyMaintenanceRequest(){
        new DataEntryForm("/Forms/ModifyMaintenanceForm.fxml");
    }

    @FXML
    void viewFloorPlans(ActionEvent event){
        new DataEntryForm("/Forms/ViewFloorPlan.fxml");
    }

    @FXML
    void generateFmoReport(){

        List<String> buildingFloors = new ArrayList<>();

        for(BuildingFloor floor : BuildingFloor.getAll()){
            buildingFloors.add(floor.getBuildingNumber()+"-"+floor.getFloorNumber());
        }

        String response = new MessageDialog.MessageDialogBuilder().choiceDialogType(buildingFloors
        ).withTitle("GENERATE FMO REPORT").withHeader("Generate FMO Report").
                withContentText("Select the building floor (Building Number - Floor Number)").build()._show();

        BuildingFloor selected = BuildingFloor.getById(response);

        FmoReport report = new FmoReport(selected);

        new MessageDialog.MessageDialogBuilder()
                .withTitle("GENERATED FMO REPORT").withHeader("Successfully generated FMO Report")
                .withContentText("Successfully generated FMO report for Building Number: "
                        +selected.getBuildingNumber()+" Floor Number: "
                        +selected.getFloorNumber()+". Report saved to Application folder under filename: "
                        +report.getFileName())
                .withGraphic(new Image("/Graphics/Icons/Sucess_Icon.png"))
                .build().show();

    }
}
