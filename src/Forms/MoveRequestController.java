package Forms;

import Entities.MoveRequest;
import Entities.Space.Space;
import Entities.Space.SpaceType;
import Session.SessionController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class MoveRequestController implements Initializable {

    @FXML
    private ChoiceBox<String> choiceBoxBuilding;

    @FXML
    private ChoiceBox<String> choiceBoxSpace;

    @FXML
    private TextField txtFieldDate;

    @FXML
    private TextArea txtFieldComments;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        choiceBoxBuilding.valueProperty().addListener(((observable, oldValue, newValue) -> {
            FormBindings.bindSpacesBySpaceType(choiceBoxSpace, choiceBoxBuilding.getValue(),
                    SpaceType.OfficeSpace);
        }));

        FormBindings.bindBuildingNumbers(choiceBoxBuilding);

    }

    @FXML
    void submitForm(ActionEvent event) {
        ZonedDateTime date = getDate();
        new MoveRequest(
                SessionController.sessionUser,
                new Space(choiceBoxBuilding.getValue(),choiceBoxSpace.getValue()),
                date,
                txtFieldComments.getText()
        ).writeToDatabase();

        Stage stage = (Stage)((Node)(event.getSource())).getScene().getWindow();
        stage.close();

    }

    @FXML
    ZonedDateTime getDate(){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return ZonedDateTime.of(
                LocalDate.parse(txtFieldDate.getText(),formatter),
                LocalTime.of(0,0),
                ZoneId.systemDefault()
        );
    }

}
