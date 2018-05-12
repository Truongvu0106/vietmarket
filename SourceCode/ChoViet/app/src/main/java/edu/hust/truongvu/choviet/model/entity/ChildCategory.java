package edu.hust.truongvu.choviet.model.entity;

/**
 * Created by truon on 2/22/2018.
 */

public class ChildCategory {
    private int id;
    private String name;
    private int idParent;
    private int img;
    private String path_img;

    public ChildCategory(int id, String name, int idParent, int img) {
        this.id = id;
        this.name = name;
        this.idParent = idParent;
        this.img = img;
    }

    public ChildCategory(int id, String name, int idParent, int img, String path_img) {
        this.id = id;
        this.name = name;
        this.idParent = idParent;
        this.img = img;
        this.path_img = path_img;
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

    public int getIdParent() {
        return idParent;
    }

    public void setIdParent(int idParent) {
        this.idParent = idParent;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getPath_img() {
        return path_img;
    }

    public void setPath_img(String path_img) {
        this.path_img = path_img;
    }
}
