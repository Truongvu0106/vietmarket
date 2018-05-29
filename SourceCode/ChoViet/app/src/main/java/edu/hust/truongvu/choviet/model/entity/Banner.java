package edu.hust.truongvu.choviet.model.entity;

/**
 * Created by truon on 5/29/2018.
 */

public class Banner {
    private int id;
    private String title;
    private String image;
    private int type;

    public Banner(int id, String title, String image, int type) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
