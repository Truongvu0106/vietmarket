package edu.hust.truongvu.choviet.admin.order;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.model.entity.Order;

/**
 * Created by truon on 6/4/2018.
 */

public interface OrderView {
    void loadOrdersSuccessful(ArrayList<Order> data);
    void loadOrderFalse();

    void updateSuccessful();
    void updateFalse();

    void deleteSuccessful();
    void deleteFalse();
}
