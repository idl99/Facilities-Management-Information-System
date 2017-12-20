package Forms;

import Entities.BuildingFloor;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class FloorFormController {

    @FXML private TextField txtFieldBuildingNum;

    @FXML private TextField txtFieldBuildingName;

    @FXML private TextField txtFieldFloorNum;

    @FXML private TextField txtFieldFloorGFA;

    @FXML private TextField txtFieldFloorUFA;


    @FXML
    void initialize() {

    }

    @FXML void submitForm(){
        String buildingNum = txtFieldBuildingNum.getText();
        String buildingName = txtFieldBuildingName.getText();
        String floorNum = txtFieldFloorNum.getText();
        String floorGFA = txtFieldFloorGFA.getText();
        String floorUFA = txtFieldFloorUFA.getText();
        BuildingFloor record = new BuildingFloor(buildingNum,buildingName,floorNum,floorGFA,floorUFA);
        record.writeRecordToDatabase();
    }
}
