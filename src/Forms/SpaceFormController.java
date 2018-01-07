package Forms;

import Application.DataEntryForm;
import Entities.Space.Space;
import Entities.Space.SpaceType;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
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
            FormBindings.bindFloorNumbers(choiceBoxFloorNum, choiceBoxBuildingNum.getValue())
        ));

        // Listener for Floor number choice box
        choiceBoxFloorNum.valueProperty().addListener(((observable, oldValue, newValue) -> {
            txtFieldSpaceId.setText(choiceBoxFloorNum.getValue());
        }));

        FormBindings.bindBuildingNumbers(choiceBoxBuildingNum);

        FormBindings.bindSpaceType(choiceBoxSpaceType);

    }

    @FXML
    void submitForm(ActionEvent event){

        Space space = new Space(choiceBoxBuildingNum.getValue(),
                choiceBoxFloorNum.getValue(),
                txtFieldSpaceId.getText(),
                txtFieldSpaceName.getText(),
                choiceBoxSpaceType.getValue(),
                textFieldEmployeeId.getText(),
                txtFieldDepartment.getText());
        space.writeToDatabase();

        DataEntryForm.closeFormOnSubmit(event);
    }
}
