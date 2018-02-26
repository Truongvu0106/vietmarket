package edu.hust.truongvu.choviet.entity;

/**
 * Created by truon on 2/26/2018.
 */

public class ProductRate {
    private int id;
    private int imgCustomer;
    private String nameCustomer;
    private float rate;
    private String comment;
    private String date;

    public ProductRate(int id, int imgCustomer, String nameCustomer, float rate, String comment, String date) {
        this.id = id;
        this.imgCustomer = imgCustomer;
        this.nameCustomer = nameCustomer;
        this.rate = rate;
        this.comment = comment;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImgCustomer() {
        return imgCustomer;
    }

    public void setImgCustomer(int imgCustomer) {
        this.imgCustomer = imgCustomer;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
