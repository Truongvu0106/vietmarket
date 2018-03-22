package edu.hust.truongvu.choviet.entity;

/**
 * Created by truon on 2/22/2018.
 */

public class Shop {
    private int id;
    private String name;
    private String slogan;
    private String imgAvatar;
    private String imgCover;
    private int idOwner;
    private String address;
    private String phone;
    private String website;
    private float rate;
    private boolean highlight;

    public Shop(int id, String name, String slogan, String imgAvatar, String imgCover, int idOwner,
                String address, String phone, String website, float rate, boolean highlight) {
        this.id = id;
        this.name = name;
        this.slogan = slogan;
        this.imgAvatar = imgAvatar;
        this.imgCover = imgCover;
        this.idOwner = idOwner;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.rate = rate;
        this.highlight = highlight;
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

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getImgAvatar() {
        return imgAvatar;
    }

    public void setImgAvatar(String imgAvatar) {
        this.imgAvatar = imgAvatar;
    }

    public String getImgCover() {
        return imgCover;
    }

    public void setImgCover(String imgCover) {
        this.imgCover = imgCover;
    }

    public int getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(int idOwner) {
        this.idOwner = idOwner;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public boolean getHighlight() {
        return highlight;
    }

    public void setHighlight(boolean highlight) {
        this.highlight = highlight;
    }
}
