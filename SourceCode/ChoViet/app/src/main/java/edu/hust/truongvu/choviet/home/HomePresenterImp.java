package edu.hust.truongvu.choviet.home;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.model.entity.Brand;
import edu.hust.truongvu.choviet.model.entity.PopularSearch;
import edu.hust.truongvu.choviet.model.entity.Product;
import edu.hust.truongvu.choviet.model.entity.Shop;
import edu.hust.truongvu.choviet.model.BrandModel;
import edu.hust.truongvu.choviet.model.PopularSearchModel;
import edu.hust.truongvu.choviet.model.ProductModel;
import edu.hust.truongvu.choviet.model.ShopModel;

/**
 * Created by truon on 2/21/2018.
 */

public class HomePresenterImp implements HomePresenter {

    private HomeView homeView;
    private Context mContext;
    private BrandModel brandModel;
    private ShopModel shopModel;
    private ProductModel productModel;
    private PopularSearchModel popularSearchModel;
    public HomePresenterImp(Context context, HomeView homeView){
        this.homeView = homeView;
        this.mContext = context;
        brandModel = new BrandModel(mContext);
        shopModel = new ShopModel(mContext);
        productModel = new ProductModel(mContext);
        popularSearchModel = new PopularSearchModel(mContext);
    }

    @Override
    public void initBanner() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("banner 1", R.drawable.banner_one);
        hashMap.put("banner 2", R.drawable.banner_two);
        hashMap.put("banner 3", R.drawable.banner_three);
        hashMap.put("banner 4", R.drawable.banner_four);
        homeView.loadBannerSuccessful(hashMap);
    }

    @Override
    public void initListBrand() {
        ArrayList<Brand> listBrand = brandModel.getListBrand();
        if (listBrand == null || listBrand.size() == 0){
            homeView.loadListBrandFalse();
        }else {
            homeView.loadListBrandSuccessful(listBrand);
        }
    }

    @Override
    public void initListSearch() {
        ArrayList<PopularSearch> list = popularSearchModel.getAllPopularSearch();
        if (list == null || list.size() == 0){
            homeView.loadListPopularSearchFalse();
        }else {
            homeView.loadListPopularSearchSuccessful(list);
        }
    }

    @Override
    public void initListHighlightShop() {
        ArrayList<Shop> listShop = shopModel.getAllShop();
        if (listShop == null || listShop.size() == 0){
            homeView.loadListHighlightShopFalse();
        }else {
            homeView.loadListHighlightShopSuccessful(listShop);
        }
    }

    @Override
    public void initListHighlightProduct() {
        ArrayList<Product> data = productModel.getProductHighlight();
        if (data == null || data.size() == 0){
            homeView.loadListHighlightProductFalse();
        }else {
            homeView.loadListHighlightProductSuccessful(data);
        }
    }

    @Override
    public void initListLastestProduct() {
        ArrayList<Product> data = productModel.getProductLastest();
        if (data == null || data.size() == 0){
            homeView.loadListLastestProductFalse();
        }else {
            homeView.loadListLastestProductSuccessful(data);
        }
    }

    @Override
    public void initListSuggest() {
        ArrayList<Product> data = productModel.getAllProduct();
        if (data == null || data.size() == 0){
            homeView.loadListSuggestProductFalse();
        }else {
            homeView.loadListSuggestProductSuccessful(data);
        }
    }
}
