package Entities;

import Entities.Space.Space;
import Forms.MessageDialog;

import javafx.scene.image.Image;

import org.bson.types.ObjectId;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.PostPersist;

import java.time.ZonedDateTime;

import static Application.Main.morphia;

@Entity("maintenanceRequests")
public class MaintenanceRequest {

    @Id private String requestId;
    private Space space;
    private ZonedDateTime dateTime;
    private String description;
    private RequestStatus status;

    public MaintenanceRequest(){

    }

    public MaintenanceRequest(Space space, ZonedDateTime dateTime, String description){
        this.requestId = ObjectId.get().toString();
        this.space = space;
        this.dateTime= dateTime;
        this.description = description;
        this.status = RequestStatus.Pending;
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

    public ZonedDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(ZonedDateTime dateTime) {
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
