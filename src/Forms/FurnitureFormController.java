package Forms;

import Entities.Furniture.*;
import Entities.Space.Space;

import ErrorHandling.InvalidDataInputException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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
    private ChoiceBox<FurnitureItemType> choiceBoxItemType;

    @FXML
    private ChoiceBox<FurnitureItemMaterial> choiceBoxMaterial;

    @FXML
    private ChoiceBox<FurnitureItemStatus> choiceBoxStatus;

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

    @FXML
    void initialize() {
        accordionDetails.setExpandedPane(paneItemDetails);

        FormBindings.bindFurnitureItemType(choiceBoxItemType);
        FormBindings.bindFurnitureItemStatus(choiceBoxStatus);
        FormBindings.bindBuildingNumbers(choiceBoxBuilding);

        choiceBoxItemType.valueProperty().addListener(((observable, oldValue, newValue) -> {
            FormBindings.bindFurnitureItemMaterial(choiceBoxMaterial, choiceBoxItemType.getValue());
        }));

        choiceBoxBuilding.valueProperty().addListener(((observable, oldValue, newValue) -> {
            FormBindings.bindSpaces(choiceBoxSpace,choiceBoxBuilding.getValue());
        }));

        txtFieldCost.setText(String.valueOf(0));

    }

    @FXML void submitForm(ActionEvent event) {
        try {
            FurnitureItem record = new FurnitureItem(
                    txtFieldBarcode.getText(), txtFieldKeyNum.getText(), choiceBoxItemType.getValue(),
                    choiceBoxMaterial.getValue(),choiceBoxStatus.getValue(),
                    new Space(choiceBoxBuilding.getValue(),choiceBoxSpace.getValue()),
                    new FurnitureItemPurchase(txtFieldSupplier.getText(),
                            new SimpleDateFormat("dd/MM/yyyy").parse(txtFieldDate.getText()),
                            Integer.parseInt(txtFieldCost.getText()))
            );
            record.writeToDatabase();

            Stage stage = (Stage)((Node)(event.getSource())).getScene().getWindow();
            stage.close();

        } catch (ParseException e) {
            InvalidDataInputException.showErrorDialog("Invalid Date entered." +
                    " Date must be in the format of dd/MM/yyyy.");
        }
    }

}
