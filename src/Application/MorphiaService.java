package Application;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

public class MorphiaService {

    private Morphia morphia;
    private Datastore datastore;

    MorphiaService(){

        this.morphia = new Morphia();
        MongoClient client = new MongoClient(new MongoClientURI("### insert Mongo Client Uri here ###"));
        this.datastore = morphia.createDatastore(client,
                "Cw03Database");

    }

    public Morphia getMorphia() {
        return morphia;
    }

    public void setMorphia(Morphia morphia) {
        this.morphia = morphia;
    }

    public Datastore getDatastore() {
        return datastore;
    }

    public void setDatastore(Datastore datastore) {
        this.datastore = datastore;
    }

}
