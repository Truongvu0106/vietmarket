package edu.hust.truongvu.choviet.wishlist;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.entity.Product;

/**
 * Created by truon on 5/6/2018.
 */

public interface WishListProductView {
    void loadListSuccessful(ArrayList<Product> data);
    void loadListFalse();
}
