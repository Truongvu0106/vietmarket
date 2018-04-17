package edu.hust.truongvu.choviet.shop;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.entity.Product;
import edu.hust.truongvu.choviet.model.ProductModel;

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
        ProductModel model = new ProductModel();
        ArrayList<Product> data = model.getAllProduct();
        shopView.loadListProduct(data);
    }
}
