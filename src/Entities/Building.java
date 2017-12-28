package Entities;

import Application.DatabaseConfig;
import com.mongodb.client.DistinctIterable;

public class Building {
    private String buildingNumber;
    private String buildingName;

    Building(String buildingName,String buildingNumber){
        this(buildingNumber);
        this.buildingName = buildingName;
    }

    Building(String buildingNumber){
        this.buildingNumber = buildingNumber;
    }

    void getAll(){

    }

    public static DistinctIterable<String> getDistinctBuildingNumber(){
        return DatabaseConfig.BUILDING_FLOORS_COLLECTION.distinct("buildingNumber", String.class);
    }
}
