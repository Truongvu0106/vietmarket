package edu.hust.truongvu.choviet.cart;

import android.content.Context;

/**
 * Created by truon on 2/27/2018.
 */

public interface CartPresenter {
    void initListItemCart(Context context);
    void deleteItemCart(Context context, int id_product);
    void updateNumberSelect(Context context, int id_product, int number);
    void calculateTotal(Context context);
    int getNumberItemCart(Context context);

}
