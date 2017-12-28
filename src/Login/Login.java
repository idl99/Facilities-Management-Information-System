package Login;

// MongoDB imports
import Application.DatabaseConfig;
import com.mongodb.client.*;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;

import org.bson.Document;

public class Login{

    private String loginId;
    private String loginPassword;

    Login() {
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    Document authenticate(){
        FindIterable query = DatabaseConfig.EMPLOYEES_COLLECTION.find(and(eq("EmpId",loginId)
                ,eq("LoginPwd",loginPassword))).projection(fields(include("EmpId", "EmpName", "EmpRole"),excludeId()));
        return (Document) query.iterator().next();
    }

}
