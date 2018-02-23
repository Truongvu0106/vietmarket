package edu.hust.truongvu.choviet.entity;

/**
 * Created by truon on 2/22/2018.
 */

public class Product {
    private int id;
    private String name;
    private int img;
    private long price;
    private int discount;
    private boolean isLike;
    private int rate;

    public Product(int id, String name, int img, long price, int discount, boolean isLike, int rate) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.price = price;
        this.discount = discount;
        this.isLike = isLike;
        this.rate = rate;
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

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
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

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
