package edu.hust.truongvu.choviet.order.confirm;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.model.ProductModel;
import edu.hust.truongvu.choviet.model.PromotionModel;
import edu.hust.truongvu.choviet.model.entity.Order;
import edu.hust.truongvu.choviet.model.entity.OrderDetails;
import edu.hust.truongvu.choviet.model.entity.PayMethod;
import edu.hust.truongvu.choviet.model.entity.Product;
import edu.hust.truongvu.choviet.model.entity.Promotion;
import edu.hust.truongvu.choviet.model.entity.Transport;
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
    private ProductModel productModel;
    private PromotionModel promotionModel;


    public ConfirmPresenterImp(Context context, ConfirmView confirmView){
        this.context = context;
        this.confirmView = confirmView;
        cartModel = new CartModel();
        transportModel = new TransportModel(context);
        payMethodModel = new PayMethodModel(context);
        orderModel = new OrderModel(context);
        productModel = new ProductModel(context);
        promotionModel = new PromotionModel(context);
    }

    @Override
    public void initView(String name, String phone, String address, int idTransport, int idPayMethod) {
        cartModel.openDatabase(context);
        ArrayList<Product> listProduct = new ArrayList<>();
        listProduct = cartModel.getAllItemCart();
        cartModel.closeDatabse();
        PayMethod payMethod = payMethodModel.getPayMethodById(idPayMethod);
        Log.e("id_transport_presenter", idTransport + "");
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
            confirmView.loadViewSuccess(transport, payMethod, name, phone, address, listProduct);
        }
    }

    @Override
    public void confirmOrder(Order order) {
        Log.e("user_order", order.getIdUser() + "");

        if (orderModel.insertOrder(order)){
            confirmView.addOrderSuccessful();
            cartModel.openDatabase(context);
            if (cartModel.deleteAllItemCart()){
                Log.e("cart", "delete all successful");
            }else {
                Log.e("cart", "delete all false");
            }
            cartModel.closeDatabse();
        }else {
            confirmView.addOrderFalse();
        }
    }

    @Override
    public void updateStockProduct(ArrayList<OrderDetails> data) {
        Log.e("tr", "update stock");
        if (data == null || data.isEmpty()){
            confirmView.updateStockFalse();
            return;
        }
        boolean isSuccessful = true;
        for (OrderDetails details : data){
            Product mProduct = productModel.getProductById(details.getIdProduct());
            int newStock = mProduct.getAmount() - details.getNumber();
            if(productModel.updateStockProduct(details.getIdProduct(), newStock)){
                isSuccessful = true;
            }else {
                isSuccessful = false;
                break;
            }
        }

        if (isSuccessful){
            confirmView.updateStockSuccessful();
        }else {
            confirmView.updateStockFalse();
        }
    }

    @Override
    public void applyPromotion(String code) {
        Promotion promotion = promotionModel.getPromotionByCode(code);

        if (promotion == null){
            confirmView.applyPromotionFalse(context.getString(R.string.code_invalid));
            return;
        }

        Date date = new Date();
        if (promotion.getNumber() <= 0){
            confirmView.applyPromotionFalse(context.getString(R.string.code_invalid));
        }else if (promotion.getStart() > date.getTime()){
            confirmView.applyPromotionFalse(context.getString(R.string.code_invalid));
        }else if (promotion.getEnd() < date.getTime()){
            confirmView.applyPromotionFalse(context.getString(R.string.code_overdue));
        }else {
            confirmView.applyPromotionSuccessful(promotion.getAmount());
            promotionModel.updateNumber(promotion.getId(), promotion.getNumber() - 1);
        }
    }
}
