package edu.hust.truongvu.choviet.shop.details_shop;

import android.content.Context;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.model.RateModel;
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
    private RateModel rateModel;
    private Context mContext;

    public ShopPresenterImp(Context context, ShopView shopView){
        this.shopView = shopView;
        this.mContext = context;
        productModel = new ProductModel(mContext);
        shopModel = new ShopModel(mContext);
        rateModel = new RateModel(mContext);
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

    @Override
    public void addUserRate(int idUser, int idShop, float rate) {
        if (rateModel.addRateShop(idShop, idUser, rate)){
            shopView.addUserRateSuccessful(rate);
        }else {
            shopView.addUserRateFalse();
        }
    }

    @Override
    public void updateUserRate(int idUser, int idShop, float rate) {
        if (rateModel.updateUserRateShop(idShop, idUser, rate)){
            shopView.updateUserRateSuccessful(rate);
        }else {
            shopView.updateUserRateFalse();
        }
    }

    @Override
    public void updateTotalRate(boolean isNew, int idShop, float rate) {
        float curTotalRate = 0;
        float total = 0;

        ArrayList<Float> listRate = rateModel.getListRateShop(idShop);
        total = listRate.size();
        if (total == 0){
            update(idShop, rate);
            return;
        }
        if (isNew){
            total += 1;
        }
        for (int i = 0; i < listRate.size(); i++){
            curTotalRate += listRate.get(i);
        }

        float newRate = curTotalRate/total;
        update(idShop, newRate);
    }

    private void update(int idShop, float rate){
        if (shopModel.updateTotalRateShop(idShop, rate)){
            Shop shop = shopModel.getShopById(idShop);
            if (shop == null){
                shopView.updateTotalRateFalse();
            }else {
                shopView.updateTotalRateSuccessful(rate);
            }
        }else {
            shopView.updateTotalRateFalse();
        }
    }

    @Override
    public float getRateByUserAndShop(int idUser, int idShop){
        return rateModel.getRateByShopAndUser(idShop, idUser);
    }

    @Override
    public int getNumberProductInShop(int idShop){
        return productModel.getProductByIdShop(idShop).size();
    }
}
