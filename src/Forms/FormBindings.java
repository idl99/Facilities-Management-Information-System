package Forms;

import Entities.Building;

import Entities.BuildingFloor;
import Entities.SpaceType;
import com.mongodb.client.DistinctIterable;

import javafx.fxml.FXML;

import javafx.scene.control.ChoiceBox;

import java.util.Iterator;


public class FormBindings {

    private FormBindings(){

    }

    @FXML static void bindBuildingNumbers(ChoiceBox<String> choiceBox){
        choiceBox.getItems().clear();
        DistinctIterable<String> buildingNumbers =  Building.getDistinctBuildingNumber();
        Iterator<String> iterator =  buildingNumbers.iterator();
        while(iterator.hasNext()){
            choiceBox.getItems().add(iterator.next());
        }
    }

    @FXML static void bindFloorNumbers(ChoiceBox<String> choiceBox, String buildingNumber){
        choiceBox.getItems().clear();
        DistinctIterable<String> floorNumbers = BuildingFloor.getDistinctBuildingFloor(buildingNumber);
        Iterator<String> iterator = floorNumbers.iterator();
        while(iterator.hasNext()){
            choiceBox.getItems().add(iterator.next());
        }
    }

    @FXML static void bindSpaceType(ChoiceBox<SpaceType> choiceBox){
        choiceBox.getItems().clear();
        for(SpaceType enumVal: SpaceType.values()){
            choiceBox.getItems().add(enumVal);
        }
    }

}
