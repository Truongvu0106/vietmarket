package edu.hust.truongvu.choviet.model.entity;

/**
 * Created by truon on 4/24/2018.
 */

public class OrderDetails {
    private int id;
    private int idOrder;
    private int idProduct;
    private int idShop;
    private int number;
    private long date;
    private int status;

    public OrderDetails(int id, int idOrder, int idProduct, int idShop, int number, long date, int status) {
        this.id = id;
        this.idOrder = idOrder;
        this.idProduct = idProduct;
        this.idShop = idShop;
        this.number = number;
        this.date = date;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getIdShop() {
        return idShop;
    }

    public void setIdShop(int idShop) {
        this.idShop = idShop;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
