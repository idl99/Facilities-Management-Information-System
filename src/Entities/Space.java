package Entities;

import Application.DatabaseConfig;

public class Space{

    private BuildingFloor buildingFloorDetails;
    private String id;
    private String name;
    private SpaceType type;
    private String occupant_EmployeeId;
    private String occupyingDepartment;

    public Space(BuildingFloor buildingFloorDetails, String id, String name, SpaceType type,
                 String empId, String department){
        this.buildingFloorDetails = buildingFloorDetails;
        this.id = id;
        this.name = name;
        this.type = type;
        this.occupant_EmployeeId = empId;
        this.occupyingDepartment = department;
    }

    public BuildingFloor getBuildingFloorDetails() {
        return buildingFloorDetails;
    }

    public void setBuildingFloorDetails(BuildingFloor buildingFloorDetails) {
        this.buildingFloorDetails = buildingFloorDetails;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        DatabaseConfig.SPACES_COLLECTION.insertOne(this);
        return "Successfully added Space Id - "+this.id+" to Spaces Database.";
    }

}
