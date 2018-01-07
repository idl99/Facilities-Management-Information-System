package Entities;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;
import org.mongodb.morphia.query.Sort;
import org.mongodb.morphia.utils.IndexDirection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static Application.Main.morphia;

@Entity("buildingFloors")
public class BuildingFloor{

    @Id
    private ObjectId id;
    @Indexed(value = IndexDirection.DESC)
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

    public void writeToDatabase() {
        morphia.getDatastore().save(this);
    }

    public static List<BuildingFloor> getAll(){
        return morphia.getDatastore().createQuery(BuildingFloor.class).asList();
    }

    public static BuildingFloor getById(String buildingFloor){
        return  morphia.getDatastore().createQuery(BuildingFloor.class)
                .field("buildingNumber").equal(buildingFloor.split("-")[0])
                .field("floorNumber").equal(buildingFloor.split("-")[1]).iterator().next();
    }

    public static List<String> distinctBuildingNumber(){
        Iterator<Document> iterator = morphia.getDatastore().createAggregation(BuildingFloor.class)
                .sort(Sort.descending("buildingNumber"))
                .group("buildingNumber")
                .aggregate(Document.class);
        List<String> list = new ArrayList<>();

        while (iterator.hasNext()) list.add(iterator.next().getString("_id"));

        return list;
    }

    public static List<String> distinctFloorNumber(String buildingNumber){
        Iterator<Document> iterator = morphia.getDatastore().createAggregation(BuildingFloor.class)
                .match(morphia.getDatastore().createQuery(BuildingFloor.class).
                        field("buildingNumber").equal(buildingNumber))
                .sort(Sort.descending("floorNumber"))
                .group("floorNumber")
                .aggregate(Document.class);
        List<String> list = new ArrayList<>();

        while (iterator.hasNext()) list.add(iterator.next().getString("_id"));

        return list;
    }


}
