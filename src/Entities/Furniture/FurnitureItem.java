package Entities.Furniture;

import Application.DatabaseConfig;
import Entities.Space.Space;
import org.bson.Document;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

public class FurnitureItem{

    private String barcode;
    private String keyNumber;
    private FurnitureItemType itemType;
    private FurnitureItemMaterial material;
    private FurnitureItemStatus status;

    private Space location;
    private FurnitureItemPurchase purchase;

    public FurnitureItem(FurnitureItemBuilder builder){

        this.barcode = builder.barcode;
        this.keyNumber = builder.keyNumber;
        this.itemType = builder.itemType;
        this.material = builder.material;
        this.status = builder.status;

        this.location = builder.location;
        this.purchase = builder.purchase;
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
        DatabaseConfig.FURNITURE_ITEM_MONGO_COLLECTION.insertOne(this);
    }

    public FurnitureItem dbRecordToPojo(Document record){
        return new FurnitureItem.FurnitureItemBuilder(
                record.getString("barcode"),
                record.getString("keyNumber"),
                FurnitureItemType.valueOf(record.getString("itemType")),
                FurnitureItemMaterial.valueOf(record.getString("material")),
                FurnitureItemStatus.valueOf(record.getString("status"))
        ).locatedAt(new Space(
                record.getString("location.buildingNumber"),
                record.getString("location.spaceId")
        )).purchaseDetails(new FurnitureItemPurchase(
                record.getString("purchase.supplier"),
                record.getDate("purchase.date"),
                record.getInteger("purchase.cost")
        )).build();
    }

    public FurnitureItem getByBuildingSpace(String buildingNumber, String spaceId){
        Document record = DatabaseConfig.FURNITURE_ITEM_MONGO_COLLECTION.find(and(eq("buildingNumber",buildingNumber),
                eq("spaceId",spaceId)),Document.class).iterator().next();
        return dbRecordToPojo(record);
    }

    public static class FurnitureItemBuilder{
        private String barcode;
        private String keyNumber;
        private FurnitureItemType itemType;
        private FurnitureItemMaterial material;
        private FurnitureItemStatus status;

        private Space location;
        private FurnitureItemPurchase purchase;

        public FurnitureItemBuilder(String barcode, String keyNumber, FurnitureItemType itemType,
                                    FurnitureItemMaterial material, FurnitureItemStatus status){
            this.barcode = barcode;
            this.keyNumber = keyNumber;
            this.itemType = itemType;
            this.material = material;
            this.status = status;
        }

        public FurnitureItemBuilder locatedAt(Space location){
            this.location = location;
            return this;
        }

        public FurnitureItemBuilder purchaseDetails(FurnitureItemPurchase purchase){
            this.purchase = purchase;
            return this;
        }

        public FurnitureItem build(){
            return new FurnitureItem(this);
        }

    }
}
