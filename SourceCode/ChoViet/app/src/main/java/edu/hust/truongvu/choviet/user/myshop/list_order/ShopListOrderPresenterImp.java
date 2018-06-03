package edu.hust.truongvu.choviet.user.myshop.list_order;

import android.content.Context;
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
        ArrayList<OrderDetails> orders = orderModel.getDetailsOrderByShop(idShop);

    }

    @Override
    public void updateStatus(Order order, Constants.OrderStatus status) {

    }

    @Override
    public void deleteOrder(int idOrder) {

    }
}
