package Entities;

import org.bson.Document;

public class BuildingFloor implements DatabaseConnectivity {

    private String buildingNumber;
    private String buildingName;
    private String floorNumber;
    private String GFA;
    private String UFA;

    public BuildingFloor(String inputBuildingNumber, String inputBuildingName, String inputFloorNumber,
                         String inputGFA, String inputUFA){
        this.buildingNumber = inputBuildingNumber;
        this.buildingName = inputBuildingName;
        this.floorNumber = inputFloorNumber;
        this.GFA = inputGFA;
        this.UFA = inputUFA;
    }

    public String writeRecordToDatabase() {

        Document record = new Document();

        record.put("BuildingNum",this.buildingNumber);
        if(this.buildingName.length()!=0) record.put("BuildingName",this.buildingName);
        record.put("FloorNum",this.floorNumber);
        record.put("FloorGFA",this.GFA);
        record.put("FloorUFA",this.UFA);

        BuildingFloorsCollection.insertOne(record);

        return "Successfully added details of Building number- "+buildingNumber+" Floor number- "+floorNumber;
    }

    public void readRecordFromDatabase() {
    }

    public void modifyRecordInDatabase() {

    }

    public void deleteRecordInDatabase() {

    }
}
