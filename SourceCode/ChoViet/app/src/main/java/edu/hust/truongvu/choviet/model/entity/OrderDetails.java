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

    public OrderDetails(int id, int idOrder, int idProduct, int idShop, int number) {
        this.id = id;
        this.idOrder = idOrder;
        this.idProduct = idProduct;
        this.idShop = idShop;
        this.number = number;
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
}
