package edu.hust.truongvu.choviet.product.list_product;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.entity.Product;

/**
 * Created by truon on 3/8/2018.
 */

public interface ListProductView {
    void loadListSuccessful(boolean isLastest, ArrayList<Product> listProduct);
    void loadListFalse();
}
