package edu.hust.truongvu.choviet.payment.confirm;

import edu.hust.truongvu.choviet.entity.Order;

/**
 * Created by truon on 4/24/2018.
 */

public interface ConfirmPresenter {
    void initView(String address, int idTransport, int idPayMethod);
    void confirmOrder(Order order);
}