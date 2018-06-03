package edu.hust.truongvu.choviet.user.myorder;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.model.entity.Order;

/**
 * Created by truon on 6/3/2018.
 */

public interface OrderStatusView {
    void loadListSuccessful(ArrayList<Order> orders);
    void loadFalse();
}
