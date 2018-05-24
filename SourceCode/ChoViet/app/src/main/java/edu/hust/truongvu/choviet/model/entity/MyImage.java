package edu.hust.truongvu.choviet.model.entity;

import android.graphics.Bitmap;

/**
 * Created by truon on 4/28/2018.
 */

public class MyImage {
    private String name;
    private Bitmap bitmap;
    private String path;

    public MyImage(String name, Bitmap bitmap) {
        this.name = name;
        this.bitmap = bitmap;
    }

    public MyImage(String name, Bitmap bitmap, String path){
        this.name = name;
        this.bitmap = bitmap;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
