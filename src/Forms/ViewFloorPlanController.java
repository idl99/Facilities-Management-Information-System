package Forms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewFloorPlanController implements Initializable{

    @FXML
    private ChoiceBox<String> choiceBoxBuilding;

    @FXML
    private ChoiceBox<String> choiceBoxFloor;

    @FXML
    private ImageView imageViewFloorPlan;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        FormBindings.bindBuildingNumbers(choiceBoxBuilding);

        choiceBoxBuilding.valueProperty().addListener(((observable, oldValue, newValue) -> {
            FormBindings.bindFloorNumbers(choiceBoxFloor, choiceBoxBuilding.getValue());
        }));

    }

    @FXML
    void viewFloorPlan(ActionEvent event) {
        imageViewFloorPlan.setImage(new Image("/Graphics/FloorPlans/"+choiceBoxBuilding.getValue()+"-"+choiceBoxFloor.getValue()+".jpg"));
    }

}
