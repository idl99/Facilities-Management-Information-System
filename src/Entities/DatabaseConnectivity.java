package Entities;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public interface DatabaseConnectivity {

    // Interface to define the contract of all objects connecting to the local database.
    // By doing so, the application code with relation to database connectivity configurations
    // can be easily maintained through this interface.

    MongoClient client = new MongoClient("Localhost", 27017);
    MongoDatabase database = client.getDatabase("Cw03Database");

    MongoCollection<Document> BuildingFloorsCollection = database.getCollection("BuildingFloors");

    String writeRecordToDatabase();

    void readRecordFromDatabase();

    void modifyRecordInDatabase();

    void deleteRecordInDatabase();

}
