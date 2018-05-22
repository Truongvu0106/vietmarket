package edu.hust.truongvu.choviet.user.myshop.registershop;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.model.entity.MyImage;
import edu.hust.truongvu.choviet.model.entity.Shop;

/**
 * Created by truon on 5/18/2018.
 */

public interface RegisterPresenter {
    void register(Shop shop);
    void uploadImage(ArrayList<MyImage> data);
}
