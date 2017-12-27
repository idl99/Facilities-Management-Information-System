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

    // Interface for configurations of the database persistence layer
    // By implementing this interface in persisted POJOs, database configurations can be easily modified
    // and maintained through this interface

    MongoClient client = new MongoClient("Localhost", 27017);

    // Codec that serializes a POJO, as writable to MongoDB
    CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
            fromProviders(PojoCodecProvider.builder().automatic(true).build()));

    MongoDatabase database = client.getDatabase("Cw03Database").withCodecRegistry(pojoCodecRegistry);

    MongoCollection<Document> EmployeesCollection = database.getCollection("Employees");
    MongoCollection<BuildingFloor> BuildingFloorsCollection = database.getCollection("BuildingFloors",
            BuildingFloor.class);

}
