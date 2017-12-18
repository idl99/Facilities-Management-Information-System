package Login;

import Services.LoginService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import org.bson.Document;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginFormController implements javafx.fxml.Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblEmployeeId;

    @FXML
    private Label lblPassword;

    @FXML
    private ImageView iconError;

    @FXML
    private Label lblErrorMsg;

    @FXML
    private TextField txtFieldEmployeeId;
    public String getEmployeeId(){
        return txtFieldEmployeeId.getText();
    }

    @FXML
    private PasswordField txtFieldPassword;
    public String getPassword(){
        return txtFieldPassword.getText();
    }

    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void login(ActionEvent event) {
        try{
            Document authResult = LoginService.authenticate(getEmployeeId(),getPassword());
            lblErrorMsg.setText("Login Successful");
            lblErrorMsg.setVisible(true);
        } catch (Exception e){
            iconError.setVisible(true);
            lblErrorMsg.setVisible(true);
        }
    }

}
