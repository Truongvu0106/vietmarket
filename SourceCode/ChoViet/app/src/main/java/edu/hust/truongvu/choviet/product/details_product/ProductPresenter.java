package edu.hust.truongvu.choviet.product.details_product;

import android.content.Context;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.model.entity.Product;
import edu.hust.truongvu.choviet.model.entity.Rate;

/**
 * Created by truon on 2/26/2018.
 */

public interface ProductPresenter {
    void initListImage(ArrayList<String> imgs);
    void initListRate(String username, int id_product);
    void initListProductOther(int idShop);
    void initListProductSuggest(int idCategory);
    boolean addRate(Rate rate);
    void addToCart(Context context, Product product);
    void initInforShop(int idShop);

    void updateRate(Rate rate);

}
