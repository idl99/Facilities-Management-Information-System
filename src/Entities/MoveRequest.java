package Entities;

import Entities.Space.Space;

import Forms.MessageDialog;
import Login.Employee;

import javafx.scene.image.Image;
import org.bson.types.ObjectId;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.PostPersist;

import java.util.Date;

import static Application.Main.morphia;

@Entity("moveRequests")
public class MoveRequest{

    private @Id String requestId;
    private Employee requestedBy;
    private Space moveTo;
    private Date relocationDate;
    private String comments;

    public MoveRequest(){

    }

    public MoveRequest(Employee requestedBy, Space moveTo, Date relocationDate, String comments){
        this.requestId = new ObjectId().toString();
        this.requestedBy = requestedBy;
        this.moveTo = moveTo;
        this.relocationDate = relocationDate;
        this.comments = comments;
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

    public Date getRelocationDate() {
        return relocationDate;
    }

    public void setRelocationDate(Date relocationDate) {
        this.relocationDate = relocationDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void writeToDatabase(){
        morphia.getDatastore().save(this);
    }

    @PostPersist
    void showMessageDialog(){
        MessageDialog dialog = new MessageDialog.MessageDialogBuilder().
                withHeader("Successfully made Relocation Request  ").
                withContentText("Successfully made Relocation Request (Request Id) "+
                        this.requestId+". Please check in back later for approval status").
                withGraphic(new Image("/Graphics/Sucess_Icon.png")).
                build();
        dialog.show();
    }
}
