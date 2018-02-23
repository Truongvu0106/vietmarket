package edu.hust.truongvu.choviet.entity;

/**
 * Created by truon on 2/22/2018.
 */

public class PopularSearch {
    private int id;
    private String keySearch;
    private int img;

    public PopularSearch(int id, String keySearch, int img) {
        this.id = id;
        this.keySearch = keySearch;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKeySearch() {
        return keySearch;
    }

    public void setKeySearch(String keySearch) {
        this.keySearch = keySearch;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
