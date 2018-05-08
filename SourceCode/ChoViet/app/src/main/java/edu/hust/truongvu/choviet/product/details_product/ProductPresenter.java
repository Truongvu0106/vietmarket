package edu.hust.truongvu.choviet.product.details_product;

import android.content.Context;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.entity.Product;
import edu.hust.truongvu.choviet.entity.Rate;

/**
 * Created by truon on 2/26/2018.
 */

public interface ProductPresenter {
    Product getProductById(int id);
    void initListImage(ArrayList<String> imgs);
    void initListRate(String username, int id_product);
    void initListProduct();
    void initListSuggest();
    boolean addRate(Rate rate);
    void addToCart(Context context, Product product);
    void initInforShop(int idShop);
}
