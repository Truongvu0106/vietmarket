package edu.hust.truongvu.choviet.user.myshop.registershop;

import android.content.Context;

import edu.hust.truongvu.choviet.model.ShopModel;
import edu.hust.truongvu.choviet.model.entity.Shop;

/**
 * Created by truon on 5/18/2018.
 */

public class RegisterPresenterImp implements RegisterPresenter{
    private ShopModel shopModel;
    private RegisterView view;
    private Context mContext;
    public RegisterPresenterImp(Context context, RegisterView view){
        this.mContext = context;
        this.view = view;
        shopModel = new ShopModel(mContext);
    }

    @Override
    public void register(Shop shop) {
        if (shopModel.registerShop(shop)){
            view.resgisterSuccessful();
        }else {
            view.registerFalse();
        }
    }
}
