package edu.hust.truongvu.choviet.product.details_product;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.model.entity.Product;
import edu.hust.truongvu.choviet.model.entity.Rate;
import edu.hust.truongvu.choviet.model.entity.Shop;

/**
 * Created by truon on 2/26/2018.
 */

public interface ProductView {
    void loadListImageSuccessful(ArrayList<String> list);
    void loadListImageFalse();

    void loadListRateSuccessful(ArrayList<Rate> listRate);
    void loadListRateFalse();

    void setEnableRate(boolean isRated);

    void loadListProductOtherSuccessful(ArrayList<Product> listProduct);
    void loadListProductOtherFalse();

    void loadLisProductSuggestSuccessful(ArrayList<Product> listSuggest);
    void loadListProductSuggestFalse();

    void addToCartSuccessful();
    void addToCartFalse();

    void loadInforShopSuccessful(Shop shop);
    void loadInforShopFalse();
}
