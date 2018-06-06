package edu.hust.truongvu.choviet.user.info_user;

import android.content.Context;

import edu.hust.truongvu.choviet.model.CartModel;

/**
 * Created by truon on 6/4/2018.
 */

public class InfoUserPresenterImp implements InfoUserPresenter{
    private Context mContext;
    private CartModel cartModel;
    private InfoUserView view;
    public InfoUserPresenterImp(Context context, InfoUserView view){
        this.mContext = context;
        this.view = view;
        cartModel = new CartModel();
    }
    @Override
    public void signout() {
        cartModel.openDatabase(mContext);
        if (cartModel.deleteAllItemCart()){
            view.signoutSuccessful();
        }else {
            view.signoutFalse();
        }
        cartModel.closeDatabse();
    }
}
