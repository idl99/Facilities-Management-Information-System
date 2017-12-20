package Entities;

import org.bson.Document;

import java.util.LinkedHashMap;
import java.util.Map;

public class BuildingFloor implements DatabaseObject{

    private String buildingNumber;
    private String buildingName;
    private String floorNumber;
    private String GFA;
    private String UFA;

    public BuildingFloor(String inputBuildingNumber, String inputBuildingName, String inputFloorNumber,
                         String inputGFA, String inputUFA){
        this(inputBuildingNumber,inputFloorNumber,inputGFA,inputUFA);
        this.buildingName = inputBuildingName;
    }

    public BuildingFloor(String inputBuildingNumber, String inputFloorNumber,
                         String inputGFA, String inputUFA){
        this.buildingNumber = inputBuildingNumber;
        this.floorNumber = inputFloorNumber;
        this.GFA = inputGFA;
        this.UFA = inputUFA;
    }

    /*public BuildingFloor(String inputFloorNumber){
        BasicDBObject query = new BasicDBObject();
        query.put("FloorNum",inputFloorNumber);
        readRecordFromDatabase(query);
    }*/

    @Override
    public void writeRecordToDatabase() {
        Map<String,Object> record = new LinkedHashMap<>();
        record.put("BuildingNum",this.buildingNumber);
        if(this.buildingName!=null) record.put("BuildingName",this.buildingName);
        record.put("FloorNum",this.floorNumber);
        record.put("FloorGFA",this.GFA);
        record.put("FloorUFA",this.UFA);
        database.getCollection("BuildingFloors").insertOne(new Document(record));
    }

    @Override
    public void readRecordFromDatabase() {
    }

    @Override
    public void modifyRecordInDatabase() {

    }

    @Override
    public void deleteRecordInDatabase() {

    }
}
