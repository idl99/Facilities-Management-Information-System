package Forms;

import Application.DateTimeConversion;
import Entities.MoveRequest;
import Entities.RequestStatus;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ScheduleMoveController implements Initializable{

    private static MoveRequest selectedRequest;

    @FXML
    private ListView<String> listViewMaintenanceRequests;

    @FXML
    private Accordion accordionMoveDetails;

    @FXML
    private TitledPane titledPaneRequestDetails;

    @FXML
    private TextField txtFieldEmployee;

    @FXML
    private TextField txtFieldMoveToBuilding;

    @FXML
    private TextField txtFieldMoveToSpace;

    @FXML
    private TextField txtFieldDate;

    @FXML
    private TextArea txtFieldComments;

    @FXML
    private TextField txtFieldScheduledDate;

    @FXML
    private TextField txtFieldScheduledTime;

    @FXML
    private TextField txtFieldStatus;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnReject;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bindMoveRequests(listViewMaintenanceRequests);
        listViewMaintenanceRequests.getSelectionModel().selectedItemProperty().
                addListener(((observable, oldValue, newValue) -> {
            accordionMoveDetails.setVisible(true);
            bindMoveDetailsToForm(listViewMaintenanceRequests.getSelectionModel().getSelectedItem().split("-")[0]);
        }));
    }

    @FXML
    void actionMove(ActionEvent event){
        selectedRequest.setStatus(RequestStatus.Actioned);
        selectedRequest.updateInDatabase();
    }

    @FXML
    void rejectMove(ActionEvent event) {
        selectedRequest.setStatus(RequestStatus.Rejected);
        selectedRequest.updateInDatabase();
    }

    @FXML
    void updateMoveDetails(ActionEvent event) {/*
        new MoveRequest.MoveRequestBuilder(selectedRequest.getRequestedBy(),selectedRequest.getMoveTo(),
                MoveRequest.dateToZonedDateTime(selectedRequest.getRequestedDateTime()),
                selectedRequest.getComments())
                .ofId(selectedRequest.getRequestId())
                .scheduledFor(
                        getScheduledDateTime())
                .build().setStatus(selectedRequest.getStatus()).updateInDatabase();*/
        selectedRequest.setStatus(RequestStatus.Accepted);
        selectedRequest.setScheduledDateTime(DateTimeConversion.zonedDateTimeToDate(getScheduledDateTime()));
        selectedRequest.updateInDatabase();
    }

    void bindMoveRequests(ListView view){
    for(MoveRequest request : MoveRequest.getAll()){
            listViewMaintenanceRequests.getItems().add(request.getRequestId()+"-"+
                    request.getRequestedBy().getName()+"-"+request.getStatus());
        }
    }

    void bindMoveDetailsToForm(String requestId){
        this.selectedRequest = MoveRequest.getRequestById(requestId);

        txtFieldEmployee.setText(selectedRequest.getRequestedBy().getName());
        txtFieldMoveToBuilding.setText(selectedRequest.getMoveTo().getBuildingNumber());
        txtFieldMoveToSpace.setText(selectedRequest.getMoveTo().getSpaceId());
        txtFieldDate.setText(
                DateTimeConversion.dateToZonedDateTime(selectedRequest.getRequestedDateTime()).
                        format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        );
        txtFieldComments.setText(selectedRequest.getComments());

        txtFieldStatus.setText(selectedRequest.getStatus().toString());
        try {
            txtFieldScheduledDate.setText(DateTimeConversion.dateToZonedDateTime(
                    selectedRequest.getScheduledDateTime()).
                    format(DateTimeFormatter.ofPattern("dd/MM/yyyy")
                    ));
            txtFieldScheduledTime.setText(DateTimeConversion.dateToZonedDateTime(
                    selectedRequest.getScheduledDateTime()).format(DateTimeFormatter.ofPattern("HH:mm")
                    ));
        } catch (NullPointerException e) {
            // Move hasn't been scheduled yet
        }

        if(selectedRequest.getStatus()==RequestStatus.Accepted){
            btnReject.setDisable(true);
        }

        accordionMoveDetails.setExpandedPane(titledPaneRequestDetails);

    }

    @FXML
    ZonedDateTime getScheduledDateTime(){

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");


        return ZonedDateTime.of(
                LocalDate.parse(txtFieldScheduledDate.getText(),dateFormatter),
                LocalTime.parse(txtFieldScheduledTime.getText(),timeFormatter),
                ZoneId.systemDefault()
        );
    }

}
