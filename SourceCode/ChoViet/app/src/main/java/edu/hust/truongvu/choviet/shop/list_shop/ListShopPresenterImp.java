package edu.hust.truongvu.choviet.shop.list_shop;

import android.content.Context;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.model.entity.Shop;
import edu.hust.truongvu.choviet.model.ShopModel;

/**
 * Created by truon on 3/7/2018.
 */

public class ListShopPresenterImp implements ListShopPresenter{
    private ListShopView listShopView;
    private Context mContext;

    public ListShopPresenterImp(Context context, ListShopView listShopView){
        this.listShopView = listShopView;
        this.mContext = context;
    }

    @Override
    public void initListShop() {
        ShopModel model = new ShopModel(mContext);
        ArrayList<Shop> data = model.getAllShop();
        listShopView.loadListShop(data);
    }
}
