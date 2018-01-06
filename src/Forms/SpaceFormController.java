package Forms;

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
        alert.setGraphic(new ImageView(new Image("/Graphics/Icons/Sucess_Icon.png")));
        Optional<ButtonType> result = alert.showAndWait();

        return result;
    }

}
