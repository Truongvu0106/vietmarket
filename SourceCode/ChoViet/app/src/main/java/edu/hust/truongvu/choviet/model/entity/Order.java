package edu.hust.truongvu.choviet.model.entity;

import java.util.ArrayList;

/**
 * Created by truon on 4/24/2018.
 */

public class Order {
    private int id;
    private int idCustomer;
    private long dateOrder;
    private int status;
    private int typeTransport;
    private int typePayment;
    private long price;
    private String address;

    private ArrayList<OrderDetails> orderDetails;

    public Order(int id, int idCustomer, long dateOrder, int status, int typeTransport, int typePayment, long price, String address) {
        this.id = id;
        this.idCustomer = idCustomer;
        this.dateOrder = dateOrder;
        this.status = status;
        this.typeTransport = typeTransport;
        this.typePayment = typePayment;
        this.price = price;
        this.address = address;
    }

    public Order(int id, int idCustomer, long dateOrder, int status, int typeTransport, int typePayment, long price, String address,
                 ArrayList<OrderDetails> orderDetails) {
        this.id = id;
        this.idCustomer = idCustomer;
        this.dateOrder = dateOrder;
        this.status = status;
        this.typeTransport = typeTransport;
        this.typePayment = typePayment;
        this.price = price;
        this.address = address;
        this.orderDetails = orderDetails;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public long getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(long dateOrder) {
        this.dateOrder = dateOrder;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTypeTransport() {
        return typeTransport;
    }

    public void setTypeTransport(int typeTransport) {
        this.typeTransport = typeTransport;
    }

    public int getTypePayment() {
        return typePayment;
    }

    public void setTypePayment(int typePayment) {
        this.typePayment = typePayment;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(ArrayList<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
