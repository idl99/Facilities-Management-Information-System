package Entities;

import Entities.Space.Space;
import Forms.MessageDialog;

import javafx.scene.image.Image;

import org.bson.types.ObjectId;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.PostPersist;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import static Application.Main.morphia;

@Entity("maintenanceRequests")
public class MaintenanceRequest {

    @Id private String requestId;
    private Space space;
    private Date dateTime;
    private String description;
    private RequestStatus status;

    public MaintenanceRequest(){

    }

    public MaintenanceRequest(Space space, ZonedDateTime dateTime, String description){
        this.requestId = generateRequestId();
        this.space = space;
        this.dateTime= zonedDateTimeToDate(dateTime);
        this.description = description;
        this.status = RequestStatus.Pending;
    }

    public String generateRequestId(){
        return String.valueOf(morphia.getDatastore().createQuery(MaintenanceRequest.class).count()+1);
    }

    public static Date zonedDateTimeToDate(ZonedDateTime zonedDateTime){
        return Date.from(zonedDateTime.toInstant());
    }

    public static ZonedDateTime dateToZonedDateTime(Date date){
        return ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of("Asia/Colombo"));
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Space getSpace() {
        return space;
    }

    public void setSpace(Space space) {
        this.space = space;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public void writeToDatabase(){
        morphia.getDatastore().save(this);
    }

    @PostPersist
    void showMessageDialog(){
        MessageDialog dialog = new MessageDialog.MessageDialogBuilder().
                withHeader("Successfully recorded Maintenance Request").
                withContentText("Successfully made Maintenace Request (Request Id) "+
                        this.requestId+". Please check in back later for approval status").
                withGraphic(new Image("/Graphics/Sucess_Icon.png")).
                build();
        dialog.show();
    }
}
