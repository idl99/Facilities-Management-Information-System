package MainScreen;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable{

    private String sessionUser_EmpId;
    private String sessionUser_EmpName;
    private String sessionUser_EmpRole;

    @FXML public Label lblWelcomeMsg;

    public void setSessionCredentials(String user_EmpId, String user_EmpName, String user_EmpRole){
        sessionUser_EmpId = user_EmpId;
        sessionUser_EmpName = user_EmpName;
        sessionUser_EmpRole = user_EmpRole;
        setLblWelcomeMsg();
    }

    public void setLblWelcomeMsg(){
        lblWelcomeMsg.setText(sessionUser_EmpName.toUpperCase()+" from "+sessionUser_EmpRole.toUpperCase());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
