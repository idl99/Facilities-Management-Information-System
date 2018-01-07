package Forms;

import Application.DataEntryForm;
import Entities.BuildingFloor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class FloorFormController implements Initializable{

    @FXML private TextField txtFieldBuildingNum;

    @FXML private TextField txtFieldBuildingName;

    @FXML private TextField txtFieldFloorNum;

    @FXML private TextField txtFieldFloorGFA;

    @FXML private TextField txtFieldFloorUFA;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML void submitForm(ActionEvent event){

        BuildingFloor record = new BuildingFloor(txtFieldBuildingNum.getText(),
                txtFieldBuildingName.getText(),
                txtFieldFloorNum.getText(),
                txtFieldFloorGFA.getText(),
                txtFieldFloorUFA.getText());

        record.writeToDatabase();

        DataEntryForm.closeFormOnSubmit(event);

    }
}
