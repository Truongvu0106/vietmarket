package edu.hust.truongvu.choviet.entity;

/**
 * Created by truon on 3/22/2018.
 */

public class Rate {
    private int idProduct;
    private String userName;
    private String title;
    private String content;
    private float starRate;
    private String date;

    public Rate(int idProduct, String userName, String title, String content, float starRate, String date) {
        this.idProduct = idProduct;
        this.userName = userName;
        this.title = title;
        this.content = content;
        this.starRate = starRate;
        this.date = date;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public float getStarRate() {
        return starRate;
    }

    public void setStarRate(float starRate) {
        this.starRate = starRate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
