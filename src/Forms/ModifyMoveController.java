package Forms;

import Entities.MoveRequest;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ModifyMoveController implements Initializable{

    private static MoveRequest requestToModify;

    @FXML
    private TextField txtFieldRequestNumber;

    @FXML
    private TextField txtFieldDate;

    @FXML
    private TextField txtFieldStatus;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML void searchMoveRequest(){
        requestToModify = MoveRequest.getRequestById(txtFieldRequestNumber.getText());

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        txtFieldDate.setText(MoveRequest.dateToZonedDateTime(requestToModify.getRequestedDateTime()).format(dateFormatter));

        txtFieldStatus.setText(requestToModify.getStatus().toString());

    }

//    TODO Updating details of Move Request

}
