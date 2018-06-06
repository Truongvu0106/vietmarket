package edu.hust.truongvu.choviet.user.myshop.list_order;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.model.entity.OrderDetails;

/**
 * Created by truon on 5/9/2018.
 */

public interface ShopListOrderView {
    void loadListOrderSuccessful(ArrayList<OrderDetails> data);
    void loadListOrderFalse();

    void updateSuccessful();
    void updateFalse();

    void deleteSuccessful();
    void deleteFalse();
}
