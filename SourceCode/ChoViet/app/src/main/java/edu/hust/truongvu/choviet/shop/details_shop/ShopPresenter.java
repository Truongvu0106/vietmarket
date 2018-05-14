package edu.hust.truongvu.choviet.shop.details_shop;

import edu.hust.truongvu.choviet.model.entity.Shop;

/**
 * Created by truon on 3/8/2018.
 */

public interface ShopPresenter {
    void initListProduct(int idShop);
    void initInforShop(Shop shop);

    void checkFollowing(int idUser, int idShop);

    void follow(int idUser, int idShop);

    void unFollow(int idUser, int idShop);
}
