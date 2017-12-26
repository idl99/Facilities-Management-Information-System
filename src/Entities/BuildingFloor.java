package Entities;

import Application.DatabaseConnectivity;

public class BuildingFloor implements DatabaseConnectivity{

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

    /*
    *   NOTE: VERY VERY IMPORTANT
    *       In order to make a POJO serializable in MongoDB, either
    *           1) Public getter and setter methods should be defined for the object fields
    *           2) Else fields should be declared public
    *   REFER to http://mongodb.github.io/mongo-java-driver/3.6/bson/pojos/ if necessary
    */

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
        BuildingFloorsCollection.insertOne(this);
        return "Successfully added details of Building number- "+getBuildingNumber()+" Floor number- "+getFloorNumber();
    }

    public void readFromDatabase() {
    }

    public void modifyInDatabase() {

    }

    public void deleteInDatabase() {

    }
}
