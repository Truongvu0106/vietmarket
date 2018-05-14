package edu.hust.truongvu.choviet.shop.details_shop;

import android.content.Context;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.model.ShopModel;
import edu.hust.truongvu.choviet.model.entity.Product;
import edu.hust.truongvu.choviet.model.entity.Shop;
import edu.hust.truongvu.choviet.model.ProductModel;

/**
 * Created by truon on 3/8/2018.
 */

public class ShopPresenterImp implements ShopPresenter {
    private ShopView shopView;
    private ProductModel productModel;
    private ShopModel shopModel;
    private Context mContext;

    public ShopPresenterImp(Context context, ShopView shopView){
        this.shopView = shopView;
        this.mContext = context;
        productModel = new ProductModel(mContext);
        shopModel = new ShopModel(mContext);
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

    @Override
    public void checkFollowing(int idUser, int idShop) {
        boolean isFollowing = shopModel.isFollowing(idShop, idUser);
        shopView.initFollow(isFollowing);
    }

    @Override
    public void follow(int idUser, int idShop) {
        if (shopModel.follow(idShop, idUser)){
            shopView.followSuccessful();
        }else {
            shopView.followFalse();
        }
    }

    @Override
    public void unFollow(int idUser, int idShop) {
        if (shopModel.unFollow(idShop, idUser)){
            shopView.unFollowSuccessful();
        }else {
            shopView.unFollowFalse();
        }
    }
}
