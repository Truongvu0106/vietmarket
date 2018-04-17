package edu.hust.truongvu.choviet.cart;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.entity.Product;
import edu.hust.truongvu.choviet.model.CartModel;

/**
 * Created by truon on 2/27/2018.
 */

public class CartPresenterImp implements CartPresenter {
    private CartView cartView;
    private CartModel cartModel;
    public CartPresenterImp(){
        cartModel = new CartModel();
    }

    public CartPresenterImp(CartView cartView){
        this.cartView = cartView;
        cartModel = new CartModel();
    }
    @Override
    public void initListItemCart(Context context) {
        cartModel.openDatabase(context);
        ArrayList<Product> list = cartModel.getAllItemCart();
        cartView.loadCartItem(list);
        cartModel.closeDatabse();
    }

    @Override
    public void deleteItemCart(Context context, int id_product) {
        cartModel.openDatabase(context);
        if (cartModel.deleteItemCart(id_product)){
            cartView.deleteSuccessful();
        }else {
            cartView.deleteFalse();
        }
        cartModel.closeDatabse();
    }

    @Override
    public void updateNumberSelect(Context context, int id_product, int number) {
        cartModel.openDatabase(context);
        if (cartModel.updateNumberOfItem(id_product, number)){
            Log.e("update", "true");
        }else {
            Log.e("update", "false");
        }
        cartModel.closeDatabse();
    }

    @Override
    public void calculateTotal(Context context) {
        cartModel.openDatabase(context);
        ArrayList<Product> data = cartModel.getAllItemCart();
        long totalMoney = 0;
        for (int i = 0; i < data.size(); i++){
            Product product = data.get(i);

            long oldPrice = product.getPrice();
            totalMoney += (oldPrice - oldPrice*product.getDiscount()/100)*product.getNumberSelect();
        }
        cartView.updateTotalMoney(totalMoney);
    }

    @Override
    public int getNumberItemCart(Context context) {
        CartModel cartModel = new CartModel();
        cartModel.openDatabase(context);
        int number = cartModel.getAllItemCart().size();
        cartModel.closeDatabse();
        return number;
    }
}
