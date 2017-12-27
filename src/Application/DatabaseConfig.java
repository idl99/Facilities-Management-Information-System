package Application;

import Entities.BuildingFloor;

import Entities.Space;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class DatabaseConfig {

    public static final MongoClient CLIENT = new MongoClient("Localhost", 27017);

    // Codec that serializes a POJO, as writable to MongoDB
    public static final CodecRegistry POJO_CODEC_REGISTRY = fromRegistries(MongoClient.getDefaultCodecRegistry(),
            fromProviders(PojoCodecProvider.builder().automatic(true).build()));

    public static final MongoDatabase DATABASE = CLIENT.getDatabase("Cw03Database").withCodecRegistry(POJO_CODEC_REGISTRY);

    public static final MongoCollection<Document> EMPLOYEES_COLLECTION = DATABASE.getCollection("Employees");
    public static final MongoCollection<BuildingFloor> BUILDING_FLOORS_COLLECTION = DATABASE.getCollection("BuildingFloors",
            BuildingFloor.class);
    public  static final MongoCollection<Space> SPACES_COLLECTION = DATABASE.getCollection("Spaces",
            Space.class);

    private DatabaseConfig(){

    }

}
