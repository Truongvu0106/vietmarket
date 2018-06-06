package edu.hust.truongvu.choviet.user.myshop.list_order;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.helper.Constants;
import edu.hust.truongvu.choviet.model.OrderModel;
import edu.hust.truongvu.choviet.model.entity.Order;
import edu.hust.truongvu.choviet.model.entity.OrderDetails;

/**
 * Created by truon on 5/9/2018.
 */

public class ShopListOrderPresenterImp implements ShopListOrderPresenter {
    private OrderModel orderModel;
    private ShopListOrderView view;
    private Context mContext;
    public ShopListOrderPresenterImp(Context context, ShopListOrderView view){
        this.mContext = context;
        this.view = view;
        orderModel = new OrderModel(context);
    }

    @Override
    public void initListOrder(int idShop) {
        Log.e("idShop", idShop + "");
        ArrayList<OrderDetails> orders = orderModel.getDetailsOrderByShop(idShop);
        if (orders == null || orders.isEmpty()){
            view.loadListOrderFalse();
        }else {
            view.loadListOrderSuccessful(orders);
        }
    }

    @Override
    public void updateStatus(int idOrder, int status) {
        if (orderModel.updateStatusOrderDetails(idOrder, status)){
            view.updateSuccessful();
        }else {
            view.updateFalse();
        }
    }

    @Override
    public void deleteOrder(int idOrder) {
        if (orderModel.deleteOrderDetails(idOrder)){
            view.deleteSuccessful();
        }else {
            view.deleteFalse();
        }
    }
}
