package Login;

import com.mongodb.*;
import com.mongodb.client.*;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;

import org.bson.Document;

public class LoginService {

    final static MongoClient client = new MongoClient("Localhost", 27017);
    final static MongoDatabase database = client.getDatabase("Cw03Database");

    private LoginService() {

    }

    public static Document authenticate(String empId, String password){

        MongoCollection<Document> Employees = database.getCollection("Employees");
        FindIterable queryResult = Employees.find(and(eq("EmpId",empId)
                ,eq("LoginPwd",password))).projection(fields(include("EmpName","EmpID","EmpRole"),excludeId()));
        return (Document) queryResult.iterator().next();

    }

}
