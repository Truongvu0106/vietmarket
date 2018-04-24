package edu.hust.truongvu.choviet.payment.confirm;

import android.content.Context;

import edu.hust.truongvu.choviet.model.CartModel;

/**
 * Created by truon on 4/24/2018.
 */

public class ConfirmPresenterImp implements ConfirmPresenter{
    private Context context;
    private ConfirmView confirmView;
    private CartModel cartModel;
    public ConfirmPresenterImp(Context context, ConfirmView confirmView){
        this.context = context;
        this.confirmView = confirmView;
        cartModel = new CartModel();
    }

    @Override
    public void initView() {
        cartModel.openDatabase(context);
        confirmView.loadView(cartModel.getAllItemCart());
        cartModel.closeDatabse();
    }
}
