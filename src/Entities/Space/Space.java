package Entities.Space;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.List;

import static Application.Main.morphia;

@Embedded
@Entity("spaces")
public class Space{

    private String buildingNumber;
    private String buildingFloorNumber;
    @Id private String spaceId;
    private String name;
    private SpaceType type;
    private String occupant_EmployeeId;
    private String occupyingDepartment;

    public Space(){

    }

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

    public Space(String buildingNumber, String spaceId){
        this.buildingNumber = buildingNumber;
        this.spaceId = spaceId;
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
        morphia.getDatastore().save(this);
        return "Successfully added Space Id - "+this.spaceId +" to Spaces Database.";
    }

    public static List<Space> getSpaceByBuildingNumber(String buildingNumber){
        return morphia.getDatastore().createQuery(Space.class).
                field("buildingNumber").equal(buildingNumber).
                asList();
    }

}
