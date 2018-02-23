package edu.hust.truongvu.choviet.entity;

/**
 * Created by truon on 2/22/2018.
 */

public class Brand {
    private int id;
    private String name;
    private int logo;

    public Brand(int id, String name, int logo) {
        this.id = id;
        this.name = name;
        this.logo = logo;
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

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }
}
