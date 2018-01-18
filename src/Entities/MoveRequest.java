package Entities;

import Application.DateTimeConversion;
import Entities.Space.Space;

import Forms.MessageDialog;
import Login.Employee;

import javafx.scene.image.Image;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.PostPersist;
import org.mongodb.morphia.query.Query;

import java.lang.reflect.Field;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import static Application.Main.morphia;

@Entity("moveRequests")
public class MoveRequest{

    private @Id String requestId;
    private Employee requestedBy;
    private Space moveTo;
    private Date requestedDateTime;
    private String comments;
    private RequestStatus status;
    private Date scheduledDateTime;

    public MoveRequest(){

    }

    private MoveRequest(MoveRequestBuilder builder){
        this.requestId = builder.requestId;
        this.requestedBy = builder.requestedBy;
        this.moveTo = builder.moveTo;
        this.requestedDateTime = builder.requestedDateTime;
        this.comments = builder.comments;
        this.status = builder.status;
        this.scheduledDateTime = builder.scheduledDateTime;
    }

    public static String generateRequestId(){
        return String.valueOf(morphia.getDatastore().createQuery(MoveRequest.class).count()+1);
    }


    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Employee getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(Employee requestedBy) {
        this.requestedBy = requestedBy;
    }

    public Space getMoveTo() {
        return moveTo;
    }

    public void setMoveTo(Space moveTo) {
        this.moveTo = moveTo;
    }

    public Date getRequestedDateTime() {
        return requestedDateTime;
    }

    public void setRequestedDateTime(Date requestedDateTime) {
        this.requestedDateTime = requestedDateTime;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public Date getScheduledDateTime() {
        return scheduledDateTime;
    }

    public void setScheduledDateTime(Date scheduledDateTime) {
        this.scheduledDateTime = scheduledDateTime;
    }

    public static List<MoveRequest> getAll(){
        return morphia.getDatastore().find(MoveRequest.class).asList();
    }

    public static MoveRequest getRequestById(String id){
        return morphia.getDatastore().createQuery(MoveRequest.class)
                .field("_id").equal(id)
                .iterator().next();
    }

    public void writeToDatabase(){
        morphia.getDatastore().save(this);
    }

    public void updateInDatabase(){
        Query<MoveRequest> query = morphia.getDatastore().createQuery(MoveRequest.class).
                field("_id").equal(this.requestId);
        for(Field field: this.getClass().getDeclaredFields()){
            try {
                morphia.getDatastore().update(query,
                        morphia.getDatastore().createUpdateOperations(MoveRequest.class)
                                .set(field.getName(),field.get(this))
                );
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public void unsetScheduledDateTime(){
        Query<MoveRequest> query = morphia.getDatastore().createQuery(MoveRequest.class).
                field("_id").equal(this.requestId);
        morphia.getDatastore().updateFirst(query,
                morphia.getDatastore().createUpdateOperations(MoveRequest.class)
                        .unset("scheduledDateTime")
        );
    }

    @PostPersist
    void showMessageDialog(){
        MessageDialog dialog = new MessageDialog.MessageDialogBuilder().
                withHeader("Successfully made Relocation Request  ").
                withContentText("Successfully made Relocation Request (Request Id) "+
                        this.requestId+". Please check in back later for approval status").
                withGraphic(new Image("/Graphics/Icons/Sucess_Icon.png")).
                build();
        dialog.show();
    }

    public static class MoveRequestBuilder{

        private String requestId;
        private Employee requestedBy;
        private Space moveTo;
        private Date requestedDateTime;
        private String comments;
        private RequestStatus status = RequestStatus.Pending;
        private Date scheduledDateTime;

        public MoveRequestBuilder(Employee requestedBy, Space moveTo, ZonedDateTime requestedDateTime, String comments){
            this.requestId = generateRequestId();
            this.moveTo = moveTo;
            this.requestedBy = requestedBy;
            this.requestedDateTime = DateTimeConversion.zonedDateTimeToDate(requestedDateTime);
            this.comments = comments;
        }

        public MoveRequestBuilder ofId(String requestId){
            this.requestId = requestId;
            return this;
        }

        public MoveRequestBuilder scheduledFor(ZonedDateTime scheduledDateTime){
            this.scheduledDateTime = DateTimeConversion.zonedDateTimeToDate(scheduledDateTime);
            return this;
        }

        public MoveRequestBuilder requestStatus(RequestStatus status){
            this.status = status;
            return this;
        }

        public MoveRequest build(){
            return new MoveRequest(this);
        }


    }
}
