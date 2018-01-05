package Session;

import Application.Main;
import Login.Employee;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SessionController implements Initializable{

    public static Employee sessionUser;

    @FXML private AnchorPane content;
    @FXML private Label lblSessionUser;
    @FXML private Label lblMenuHeader;
    @FXML private Button btnLogout;


    public SessionController(Employee sessionUser) throws Exception{
        this.sessionUser = sessionUser;
        // Load view
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("/Session/SessionView.fxml"));
        fxml.setController(this);
        Main.changeScreen(fxml.load());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblSessionUser.setText(sessionUser.getName().toUpperCase());
        lblMenuHeader.setText(sessionUser.getRole() +" Staff Main Menu");
        showMenuOptions();
    }

    public void showMenuOptions(){
        Pane options=null;
        try {
            if (sessionUser.getRole().equals("FMD")){
                options = FXMLLoader.load(getClass().getResource("/Session/FmdMenu.fxml"));
            }
            else if(sessionUser.getRole().equals("Normal")||
                    sessionUser.getRole().equals("Superior")){
                options = FXMLLoader.load(getClass().getResource("/Session/StaffMenu.fxml"));
            }
            content.getChildren().add(options);
            options.setLayoutX(176);
            options.setLayoutY(200);
        } catch (IOException ioE) {
            //
        } catch (NullPointerException npE){
            //
        }
    }

    @FXML public void logout(ActionEvent event){
        try {
            Main.changeScreen(FXMLLoader.load(getClass().getResource("/Login/LoginForm.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
