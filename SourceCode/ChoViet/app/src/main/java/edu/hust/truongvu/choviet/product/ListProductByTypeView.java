package edu.hust.truongvu.choviet.product;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.entity.Product;

/**
 * Created by truon on 3/8/2018.
 */

public interface ListProductByTypeView {
    void loadListSuccessful(ArrayList<Product> listProduct);
    void loadListFalse();
}
