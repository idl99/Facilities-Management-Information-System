package Forms;

import Entities.Furniture.FurnitureItem;
import Entities.Furniture.FurnitureItemStatus;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ModifyFurnitureController implements Initializable {

    @FXML
    private AnchorPane Content;

    @FXML
    private TextField txtFieldSearchBarcode;

    @FXML
    private TextField txtFieldBarcode;

    @FXML
    private TextField txtFieldKeyNum;

    @FXML
    private ChoiceBox<FurnitureItemStatus> choiceBoxStatus;

    @FXML
    private TitledPane paneLocationDetails;

    @FXML
    private ChoiceBox<String> choiceBoxBuilding;

    @FXML
    private ChoiceBox<String> choiceBoxSpace;

    private FurnitureItem itemToModify;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    void searchFurnitureItem(ActionEvent event) {
        itemToModify = FurnitureItem.getFurnitureByBarcode(txtFieldBarcode.getText());

        txtFieldBarcode.setText(itemToModify.getBarcode());

        txtFieldKeyNum.setText(itemToModify.getKeyNumber());

        FormBindings.bindFurnitureItemStatus(choiceBoxStatus);
        choiceBoxStatus.setValue(itemToModify.getStatus());

        FormBindings.bindBuildingNumbers(choiceBoxBuilding);
        choiceBoxBuilding.setValue(itemToModify.getLocation().getBuildingNumber());

        FormBindings.bindSpaces(choiceBoxSpace,choiceBoxBuilding.getValue());
        choiceBoxSpace.setValue(itemToModify.getLocation().getSpaceId());

        addListeners();
    }

    public void addListeners(){

        txtFieldBarcode.textProperty().addListener(((observable, oldValue, newValue) -> {
            itemToModify.setBarcode(txtFieldBarcode.getText());
        }));

        txtFieldKeyNum.textProperty().addListener(((observable, oldValue, newValue) -> {
            itemToModify.setKeyNumber(txtFieldKeyNum.getText());
        }));

        choiceBoxStatus.valueProperty().addListener(((observable, oldValue, newValue)-> {
            itemToModify.setStatus(choiceBoxStatus.getValue());
        }));

        choiceBoxBuilding.valueProperty().addListener(((observable, oldValue, newValue) -> {
            itemToModify.getLocation().setBuildingNumber(choiceBoxBuilding.getValue());
            FormBindings.bindSpaces(choiceBoxSpace,choiceBoxBuilding.getValue());
        }));

        choiceBoxSpace.valueProperty().addListener(((observable, oldValue, newValue) -> {
            itemToModify.getLocation().setSpaceId(choiceBoxSpace.getValue());
        }));

    }

    @FXML
    void submitForm(ActionEvent event) {
        itemToModify.updateInDatabase();
    }

}
