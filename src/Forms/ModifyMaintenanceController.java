package Forms;

import Application.DataEntryForm;
import Application.DateTimeConversion;
import Entities.MaintenanceRequest;
import Entities.RequestStatus;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ModifyMaintenanceController implements Initializable{

    private static MaintenanceRequest requestToModify;

    @FXML
    private TextField txtFieldDate;

    @FXML
    private TextField txtFieldTime;

    @FXML
    private TextArea txtAreaDescription;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnCancel;

    @FXML
    private TextField txtFieldRequestNumber;

    @FXML
    private TextField txtFieldStatus;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void searchForRequest(ActionEvent event){

        requestToModify = MaintenanceRequest.getById(txtFieldRequestNumber.getText());

        String status =  requestToModify.getStatus().toString();
        ZonedDateTime dateTime = DateTimeConversion.dateToZonedDateTime(requestToModify.getDateTime());

        if(status.equals("Actioned")||status.equals("Cancelled")){
            btnUpdate.setDisable(true);
        } else btnUpdate.setDisable(false);

        if(status.equals("Cancelled")){
            btnCancel.setDisable(true);
        } else btnUpdate.setDisable(false);

        txtFieldDate.setText(dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        txtFieldTime.setText(dateTime.format(DateTimeFormatter.ofPattern("HH:mm")));
        txtAreaDescription.setText(requestToModify.getDescription());
        txtFieldStatus.setText(status);


    }

    @FXML
    void submitForm(ActionEvent event) {

        if(((Node)event.getSource()).getId().equals("btnCancel")) {
            requestToModify.setStatus(RequestStatus.Cancelled);
            requestToModify.updateInDatabase();
        } else{
            requestToModify.setDateTime(
                DateTimeConversion.zonedDateTimeToDate(getDateTime()));
            requestToModify.setDescription(txtAreaDescription.getText());
            requestToModify.setStatus(RequestStatus.Pending);
            requestToModify.updateInDatabase();
        }

        DataEntryForm.closeFormOnSubmit(event);

    }

    @FXML
    ZonedDateTime getDateTime(){

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        return ZonedDateTime.of(
                LocalDate.parse(txtFieldDate.getText(),dateFormatter),
                LocalTime.parse(txtFieldTime.getText(),timeFormatter),
                ZoneId.systemDefault()
        );
    }

}