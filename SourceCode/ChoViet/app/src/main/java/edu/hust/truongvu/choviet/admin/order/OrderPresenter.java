package edu.hust.truongvu.choviet.admin.order;

/**
 * Created by truon on 6/4/2018.
 */

public interface OrderPresenter {
    void initListOrder();
    void updateStatusOrder(int id, int status);
    void deleteOrder(int id);
}
