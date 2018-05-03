package edu.hust.truongvu.choviet.shop;

import android.content.Context;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.entity.Product;
import edu.hust.truongvu.choviet.entity.Shop;
import edu.hust.truongvu.choviet.model.ProductModel;

/**
 * Created by truon on 3/8/2018.
 */

public class ShopPresenterImp implements ShopPresenter {
    private ShopView shopView;
    private ProductModel productModel;
    private Context mContext;

    public ShopPresenterImp(Context context, ShopView shopView){
        this.shopView = shopView;
        this.mContext = context;
        productModel = new ProductModel(mContext);
    }


    @Override
    public void initListProduct(int idShop) {
        ArrayList<Product> data = new ArrayList<>();
        data = productModel.getProductByIdShop(idShop);
        if (data == null || data.size() == 0){
            shopView.loadListProductByShopFalse();
        }else {
            shopView.loadListProductByShopSuccessful(data);
        }
    }

    @Override
    public void initInforShop(Shop shop) {
        if (shop == null){
            shopView.loadInforShopFalse();
        }else {
            shopView.loadInforShopSuccessful(shop);
        }
    }
}
