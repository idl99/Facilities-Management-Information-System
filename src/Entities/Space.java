package Entities;

import Application.DatabaseConfig;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.mongodb.client.model.Filters.*;

public class Space{

    private String buildingNumber;
    private String buildingFloorNumber;
    private String spaceId;
    private String name;
    private SpaceType type;
    private String occupant_EmployeeId;
    private String occupyingDepartment;

    public Space(String buildingNumber, String buildingFloorNumber, String spaceId, String name, SpaceType type,
                 String empId, String department){
        this.buildingNumber = buildingNumber;
        this.buildingFloorNumber = buildingFloorNumber;
        this.spaceId = spaceId;
        this.name = name;
        this.type = type;
        this.occupant_EmployeeId = empId;
        this.occupyingDepartment = department;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getBuildingFloorNumber() {
        return buildingFloorNumber;
    }

    public void setBuildingFloorNumber(String buildingFloorNumber) {
        this.buildingFloorNumber = buildingFloorNumber;
    }

    public String getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(String spaceId) {
        this.spaceId = spaceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SpaceType getType() {
        return type;
    }

    public void setType(SpaceType type) {
        this.type = type;
    }

    public String getOccupant_EmployeeId() {
        return occupant_EmployeeId;
    }

    public void setOccupant_EmployeeId(String occupant_EmployeeId) {
        this.occupant_EmployeeId = occupant_EmployeeId;
    }

    public String getOccupyingDepartment() {
        return occupyingDepartment;
    }

    public void setOccupyingDepartment(String occupyingDepartment) {
        this.occupyingDepartment = occupyingDepartment;
    }

    public String writeToDatabase(){
        Document record = new Document();

        record.put("buildingNumber",this.buildingNumber);
        record.put("buildingFloorNumber",this.buildingFloorNumber);
        record.put("spaceId",this.spaceId);
        record.put("name",this.name);
        record.put("type",this.type.name());
        record.put("occupant",this.occupant_EmployeeId);
        record.put("department",this.occupyingDepartment);


        DatabaseConfig.SPACES_COLLECTION.insertOne(record);
        return "Successfully added Space Id - "+this.spaceId +" to Spaces Database.";
    }

    public static List<Space> getSpaceByBuildingNumber(String buildingNumber){
        Iterator<Document> iterator = DatabaseConfig.SPACES_COLLECTION.find(
                eq("buildingNumber",buildingNumber),Document.class).iterator();
        List<Space> listOfSpaces = new ArrayList<>();
        while (iterator.hasNext()){
            Document space = iterator.next();
            listOfSpaces.add(new Space(space.getString("buildingNumber"),
                    space.getString("buildingFloorNumber"),
                    space.getString("spaceId"),
                    space.getString("name"),
                    SpaceType.valueOf(space.getString("type")),
                    space.getString("occupant"),
                    space.getString("department")));

        }
        return listOfSpaces;
    }

}
