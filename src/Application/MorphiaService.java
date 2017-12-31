package Application;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

public class MorphiaService {

    private Morphia morphia;
    private Datastore datastore;

    MorphiaService(){

        this.morphia = new Morphia();
        this.datastore = morphia.createDatastore(new MongoClient("Localhost",27017),
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
