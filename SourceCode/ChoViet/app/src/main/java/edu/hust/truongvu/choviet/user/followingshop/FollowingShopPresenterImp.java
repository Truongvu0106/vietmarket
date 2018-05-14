package edu.hust.truongvu.choviet.user.followingshop;

import android.content.Context;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.model.ShopModel;
import edu.hust.truongvu.choviet.model.entity.Shop;

/**
 * Created by truon on 5/14/2018.
 */

public class FollowingShopPresenterImp implements FollowingShopPresenter{
    private Context mContext;
    private FollowingShopView shopView;
    private ShopModel shopModel;
    public FollowingShopPresenterImp(Context context, FollowingShopView view){
        this.mContext = context;
        this.shopView = view;
        shopModel = new ShopModel(mContext);
    }
    @Override
    public void initListShop(int idUser) {
        ArrayList<Shop> data = new ArrayList<>();
        data = shopModel.getListShopFollowing(idUser);
        if (data == null || data.size() == 0){
            shopView.loadListShopFalse();
        }else {
            shopView.loadListShopSuccessful(data);
        }
    }
}
