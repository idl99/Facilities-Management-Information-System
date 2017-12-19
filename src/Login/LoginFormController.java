package Login;

import Application.ScreenManager;
import MainMenu.MainMenuController;
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
            Document authResult = LoginService.authenticate(getEmployeeId(),getPassword());
//            lblErrorMsg.setText("Login Successful");
//            lblErrorMsg.setVisible(true);

            // Creating instance of FXML Loader to gain access to setController()
            FXMLLoader fxml_loader = new FXMLLoader(getClass().
                    getResource("/MainMenu/MainMenu.fxml"));
            AnchorPane main_menu_screen = fxml_loader.load();
            MainMenuController main_menu_controller = fxml_loader.getController();
            main_menu_controller.setLblWelcomeMsg(authResult.getString("EmpName"));

            ScreenManager.changeScreen(main_menu_screen,1024,768);


        } catch (Exception e){
            e.printStackTrace();
            iconError.setVisible(true);
            lblErrorMsg.setVisible(true);
        }
    }

}
