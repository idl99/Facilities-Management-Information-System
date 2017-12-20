package Login;

import Application.ScreenManager;

import MainScreen.MainScreenController;

import Services.LoginService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import javafx.scene.layout.AnchorPane;
import org.bson.Document;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginFormController implements javafx.fxml.Initializable {

    @FXML private TextField txtFieldEmployeeId;
    @FXML private PasswordField txtFieldPassword;
    @FXML private ImageView iconError;
    @FXML private Label lblErrorMsg;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public String getEmployeeId(){
        return txtFieldEmployeeId.getText();
    }


    public String getPassword(){
        return txtFieldPassword.getText();
    }

    @FXML
    void login(ActionEvent event) {
        try{
            Document login_credentials = LoginService.authenticate(getEmployeeId(),getPassword());

            String loggedInUser_EmployeeId = login_credentials.getString("EmpId");
            String loggedInUser_EmployeeName = login_credentials.getString("EmpName");
            String loggedInUser_EmployeeRole = login_credentials.getString("EmpRole");

            // First, load the FXML resource
            // Creating instance of FXML Loader to gain access to setController()
            FXMLLoader fxml_loader = new FXMLLoader(getClass().
                    getResource("/MainScreen/MainScreen.fxml"));

            // Loading menu screen scene graph from FXML File
            // FXML file should be loaded first in order to access the controller using
            // the getController() instance method of FXML Loader
            AnchorPane main_menu_screen = fxml_loader.load();

            // Secondly, call setSessionCredentials() method from controller of FXML resource
            MainScreenController main_menu_controller = fxml_loader.getController();
            main_menu_controller.setSessionCredentials(loggedInUser_EmployeeId, loggedInUser_EmployeeName, loggedInUser_EmployeeRole);

            ScreenManager.changeScreen(main_menu_screen,800,768);

        } catch (Exception e){
            iconError.setVisible(true);
            lblErrorMsg.setVisible(true);
        }
    }

}
