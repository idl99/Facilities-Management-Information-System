package Forms;

import Entities.MaintenanceRequest;
import Entities.Space.Space;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;

import java.time.*;
import java.time.format.DateTimeFormatter;

import java.util.ResourceBundle;

public class MaintenanceRequestController implements Initializable{

    @FXML
    private TextField txtFieldDate;

    @FXML
    private TextField txtFieldTime;

    @FXML
    private TextArea txtAreaDescription;

    @FXML
    private ChoiceBox<String> choiceBoxBuilding;

    @FXML
    private ChoiceBox<String> choiceBoxSpace;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FormBindings.bindBuildingNumbers(choiceBoxBuilding);
        choiceBoxBuilding.valueProperty().addListener(((observable, oldValue, newValue) -> {
            FormBindings.bindSpaces(choiceBoxSpace,choiceBoxBuilding.getValue());
        }));
    }

    @FXML
    void submitForm(ActionEvent event) {

        ZonedDateTime date = getDate();

        MaintenanceRequest request = new MaintenanceRequest(
                new Space(choiceBoxBuilding.getValue(),choiceBoxSpace.getValue()),
                date,
                txtAreaDescription.getText()
        );
        request.writeToDatabase();

        Stage stage = (Stage)((Node)(event.getSource())).getScene().getWindow();
        stage.close();
    }

    @FXML
    ZonedDateTime getDate(){

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        return ZonedDateTime.of(
                LocalDate.parse(txtFieldDate.getText(),dateFormatter),
                LocalTime.parse(txtFieldTime.getText(),timeFormatter),
                ZoneId.systemDefault()
        );
    }

}
