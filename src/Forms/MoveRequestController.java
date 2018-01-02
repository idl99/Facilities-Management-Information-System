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

import java.text.ParseException;
import java.text.SimpleDateFormat;

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
        FormBindings.bindBuildingNumbers(choiceBoxBuilding);

        choiceBoxBuilding.valueProperty().addListener(((observable, oldValue, newValue) -> {
            FormBindings.bindSpacesBySpaceType(choiceBoxSpace, choiceBoxBuilding.getValue(),
                    SpaceType.OfficeSpace);
        }));

    }

    @FXML
    void submitForm(ActionEvent event) {
        try {
            MoveRequest request = new MoveRequest(
                    SessionController.sessionUser,
                    new Space(choiceBoxBuilding.getValue(),choiceBoxSpace.getValue()),
                    new SimpleDateFormat("dd/MM/yyyy").parse(txtFieldDate.getText()),
                    txtFieldComments.getText()
            );
            request.writeToDatabase();

            Stage stage = (Stage)((Node)(event.getSource())).getScene().getWindow();
            stage.close();

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
