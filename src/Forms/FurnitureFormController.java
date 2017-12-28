package Forms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;

public class FurnitureFormController {

    @FXML
    private Accordion accordionDetails;

    @FXML
    private TitledPane paneItemDetails;

    @FXML
    private TextField txtFieldBarcode;

    @FXML
    private TextField txtFieldKeyNum;

    @FXML
    private ChoiceBox<?> choiceBoxItem;

    @FXML
    private ChoiceBox<?> choiceBoxMaterial;

    @FXML
    private ChoiceBox<?> choiceBoxStatus;

    @FXML
    private TitledPane paneLocationDetails;

    @FXML
    private ChoiceBox<String> choiceBoxBuilding;

    @FXML
    private ChoiceBox<String> choiceBoxSpace;

    @FXML
    private TitledPane panePurchaseDetails;

    @FXML
    private TextField txtFieldSupplier;

    @FXML
    private TextField txtFieldDate;

    @FXML
    private TextField txtFieldCost;


    @FXML void submitForm(ActionEvent event) {

    }

    @FXML
    void initialize() {
        accordionDetails.setExpandedPane(paneItemDetails);
        choiceBoxBuilding.valueProperty().addListener(((observable, oldValue, newValue) -> {
            FormBindings.bindSpaces(choiceBoxSpace,choiceBoxBuilding.getValue());
        }));
        FormBindings.bindBuildingNumbers(choiceBoxBuilding);
    }

}
