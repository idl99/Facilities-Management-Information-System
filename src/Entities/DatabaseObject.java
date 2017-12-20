package Entities;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public interface DatabaseObject {

    MongoClient client = new MongoClient("Localhost", 27017);
    MongoDatabase database = client.getDatabase("Cw03Database");

    void writeRecordToDatabase();

    void readRecordFromDatabase();

    void modifyRecordInDatabase();

    void deleteRecordInDatabase();

}
