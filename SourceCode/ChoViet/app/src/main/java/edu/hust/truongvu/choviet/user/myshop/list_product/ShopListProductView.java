package edu.hust.truongvu.choviet.user.myshop.list_product;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.model.entity.Product;

/**
 * Created by truon on 5/9/2018.
 */

public interface ShopListProductView {
    void loadListSuccessful(ArrayList<Product> data);
    void loadListFalse();

    void deleteSuccessful();
    void deleteFalse();
}
