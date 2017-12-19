package MainMenu;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.bson.Document;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable{

    private Document authorization_credentials;

    @FXML public Label lblWelcomeMsg;

    public void setLblWelcomeMsg(String session_user){
        lblWelcomeMsg.setText("Welcome "+session_user.toUpperCase()+
                " to the FMIS");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
