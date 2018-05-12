package edu.hust.truongvu.choviet.model.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by truon on 2/22/2018.
 */

public class Product implements Serializable{
    private int id;
    private String name;
    private long price;
    private int discount;
    private ArrayList<String> imgs;
    private String infomation;
    private String weight;
    private int typeProduct;
    private int brand;
    private float rate;
    private int amount;
    private int idShop;
    private boolean highlight;
    private boolean isLike;

    private int numberSelect;

    public Product(){

    }

    public Product(int id, String name, long price, int discount, ArrayList<String> imgs, String infomation,
                   String weight, int typeProduct, int brand, float rate, int amount, int idShop, boolean highlight,
                   boolean isLike) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.imgs = imgs;
        this.infomation = infomation;
        this.weight = weight;
        this.typeProduct = typeProduct;
        this.brand = brand;
        this.rate = rate;
        this.amount = amount;
        this.idShop = idShop;
        this.highlight = highlight;
        this.isLike = isLike;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public ArrayList<String> getImgs() {
        return imgs;
    }

    public void setImgs(ArrayList<String> imgs) {
        this.imgs = imgs;
    }

    public String getInfomation() {
        return infomation;
    }

    public void setInfomation(String infomation) {
        this.infomation = infomation;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public int getTypeProduct() {
        return typeProduct;
    }

    public void setTypeProduct(int typeProduct) {
        this.typeProduct = typeProduct;
    }

    public int getBrand() {
        return brand;
    }

    public void setBrand(int brand) {
        this.brand = brand;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getIdShop() {
        return idShop;
    }

    public void setIdShop(int idShop) {
        this.idShop = idShop;
    }

    public boolean isHighlight() {
        return highlight;
    }

    public void setHighlight(boolean highlight) {
        this.highlight = highlight;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    public int getNumberSelect() {
        return numberSelect;
    }

    public void setNumberSelect(int numberSelect) {
        this.numberSelect = numberSelect;
    }
}
