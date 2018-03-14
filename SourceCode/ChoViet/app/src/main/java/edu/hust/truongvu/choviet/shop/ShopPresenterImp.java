package edu.hust.truongvu.choviet.shop;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.entity.Product;

/**
 * Created by truon on 3/8/2018.
 */

public class ShopPresenterImp implements ShopPresenter {
    private ShopView shopView;
    public ShopPresenterImp(ShopView shopView){
        this.shopView = shopView;
    }

    @Override
    public void initListProduct() {
        ArrayList<Product> data = new ArrayList<>();
        shopView.loadListProduct(data);
    }
}
