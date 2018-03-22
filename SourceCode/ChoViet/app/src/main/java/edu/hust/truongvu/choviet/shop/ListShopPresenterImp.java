package edu.hust.truongvu.choviet.shop;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.entity.Shop;

/**
 * Created by truon on 3/7/2018.
 */

public class ListShopPresenterImp implements ListShopPresenter{
    private ListShopView listShopView;

    public ListShopPresenterImp(ListShopView listShopView){
        this.listShopView = listShopView;
    }

    @Override
    public void initListShop() {
        ShopModel model = new ShopModel();
        ArrayList<Shop> data = model.getAllShop();
        listShopView.loadListShop(data);
    }
}
