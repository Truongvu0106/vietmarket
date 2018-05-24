package edu.hust.truongvu.choviet.user.myshop.list_order;

import edu.hust.truongvu.choviet.helper.Constants;
import edu.hust.truongvu.choviet.model.entity.Order;

/**
 * Created by truon on 5/9/2018.
 */

public interface ShopListOrderPresenter {
    void initListOrder(int idShop);
    void updateStatus(Order order, Constants.OrderStatus status);
    void deleteOrder(int idOrder);
}
