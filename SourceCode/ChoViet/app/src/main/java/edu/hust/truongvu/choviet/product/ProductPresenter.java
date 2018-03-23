package edu.hust.truongvu.choviet.product;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.entity.Product;

/**
 * Created by truon on 2/26/2018.
 */

public interface ProductPresenter {
    Product getProductById(int id);
    void initListImage(ArrayList<String> imgs);
    void initListRate(String username, int id_product);
    void initListProduct();
    void initListSuggest();
}
