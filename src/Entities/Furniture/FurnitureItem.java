package Entities.Furniture;

import Entities.Space.Space;

import Forms.MessageDialog;
import javafx.scene.image.Image;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.PostPersist;
import org.mongodb.morphia.annotations.Reference;
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

    @Reference("spaces")
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

    public static FurnitureItem getFurnitureByBarcode(String barcode){
        final Query<FurnitureItem> query = morphia.getDatastore().createQuery(FurnitureItem.class);
        final List<FurnitureItem> items = query.asList();
        return items.get(0);
    }

    public void writeToDatabase(){
        morphia.getDatastore().save(this);
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

    @PostPersist
    void showMessageDialog(){
        MessageDialog dialog = new MessageDialog.MessageDialogBuilder().
                withHeader("Successfully inserted record into Furniture Items' Database").
                withContentText("Successfully inserted Furniture Item (Barcode Id) "+
                        this.barcode+" into Furniture Items' Database").
                withGraphic(new Image("/Graphics/Sucess_Icon.png")).
                build();
        dialog.show();
    }

}
