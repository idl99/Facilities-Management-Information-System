package Login;

import Session.SessionController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import org.bson.Document;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private Login login;

    @FXML private TextField txtFieldEmployeeId;
    @FXML private PasswordField txtFieldPassword;
    @FXML private ImageView iconError;
    @FXML private Label lblErrorMsg;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Instantiating the login model
        login = new Login();

        //Adding listeners to text fields
        txtFieldEmployeeId.textProperty().addListener((observable, oldValue, newValue) ->
                login.setLoginId(newValue));
        txtFieldPassword.textProperty().addListener(((observable, oldValue, newValue) ->
                login.setLoginPassword(newValue)));
    }

    @FXML
    void login(ActionEvent event) {
        try{
            Document response = login.authenticate();
            SessionController sessionController = new SessionController(
                    response.getString("EmpId"),
                    response.getString("EmpName"),
                    response.getString("EmpRole")
            );
        } catch (Exception e){
            e.printStackTrace();
            iconError.setVisible(true);
            lblErrorMsg.setVisible(true);
        }
    }

}
