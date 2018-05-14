package edu.hust.truongvu.choviet.shop.details_shop;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.model.entity.Product;
import edu.hust.truongvu.choviet.model.entity.Shop;

/**
 * Created by truon on 3/8/2018.
 */

public interface ShopView {
    void loadListProductByShopSuccessful(ArrayList<Product> listProduct);
    void loadListProductByShopFalse();

    void loadInforShopSuccessful(Shop shop);
    void loadInforShopFalse();

    void initFollow(boolean isFollowing);

    void followSuccessful();
    void followFalse();

    void unFollowSuccessful();
    void unFollowFalse();
}
