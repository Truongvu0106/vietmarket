package edu.hust.truongvu.choviet.cart;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.entity.Product;

/**
 * Created by truon on 2/27/2018.
 */

public interface CartView {
    void loadCartItem(ArrayList<Product> listProduct);
    void deleteSuccessful();
    void deleteFalse();
    void updateTotalMoney(long money);
}
