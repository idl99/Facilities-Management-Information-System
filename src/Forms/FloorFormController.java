package Forms;

import Application.DatabaseConnectivity;
import Entities.BuildingFloor;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class FloorFormController implements DatabaseConnectivity {

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

        BuildingFloor record = new BuildingFloor(txtFieldBuildingNum.getText(),
                txtFieldBuildingName.getText(),
                txtFieldFloorNum.getText(),
                txtFieldFloorGFA.getText(),
                txtFieldFloorUFA.getText());

        String response = record.writeToDatabase();
        iconSuccess.setVisible(true);
        lblSuccessMsg.setText(response+". You may continue to add details of a new building floor above," +
                "or close this window");
        for(TextField textfield: new TextField[]{txtFieldBuildingNum,txtFieldBuildingName,
                                                txtFieldFloorNum,txtFieldFloorGFA,txtFieldFloorUFA}){
            textfield.clear();
        }
    }
}
