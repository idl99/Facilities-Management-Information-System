package MainScreen;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static Application.Application.screen;

public class MainScreenController implements Initializable{

    private String sessionUser_EmpId;
    private String sessionUser_EmpName;
    private String sessionUser_EmpRole;

    @FXML private AnchorPane content;
    @FXML private Label lblSessionUser;
    @FXML private Button btnLogout;
    @FXML private Label lblMenuHeader;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setSessionCredentials(String user_EmpId, String user_EmpName, String user_EmpRole){
        sessionUser_EmpId = user_EmpId;
        sessionUser_EmpName = user_EmpName;
        sessionUser_EmpRole = user_EmpRole;
        lblSessionUser.setText(sessionUser_EmpName.toUpperCase());
        lblMenuHeader.setText(sessionUser_EmpRole.replace('_',' ')+" Main Menu");
        showMenuOptions();
    }

    public void showMenuOptions(){
        AnchorPane menuOptionsPane=null;
        try {
            if (sessionUser_EmpRole.equals("FMD_Staff")){
                menuOptionsPane = FXMLLoader.load(getClass().getResource("/MainScreen/FMD_MenuOptions.fxml"));
            }
            else if(sessionUser_EmpRole.equals("Normal_Staff")){
                menuOptionsPane = FXMLLoader.load(getClass().getResource("/MainScreen/NormalStaff_MenuOptions.fxml"));
            }
            content.getChildren().add(menuOptionsPane);
            menuOptionsPane.setLayoutX(176);
            menuOptionsPane.setLayoutY(200);
        } catch (IOException ioE) {
            //
        } catch (NullPointerException npE){
            //
        }
    }

    @FXML public void logout(ActionEvent event){
        try {
            screen.changeScreen(FXMLLoader.load(getClass().getResource("/Login/LoginForm.fxml")),800,400);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
