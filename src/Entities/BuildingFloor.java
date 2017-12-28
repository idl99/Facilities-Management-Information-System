package Entities;

import Application.DatabaseConfig;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.mongodb.client.model.Filters.*;

public class BuildingFloor{

    private String buildingNumber;
    private String buildingName;
    private String floorNumber;
    private String GFA;
    private String UFA;

    public BuildingFloor(String buildingNumber, String buildingName, String floorNumber,
                         String GFA, String UFA){
        this.buildingNumber = buildingNumber;
        this.buildingName = buildingName;
        this.floorNumber = floorNumber;
        this.GFA = GFA;
        this.UFA = UFA;
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

        Document record = new Document();

        record.put("buildingNumber",this.buildingNumber);
        if(this.buildingName.length()!=0) record.put("buildingName",this.buildingName);
        record.put("floorNumber",this.floorNumber);
        record.put("floorGFA",this.GFA);
        record.put("floorUFA",this.UFA);

        DatabaseConfig.BUILDING_FLOORS_COLLECTION.insertOne(record);

        return "Successfully added details of Building number- "+getBuildingNumber()+" Floor number- "+getFloorNumber();
    }

    public void readFromDatabase() {
    }

    public void modifyInDatabase() {

    }

    public void deleteInDatabase() {

    }

    public static List<String> getDistinctBuildingFloor(String buildingNumber){
         Iterator<String> iterator = DatabaseConfig.BUILDING_FLOORS_COLLECTION.distinct("floorNumber",
                and(eq("buildingNumber",buildingNumber)),String.class).iterator();
         List<String> listOfDistinctBuildingFloor = new ArrayList<>();
         iterator.forEachRemaining(listOfDistinctBuildingFloor::add);
         return listOfDistinctBuildingFloor;
    }

    public static List<String> getDistinctBuildingNumber(){
        Iterator<String > iterator = DatabaseConfig.BUILDING_FLOORS_COLLECTION.distinct(
                "buildingNumber", String.class).iterator();
        List<String> listOfDistinctBuildingNumber = new ArrayList<>();
        iterator.forEachRemaining(listOfDistinctBuildingNumber::add);
        return  listOfDistinctBuildingNumber;
    }
}
