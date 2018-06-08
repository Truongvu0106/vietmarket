package edu.hust.truongvu.choviet.user.myshop;

import android.content.Context;

import edu.hust.truongvu.choviet.model.ProductModel;
import edu.hust.truongvu.choviet.model.ShopModel;
import edu.hust.truongvu.choviet.model.entity.Shop;

/**
 * Created by truon on 5/22/2018.
 */

public class MyShopPresenterImp implements MyShopPresenter{
    private Context mContext;
    private MyShopView shopView;
    private ShopModel shopModel;
    private ProductModel productModel;
    public MyShopPresenterImp(Context context, MyShopView view){
        this.mContext = context;
        this.shopView = view;
        shopModel = new ShopModel(mContext);
        productModel = new ProductModel(mContext);
    }

    @Override
    public void initShopInfor(int idUser) {
        Shop shop = shopModel.getShopByOwner(idUser);
        if (shop == null){
            shopView.loadShopFalse();
        }else {
            shopView.loadShopSuccessful(shop);
        }
    }

    @Override
    public int getNUmbserProductShop(int idShop) {
        return productModel.getProductByIdShop(idShop).size();
    }
}
