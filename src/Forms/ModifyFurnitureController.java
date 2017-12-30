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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void searchFurnitureItem(ActionEvent event) {
        FurnitureItem item = FurnitureItem.getFurnitureByBarcode(txtFieldBarcode.getText());
        txtFieldBarcode.setText(item.getBarcode());
        txtFieldKeyNum.setText(item.getKeyNumber());

        choiceBoxStatus.getItems().add(item.getStatus());
        choiceBoxStatus.setValue(item.getStatus());

        choiceBoxBuilding.getItems().add(item.getLocation().getBuildingNumber());
        choiceBoxBuilding.setValue(item.getLocation().getBuildingNumber());

        choiceBoxSpace.getItems().add(item.getLocation().getSpaceId());
        choiceBoxSpace.setValue(item.getLocation().getSpaceId());
    }

    @FXML
    void submitForm(ActionEvent event) {

    }

}
