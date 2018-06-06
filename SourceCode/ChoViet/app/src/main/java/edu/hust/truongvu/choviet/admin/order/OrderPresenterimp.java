package edu.hust.truongvu.choviet.admin.order;

import android.content.Context;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.model.OrderModel;
import edu.hust.truongvu.choviet.model.entity.Order;

/**
 * Created by truon on 6/4/2018.
 */

public class OrderPresenterimp implements OrderPresenter{
    private Context mContext;
    private OrderView view;
    private OrderModel model;
    public OrderPresenterimp(Context context, OrderView view){
        this.mContext = context;
        this.view = view;
        model = new OrderModel(mContext);
    }

    @Override
    public void initListOrder() {
        ArrayList<Order> data = model.getAllOrder();
        if (data == null || data.isEmpty()){
            view.loadOrderFalse();
        }else {
            view.loadOrdersSuccessful(data);
        }
    }

    @Override
    public void updateStatusOrder(int id, int status) {
        if (model.updateStatusOrder(id, status)){
            view.updateSuccessful();
        }else {
            view.updateFalse();
        }
    }

    @Override
    public void deleteOrder(int id) {
        if (model.deleteOrder(id)){
            view.deleteSuccessful();
        }else {
            view.deleteFalse();
        }
    }
}
