package edu.hust.truongvu.choviet.order.confirm;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.model.entity.Order;
import edu.hust.truongvu.choviet.model.entity.OrderDetails;

/**
 * Created by truon on 4/24/2018.
 */

public interface ConfirmPresenter {
    void initView(String name, String phone, String address, int idTransport, int idPayMethod);
    void confirmOrder(Order order);
    void updateStockProduct(ArrayList<OrderDetails> data);
}
