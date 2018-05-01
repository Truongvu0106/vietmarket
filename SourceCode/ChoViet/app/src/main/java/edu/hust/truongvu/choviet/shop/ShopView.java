package edu.hust.truongvu.choviet.shop;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.entity.Product;
import edu.hust.truongvu.choviet.entity.Shop;

/**
 * Created by truon on 3/8/2018.
 */

public interface ShopView {
    void loadListProductByShopSuccessful(ArrayList<Product> listProduct);
    void loadListProductByShopFalse();

    void loadInforShopSuccessful(Shop shop);
    void loadInforShopFalse();
}
