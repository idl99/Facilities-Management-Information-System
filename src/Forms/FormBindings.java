package Forms;

import Entities.BuildingFloor;
import Entities.Space;
import Entities.SpaceType;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

import java.util.Iterator;
import java.util.List;


public class FormBindings {

    private FormBindings(){

    }

    @FXML static void bindBuildingNumbers(ChoiceBox<String> choiceBox){
        choiceBox.getItems().clear();
        List<String> buildingNumbers =  BuildingFloor.getDistinctBuildingNumber();
        Iterator<String> iterator =  buildingNumbers.iterator();
        while(iterator.hasNext()){
            choiceBox.getItems().add(iterator.next());
        }
    }

    @FXML static void bindFloorNumbers(ChoiceBox<String> choiceBox, String buildingNumber){
        choiceBox.getItems().clear();
        List<String> listOfDistinctBuildingFloors = BuildingFloor.getDistinctBuildingFloor(buildingNumber);
        for(String floor: listOfDistinctBuildingFloors){
            choiceBox.getItems().add(floor);
        }
    }


    @FXML static void bindSpaces(ChoiceBox<String> choiceBox, String buildingNumber){
        choiceBox.getItems().clear();
        List<Space> spaces = Space.getSpaceByBuildingNumber(buildingNumber);
        for (Space space: spaces){
            choiceBox.getItems().add(space.getSpaceId());
        }
    }

    @FXML static void bindSpaceType(ChoiceBox<SpaceType> choiceBox){
        choiceBox.getItems().clear();
        for(SpaceType enumVal: SpaceType.values()){
            choiceBox.getItems().add(enumVal);
        }
    }

}
