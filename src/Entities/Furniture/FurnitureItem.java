package Entities.Furniture;

import Entities.Space.Space;

import ErrorHandling.InvalidDataInputException;
import Forms.MessageDialog;
import javafx.scene.image.Image;
import org.mongodb.morphia.annotations.*;
import org.mongodb.morphia.query.Query;

import java.lang.reflect.Field;
import java.util.List;

import static Application.Main.morphia;

@Entity("furnitureItems")
public class FurnitureItem{

    @Id
    private String barcode;
    private String keyNumber;
    private FurnitureItemType itemType;
    private FurnitureItemMaterial material;
    private FurnitureItemStatus status;
    private Space location;
    private FurnitureItemPurchase purchase;

    public FurnitureItem(){

    }

    public FurnitureItem(String barcode, String keyNumber, FurnitureItemType type,
                         FurnitureItemMaterial material, FurnitureItemStatus status,
                         Space location, FurnitureItemPurchase purchase){
        this.barcode = barcode;
        this.keyNumber = keyNumber;
        this.itemType = type;
        this.material = material;
        this.status = status;
        this.location = location;
        this.purchase = purchase;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getKeyNumber() {
        return keyNumber;
    }

    public void setKeyNumber(String keyNumber) {
        this.keyNumber = keyNumber;
    }

    public FurnitureItemType getItemType() {
        return itemType;
    }

    public void setItemType(FurnitureItemType itemType) {
        this.itemType = itemType;
    }

    public FurnitureItemMaterial getMaterial() {
        return material;
    }

    public void setMaterial(FurnitureItemMaterial material) {
        this.material = material;
    }

    public FurnitureItemStatus getStatus() {
        return status;
    }

    public void setStatus(FurnitureItemStatus status) {
        this.status = status;
    }

    public Space getLocation() {
        return location;
    }

    public void setLocation(Space location) {
        this.location = location;
    }

    public FurnitureItemPurchase getPurchase() {
        return purchase;
    }

    public void setPurchase(FurnitureItemPurchase purchase) {
        this.purchase = purchase;
    }

    public void writeToDatabase(){
        validateBarcode();
        morphia.getDatastore().save(this);
    }

    public static FurnitureItem getFurnitureByBarcode(String barcode){
        return morphia.getDatastore().createQuery(FurnitureItem.class)
                .field("_id").equal(barcode).iterator().next();
    }

    public static List<FurnitureItem> getFurnitureBySpace(String spaceId, String buildingNumber){
        return morphia.getDatastore().createQuery(FurnitureItem.class)
                .field("location._id").equal(spaceId)
                .field("location.buildingNumber").equal(buildingNumber).asList();
    }

    public void updateInDatabase(){
        Query<FurnitureItem> query = morphia.getDatastore().createQuery(FurnitureItem.class).
                  field("_id").equal(this.barcode);
        for(Field field: this.getClass().getDeclaredFields()){
            try {
                if(!field.getName().equals("barcode"))
                    morphia.getDatastore().update(query,
                            morphia.getDatastore().createUpdateOperations(FurnitureItem.class)
                            .set(field.getName(),field.get(this))
                    );
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @PrePersist
    void validateBarcode(){
        try{
            // Validate Barcode
            if(this.barcode.length()!=13) throw new InvalidDataInputException("Invalid Barcode Id. Barcode Id " +
                    "must be exactly 13 digits in length.") ;
            else if(this.barcode.matches(".*\\D+.*")) throw new InvalidDataInputException("Invalid Barcode Id. Barcode Id " +
                    "can only contain digits.");
        } catch (InvalidDataInputException e){
            e.showErrorDialog();
        }
    }

    @PostPersist
    void showMessageDialog(){
        MessageDialog dialog = new MessageDialog.MessageDialogBuilder().
                withHeader("Successfully inserted record into Furniture Items' Database").
                withContentText("Successfully inserted Furniture Item (Barcode Id) "+
                        this.barcode+" into Furniture Items' Database").
                withGraphic(new Image("/Graphics/Icons/Sucess_Icon.png")).
                build();
        String response = dialog.show();
    }

}
