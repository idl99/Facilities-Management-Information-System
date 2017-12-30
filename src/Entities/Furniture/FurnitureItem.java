package Entities.Furniture;

import Application.Main;
import Entities.Space.Space;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;
import org.mongodb.morphia.query.Query;

import java.util.List;

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

    public void writeToDatabase(){
//        DatabaseConfig.FURNITURE_ITEM_MONGO_COLLECTION.insertOne(this);
        Main.datastore.save(this);
    }

    public static FurnitureItem getFurnitureByBarcode(String barcode){
        final Query<FurnitureItem> query = Main.datastore.createQuery(FurnitureItem.class);
        final List<FurnitureItem> items = query.asList();
        return items.get(0);
    }

}
