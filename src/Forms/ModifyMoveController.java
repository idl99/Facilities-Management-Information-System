package Forms;

import Entities.MoveRequest;
import Entities.RequestStatus;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class ModifyMoveController implements Initializable{

    private static MoveRequest requestToModify;

    @FXML
    private TextField txtFieldRequestNumber;

    @FXML
    private TextField txtFieldDate;

    @FXML
    private TextField txtFieldStatus;

    @FXML
    private TextField txtFieldScheduleDate;

    @FXML
    private TextField txtFieldScheduledTime;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML void searchMoveRequest(){
        requestToModify = MoveRequest.getRequestById(txtFieldRequestNumber.getText());

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        txtFieldDate.setText(MoveRequest.dateToZonedDateTime(requestToModify.getRequestedDateTime()).format(dateFormatter));
        txtFieldStatus.setText(requestToModify.getStatus().toString());
        txtFieldScheduleDate.setText(MoveRequest.dateToZonedDateTime(requestToModify.getScheduledDateTime()).format(dateFormatter));
        txtFieldScheduledTime.setText(MoveRequest.dateToZonedDateTime(requestToModify.getScheduledDateTime()).format(timeFormatter));

    }

    @FXML void updateMoveRequest(){
        if(requestToModify.getRequestedDateTime().getTime() !=
                getDate().toEpochSecond()){
            long currentTime = new Date().getTime()/(3600*1000);
            long requestedTime = getDate().toEpochSecond()/3600;
            long scheduledTime = requestToModify.getScheduledDateTime().getTime()/(3600*1000);
            double timeDiffCurrentAndScheduled = scheduledTime - currentTime;
            double timeDiffCurrentAndRequested = requestedTime - scheduledTime;

            if(timeDiffCurrentAndRequested>24 && timeDiffCurrentAndScheduled>24 ){
                requestToModify.setRequestedDateTime(MoveRequest.zonedDateTimeToDate(getDate()));
                requestToModify.setStatus(RequestStatus.Pending);
                requestToModify.setScheduledDateTime(new Date());
                requestToModify.updateInDatabase();
            }

        }
    }

    @FXML
    void cancelMoveRequest(){
        requestToModify.setStatus(RequestStatus.Cancelled);
        requestToModify.updateInDatabase();
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
