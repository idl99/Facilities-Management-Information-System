package Forms;

import Entities.Building;

import Entities.BuildingFloor;
import Entities.Space;
import Entities.SpaceType;
import com.mongodb.client.DistinctIterable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class SpaceFormController implements Initializable {

    @FXML private ChoiceBox<String> choiceBoxBuildingNum;

    @FXML private ChoiceBox<String> choiceBoxFloorNum;

    @FXML
    private TextField txtFieldSpaceId;

    @FXML
    private ChoiceBox<SpaceType> choiceBoxSpaceType;

    @FXML
    private TextField textFieldEmployeeId;

    @FXML
    private TextField txtFieldSpaceName;

    @FXML
    private TextField txtFieldDepartment;


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
    void submitForm(ActionEvent event){

        Space space = new Space(new BuildingFloor(choiceBoxBuildingNum.getValue(),choiceBoxFloorNum.getValue()),
                txtFieldSpaceId.getText(),
                txtFieldSpaceName.getText(),
                choiceBoxSpaceType.getValue(),
                textFieldEmployeeId.getText(),
                txtFieldDepartment.getText());

        String response = space.writeToDatabase();

        // Clearing text fields
        for(TextField textField:new TextField[]{txtFieldSpaceId,textFieldEmployeeId,
                txtFieldDepartment,txtFieldSpaceName}){
            textField.clear();
        }

        // Clearing choice box fields
        for(ChoiceBox<?> choiceBox: new ChoiceBox[]{choiceBoxBuildingNum,choiceBoxFloorNum,
                choiceBoxSpaceType}){
            choiceBox.valueProperty().setValue(null);
        }

        // Display message dialog
        if(showMessageDialog(response).get() == ButtonType.CANCEL ){
            ((Stage)(((Node)(event.getSource())).getScene().getWindow())).close();
        }


    }

    Optional<ButtonType> showMessageDialog(String message){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("FMIS - Floor Form Submission");
        alert.setHeaderText("Successfully recorded details");
        alert.setContentText(message+" Do you wish to add any more records? " +
                "If YES, Click on OK, else Click on Cancel to Exit");
        alert.setGraphic(new ImageView(new Image("/Graphics/Sucess_Icon.png")));
        Optional<ButtonType> result = alert.showAndWait();

        return result;
    }

}
