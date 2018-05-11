package edu.hust.truongvu.choviet.cart;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.entity.Product;

/**
 * Created by truon on 2/27/2018.
 */

public interface CartView {
    void loadCartItemSuccessful(ArrayList<Product> listProduct);
    void loadCartItemFalse();

    void deleteSuccessful();
    void deleteFalse();

    void updateTotalMoney(long money);

    void updateNumberSuccessful();
    void updateNumberFalse();
}
