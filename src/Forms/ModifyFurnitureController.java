package Forms;

import Entities.Furniture.FurnitureItem;
import Entities.Furniture.FurnitureItemStatus;
import Entities.Space.Space;
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

        itemToModify = FurnitureItem.getFurnitureByBarcode(txtFieldSearchBarcode.getText());

        txtFieldBarcode.setText(itemToModify.getBarcode());

        txtFieldKeyNum.setText(itemToModify.getKeyNumber());

        FormBindings.bindFurnitureItemStatus(choiceBoxStatus);
        choiceBoxStatus.setValue(itemToModify.getStatus());

        FormBindings.bindBuildingNumbers(choiceBoxBuilding);
        choiceBoxBuilding.setValue(itemToModify.getLocation().getBuildingNumber());

        FormBindings.bindSpaces(choiceBoxSpace,choiceBoxBuilding.getValue());
        choiceBoxSpace.setValue(itemToModify.getLocation().getSpaceId());
    }

    @FXML
    void submitForm(ActionEvent event) {
        itemToModify.setKeyNumber(txtFieldKeyNum.getText());
        itemToModify.setStatus(choiceBoxStatus.getValue());
        itemToModify.setLocation(new Space(choiceBoxBuilding.getValue(),choiceBoxSpace.getValue()));
        itemToModify.updateInDatabase();
    }

}
