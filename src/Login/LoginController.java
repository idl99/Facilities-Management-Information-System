package Login;

import Session.SessionController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private Employee user;

    @FXML private TextField txtFieldEmployeeId;
    @FXML private PasswordField txtFieldPassword;
    @FXML private ImageView iconError;
    @FXML private Label lblErrorMsg;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Adding listeners to text fields
        /*txtFieldEmployeeId.textProperty().addListener((observable, oldValue, newValue) ->
                user.setEmployeeId(newValue));
        txtFieldPassword.textProperty().addListener(((observable, oldValue, newValue) ->
                user.setPassword(newValue)));*/
    }

    @FXML
    void login(ActionEvent event) {
        try{
            new SessionController(Employee.login(txtFieldEmployeeId.getText(),
                    txtFieldPassword.getText()));
        } catch (Exception e){
            e.printStackTrace();
            iconError.setVisible(true);
            lblErrorMsg.setVisible(true);
        }
    }

}
