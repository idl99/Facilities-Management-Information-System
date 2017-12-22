package Login;

import Entities.DatabaseConnectivity;
import com.mongodb.client.*;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;

import org.bson.Document;

public class LoginService implements DatabaseConnectivity{

    private LoginService() {

    }

    public static Document authenticate(String empId, String password){
        FindIterable queryResult = EmployeesCollection.find(and(eq("EmpId",empId)
                ,eq("LoginPwd",password))).projection(fields(include("EmpName","EmpID","EmpRole"),excludeId()));
        return (Document) queryResult.iterator().next();
    }

}
