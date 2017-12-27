package Forms;

import Entities.Building;

import Entities.BuildingFloor;
import Entities.Space;
import Entities.SpaceType;
import com.mongodb.client.DistinctIterable;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SpaceFormController implements Initializable {

    @FXML private ChoiceBox<String> choiceBoxBuildingNum;

    @FXML private ChoiceBox<String> choiceBoxFloorNum;

    @FXML
    private TextField txtFieldSpaceId;

    @FXML
    private TextField txtFieldOptionalSuffix;

    @FXML
    private ChoiceBox<SpaceType> choiceBoxSpaceType;

    @FXML
    private TextField textFieldEmployeeId;

    @FXML
    private TextField txtFieldSpaceName;

    @FXML
    private TextField txtFieldDepartment;

    @FXML
    private Label lblSuccessMsg;

    @FXML
    private ImageView iconSuccess;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Listener for Building number choice box
        choiceBoxBuildingNum.valueProperty().addListener(((observable, oldValue, newValue) ->
            getFloorNumbers()
        ));

        // Listener for Floor number choice box
        choiceBoxFloorNum.valueProperty().addListener(((observable, oldValue, newValue) -> {
            txtFieldSpaceId.setText(choiceBoxFloorNum.getValue());
        }));

        getBuildingNumbers();
        bindSpaceTypes();

    }

    @FXML
    void getBuildingNumbers(){
        DistinctIterable<String> buildingNumbers =  Building.getDistinctBuildingNumber();
        List<String> list = new ArrayList<>();
        buildingNumbers.iterator().forEachRemaining(list::add);
        for(String number: list){
            choiceBoxBuildingNum.getItems().add(number);
        }
    }

    @FXML
    void getFloorNumbers(){
        choiceBoxFloorNum.getItems().clear();
        DistinctIterable<String> floorNumbers = BuildingFloor.getDistinctBuildingFloor(choiceBoxBuildingNum.getValue());
        List<String> list = new ArrayList<>();
        floorNumbers.iterator().forEachRemaining(list::add);
        for(String number: list){
            choiceBoxFloorNum.getItems().add(number);
        }
    }

    @FXML
    void bindSpaceTypes(){
        choiceBoxSpaceType.getItems().clear();
        for(SpaceType enumVal: SpaceType.values()){
            choiceBoxSpaceType.getItems().add(enumVal);
        }
    }

    @FXML
    void submitForm(){
        Space space = new Space(new BuildingFloor(choiceBoxBuildingNum.getValue(),choiceBoxFloorNum.getValue()),
                (txtFieldSpaceId.getText()+txtFieldOptionalSuffix.getText()),
                txtFieldSpaceName.getText(),
                choiceBoxSpaceType.getValue(),
                textFieldEmployeeId.getText(),
                txtFieldDepartment.getText());
        String response = space.writeToDatabase();
        iconSuccess.setVisible(true);
        lblSuccessMsg.setText(response);
    }

}
