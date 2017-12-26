package Application;

import Entities.BuildingFloor;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public interface DatabaseConnectivity {
    // Interface to define the constants holding configuration to the local database.
    // By doing so, the application code with relation to database connectivity configurations
    // can be easily maintained through this interface.
    CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
            fromProviders(PojoCodecProvider.builder().automatic(true).build()));

    MongoClient client = new MongoClient("Localhost", 27017);
    MongoDatabase database = client.getDatabase("Cw03Database").withCodecRegistry(pojoCodecRegistry);

    MongoCollection<Document> EmployeesCollection = database.getCollection("Employees");
    MongoCollection<BuildingFloor> BuildingFloorsCollection = database.getCollection("BuildingFloors",
            BuildingFloor.class);

}
