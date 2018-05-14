package edu.hust.truongvu.choviet.user.followingshop;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.model.entity.Shop;

/**
 * Created by truon on 5/14/2018.
 */

public interface FollowingShopView {
    void loadListShopSuccessful(ArrayList<Shop> data);
    void loadListShopFalse();
}
