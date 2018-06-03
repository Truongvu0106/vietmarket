package edu.hust.truongvu.choviet.user.myorder;

import android.content.Context;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.model.OrderModel;
import edu.hust.truongvu.choviet.model.entity.Order;

/**
 * Created by truon on 6/3/2018.
 */

public class OrderStatusPresenterImp implements OrderStatusPresenter{
    private Context mContext;
    private OrderModel orderModel;
    private OrderStatusView view;

    public OrderStatusPresenterImp(Context context, OrderStatusView view){
        this.mContext = context;
        this.view = view;
        orderModel = new OrderModel(mContext);
    }

    @Override
    public void initListOrder(int idUser, int status) {
        ArrayList<Order> data = orderModel.getOrderByUser(idUser, status);
        if (data == null || data.isEmpty()){
            view.loadFalse();
        }else {
            view.loadListSuccessful(data);
        }
    }
}
