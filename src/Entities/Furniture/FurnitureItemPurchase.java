package Entities.Furniture;

import java.util.Date;

public class FurnitureItemPurchase{

    private String supplier;
    private Date date;
    private int cost;

    public FurnitureItemPurchase(String supplier,Date date, int cost){
        this.supplier = supplier;
        this.date = date;
        this.cost = cost;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
