package Forms;

import Entities.MaintenanceRequest;
import Entities.RequestStatus;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ActionMaintenanceController implements Initializable {

    private MaintenanceRequest selectedRequest;

    @FXML
    private ListView<String> listViewMaintenanceRequests;

    @FXML
    private AnchorPane paneRequestDetails;

    @FXML
    private TextField txtFieldDate;

    @FXML
    private TextField txtFieldTime;

    @FXML
    private TextArea txtAreaDescription;

    @FXML
    private Button btnAction;

    @FXML
    private TextField txtFieldStatus;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bindMaintenanceReqeuest(listViewMaintenanceRequests);
        listViewMaintenanceRequests.getSelectionModel().selectedItemProperty().
                addListener(((observable, oldValue, newValue) -> {
                    selectedRequest = MaintenanceRequest.getById(newValue.split("-")[0]);
                    paneRequestDetails.setVisible(true);
                    bindRequestDetails();
                }));
    }

    void bindMaintenanceReqeuest(ListView listView){
        for(MaintenanceRequest request: MaintenanceRequest.getAll()){
            listViewMaintenanceRequests.getItems().add(
                    request.getRequestId()+"-"+request.getDescription()
            );
        }
    }

    void bindRequestDetails(){
        ZonedDateTime dateTime = MaintenanceRequest.dateToZonedDateTime(selectedRequest.getDateTime());
        txtFieldDate.setText(dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        txtFieldTime.setText(dateTime.format(DateTimeFormatter.ofPattern("HH:mm")));
        txtAreaDescription.setText(selectedRequest.getDescription());
        txtFieldStatus.setText(selectedRequest.getStatus().toString());
    }

    @FXML
    void setAsActioned(ActionEvent event) {
        selectedRequest.setStatus(RequestStatus.Actioned);
        selectedRequest.updateInDatabase();
    }

}
