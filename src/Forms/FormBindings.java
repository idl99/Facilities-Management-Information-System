package Forms;

import Entities.BuildingFloor;
import Entities.Furniture.FurnitureItemMaterial;
import Entities.Furniture.FurnitureItemStatus;
import Entities.Furniture.FurnitureItemType;
import Entities.Space.Space;
import Entities.Space.SpaceType;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

import java.util.List;


public class FormBindings {

    private FormBindings(){

    }

    @FXML static void bindBuildingNumbers(ChoiceBox<String> choiceBox){
        choiceBox.getItems().clear();
        List<String> list =  BuildingFloor.distinctBuildingNumber();
        for(String number: list) choiceBox.getItems().add(number);
    }

    @FXML static void bindFloorNumbers(ChoiceBox<String> choiceBox, String buildingNumber){
        choiceBox.getItems().clear();
        List<String> list = BuildingFloor.distinctFloorNumber(buildingNumber);
        for(String number: list) choiceBox.getItems().add(number);
    }


    @FXML static void bindSpaces(ChoiceBox<String> choiceBox, String buildingNumber){
        choiceBox.getItems().clear();
        List<Space> spaces = Space.getSpaceByBuildingNumber(buildingNumber);
        for (Space space: spaces){
            choiceBox.getItems().add(space.getSpaceId());
        }
    }

    @FXML static void bindSpacesBySpaceType(ChoiceBox<String> choiceBox, String buildingNumber,
                                            SpaceType spaceType){
        choiceBox.getItems().clear();
        List<Space> spaces = Space.getSpaceByBuildingNumber(buildingNumber);
        for (Space space: spaces){
            if(space.getType() == spaceType)
                choiceBox.getItems().add(space.getSpaceId());
        }
    }

    @FXML static void bindSpaceType(ChoiceBox<SpaceType> choiceBox){
        choiceBox.getItems().clear();
        for(SpaceType enumVal: SpaceType.values()){
            choiceBox.getItems().add(enumVal);
        }
    }

    @FXML static void bindFurnitureItemType(ChoiceBox<FurnitureItemType> choiceBox){
        choiceBox.getItems().clear();
        for(FurnitureItemType type: FurnitureItemType.values()){
            choiceBox.getItems().add(type);
        }
    }

    @FXML static void bindFurnitureItemMaterial(ChoiceBox<FurnitureItemMaterial> choiceBox,
                                                FurnitureItemType type){
        choiceBox.getItems().clear();
        if(type == FurnitureItemType.Desk || type == FurnitureItemType.FilingCabinet){
            choiceBox.getItems().addAll(
                    FurnitureItemMaterial.Chipboard,
                    FurnitureItemMaterial.Melamine,
                    FurnitureItemMaterial.StainedPine);
        }
        else if(type == FurnitureItemType.BookCase){
            choiceBox.getItems().addAll(
                    FurnitureItemMaterial.Metal,
                    FurnitureItemMaterial.StainedPine
            );
        }
        else{
            choiceBox.getItems().add(FurnitureItemMaterial.Unspecified);
        }
    }

    @FXML static void bindFurnitureItemStatus(ChoiceBox<FurnitureItemStatus> choiceBox){
        choiceBox.getItems().clear();
        for(FurnitureItemStatus status: FurnitureItemStatus.values()){
            choiceBox.getItems().add(status);
        }

    }

}
