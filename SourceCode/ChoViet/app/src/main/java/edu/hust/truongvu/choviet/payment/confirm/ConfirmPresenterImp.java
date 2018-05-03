package edu.hust.truongvu.choviet.payment.confirm;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.entity.Order;
import edu.hust.truongvu.choviet.entity.PayMethod;
import edu.hust.truongvu.choviet.entity.Product;
import edu.hust.truongvu.choviet.entity.Transport;
import edu.hust.truongvu.choviet.model.CartModel;
import edu.hust.truongvu.choviet.model.OrderModel;
import edu.hust.truongvu.choviet.model.PayMethodModel;
import edu.hust.truongvu.choviet.model.TransportModel;

/**
 * Created by truon on 4/24/2018.
 */

public class ConfirmPresenterImp implements ConfirmPresenter{
    private Context context;
    private ConfirmView confirmView;
    private CartModel cartModel;
    private TransportModel transportModel;
    private PayMethodModel payMethodModel;
    private OrderModel orderModel;


    public ConfirmPresenterImp(Context context, ConfirmView confirmView){
        this.context = context;
        this.confirmView = confirmView;
        cartModel = new CartModel();
        transportModel = new TransportModel(context);
        payMethodModel = new PayMethodModel(context);
        orderModel = new OrderModel(context);
    }

    @Override
    public void initView(String address, int idTransport, int idPayMethod) {
        cartModel.openDatabase(context);
        ArrayList<Product> listProduct = new ArrayList<>();
        listProduct = cartModel.getAllItemCart();
        cartModel.closeDatabse();
        PayMethod payMethod = payMethodModel.getPayMethodById(idPayMethod);
        Transport transport = transportModel.getTransportById(idTransport);
        if (payMethod == null || transport == null){
            if (payMethod == null){
                Log.e("err", "paymethod null");
            }

            if (transport == null){
                Log.e("err", "transport null");
            }
            confirmView.loadViewFalse();
        }else {
            confirmView.loadViewSuccess(transport, payMethod, address, listProduct);
        }
    }

    @Override
    public void confirmOrder(Order order) {
        if (orderModel.insertOrder(order)){
            confirmView.addOrderSuccessful();
        }else {
            confirmView.addOrderFalse();
        }
    }
}
