package edu.hust.truongvu.choviet.model.entity;

/**
 * Created by truon on 3/27/2018.
 */

public class Transport {
    private int id;
    private String note;
    private String title;
    private long price;

    public Transport(int id, String note, String title, long price) {
        this.id = id;
        this.note = note;
        this.title = title;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
