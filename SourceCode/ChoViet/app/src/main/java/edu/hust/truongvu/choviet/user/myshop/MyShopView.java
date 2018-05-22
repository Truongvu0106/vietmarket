package edu.hust.truongvu.choviet.user.myshop;

import edu.hust.truongvu.choviet.model.entity.Shop;

/**
 * Created by truon on 5/22/2018.
 */

public interface MyShopView {
    void loadShopSuccessful(Shop shop);
    void loadShopFalse();
}
