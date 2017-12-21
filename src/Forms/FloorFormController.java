package Forms;

import Entities.BuildingFloor;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class FloorFormController {

    @FXML private TextField txtFieldBuildingNum;

    @FXML private TextField txtFieldBuildingName;

    @FXML private TextField txtFieldFloorNum;

    @FXML private TextField txtFieldFloorGFA;

    @FXML private TextField txtFieldFloorUFA;

    @FXML private Label lblSuccessMsg;

    @FXML private ImageView iconSuccess;

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

        String response = record.writeRecordToDatabase();
        iconSuccess.setVisible(true);
        lblSuccessMsg.setText(response+". You may continue to add details of a new building floor above," +
                "or close this window");

        txtFieldBuildingNum.clear();
        txtFieldBuildingName.clear();
        txtFieldFloorNum.clear();
        txtFieldFloorGFA.clear();
        txtFieldFloorUFA.clear();
    }

    @FXML void goBack(){
    }
}
