package edu.hust.truongvu.choviet.product.details_product;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.entity.Product;
import edu.hust.truongvu.choviet.entity.Rate;
import edu.hust.truongvu.choviet.entity.Shop;

/**
 * Created by truon on 2/26/2018.
 */

public interface ProductView {
    void loadListImageSuccessful(ArrayList<String> list);
    void loadListImageFalse();

    void loadListRate(ArrayList<Rate> listRate);
    void setEnableRate(boolean isRated);

    void loadListProduct(ArrayList<Product> listProduct);

    void loadListSuggest(ArrayList<Product> listSuggest);

    void addToCartSuccessful();
    void addToCartFalse();

    void loadInforShopSuccessful(Shop shop);
    void loadInforShopFalse();
}
