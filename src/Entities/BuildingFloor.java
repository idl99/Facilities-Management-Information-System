package Entities;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static Application.Main.morphia;

@Entity("buildingFloors")
public class BuildingFloor{

    @Id
    private ObjectId id;
    private String buildingNumber;
    private String buildingName;
    private String floorNumber;
    private String GFA;
    private String UFA;

    public BuildingFloor(){

    }

    public BuildingFloor(String buildingNumber, String buildingName, String floorNumber,
                         String GFA, String UFA){
        this.id = ObjectId.get();
        this.buildingNumber = buildingNumber;
        this.buildingName = buildingName;
        this.floorNumber = floorNumber;
        this.GFA = GFA;
        this.UFA = UFA;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(String floorNumber) {
        this.floorNumber = floorNumber;
    }

    public String getGFA() {
        return GFA;
    }

    public void setGFA(String GFA) {
        this.GFA = GFA;
    }

    public String getUFA() {
        return UFA;
    }

    public void setUFA(String UFA) {
        this.UFA = UFA;
    }

    public String writeToDatabase() {
        morphia.getDatastore().save(this);
        return "Successfully added details of Building number- "+getBuildingNumber()+" Floor number- "+getFloorNumber();
    }

    public static List<String> distinctBuildingNumber(){
        Iterator<Document> iterator = morphia.getDatastore().createAggregation(BuildingFloor.class)
                .group("buildingNumber")
                .aggregate(Document.class);
        List<String> list = new ArrayList<>();

        while (iterator.hasNext()) list.add(iterator.next().getString("_id"));

        return list;
    }

    public static List<String> distinctFloorNumber(String buildingNumber){
        // TO BE COMPLETED
        return new ArrayList<String>();
    }


}
