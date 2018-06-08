package edu.hust.truongvu.choviet.home;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.helper.Constants;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.model.BannerModel;
import edu.hust.truongvu.choviet.model.OrderModel;
import edu.hust.truongvu.choviet.model.entity.Banner;
import edu.hust.truongvu.choviet.model.entity.Brand;
import edu.hust.truongvu.choviet.model.entity.Order;
import edu.hust.truongvu.choviet.model.entity.OrderDetails;
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
    private OrderModel orderModel;
    private BannerModel bannerModel;
    private PopularSearchModel popularSearchModel;
    public HomePresenterImp(Context context, HomeView homeView){
        this.homeView = homeView;
        this.mContext = context;
        brandModel = new BrandModel(mContext);
        shopModel = new ShopModel(mContext);
        productModel = new ProductModel(mContext);
        popularSearchModel = new PopularSearchModel(mContext);
        orderModel = new OrderModel(mContext);
        bannerModel = new BannerModel(mContext);
    }

    @Override
    public void initBanner() {
//        HashMap<String, Integer> hashMap = new HashMap<>();
//        hashMap.put("banner 1", R.drawable.banner_one);
//        hashMap.put("Thế giới công nghệ", R.drawable.banner_two);
//        hashMap.put("banner 3", R.drawable.banner_three);
//        hashMap.put("banner 4", R.drawable.banner_four);
        ArrayList<Banner> data = bannerModel.getAllBanner();
        homeView.loadBannerSuccessful(data);
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

    public void initListBrand(ArrayList<Brand> data){
        if (data == null || data.size() == 0){
            homeView.loadListBrandFalse();
        }else {
            homeView.loadListBrandSuccessful(data);
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

    public void initListHighLightShop(ArrayList<Shop> data){
        if (data == null || data.size() == 0){
            homeView.loadListHighlightShopFalse();
        }else {
            homeView.loadListHighlightShopSuccessful(data);
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
        ArrayList<Product> data = suggestProduct();
        if (data == null || data.size() == 0){
            homeView.loadListSuggestProductFalse();
        }else {
            homeView.loadListSuggestProductSuccessful(data);
        }
    }

    private ArrayList<Product> suggestProduct(){
        if (MyHelper.getCurrentUser(mContext) == null){
            return getDefaultSuggest();
        }else {
            int id = MyHelper.getUserIdPreference(mContext);
            if (isHasOrders() && isHasWishlist()){
                ArrayList<Product> wishList = productModel.getListWishlist(id);
                ArrayList<Product> list1 = productModel.getProductByCategory(wishList.get(0).getTypeProduct());

                ArrayList<Order> listOrder = orderModel.getOrderByUser(id, -1);
                ArrayList<OrderDetails> listOrderDetails = orderModel.getDetailsOrderById(listOrder.get(0).getId());

                Product product = productModel.getProductById(listOrderDetails.get(0).getIdProduct());
                ArrayList<Product> list2 = productModel.getProductByCategory(product.getId());

                ArrayList<Product> results = new ArrayList<>();
                for (Product product1 : list1){
                    results.add(product1);
                }

                for (Product product2 : list2){
                    results.add(product2);
                }

                if (results.size() == 0){
                    return getDefaultSuggest();
                }else {
                    return results;
                }
            }else if (isHasWishlist()){
                ArrayList<Product> wishList = productModel.getListWishlist(id);
                ArrayList<Product> list1 = productModel.getProductByCategory(wishList.get(0).getTypeProduct());
                return list1;
            }else if (isHasOrders()){
                ArrayList<Order> listOrder = orderModel.getOrderByUser(id, -1);
                ArrayList<OrderDetails> listOrderDetails = orderModel.getDetailsOrderById(listOrder.get(0).getId());

                Product product = productModel.getProductById(listOrderDetails.get(0).getIdProduct());
                ArrayList<Product> list2 = productModel.getProductByCategory(product.getId());

                return list2;
            }else {
                return getDefaultSuggest();
            }
        }
    }

    private ArrayList<Product> getDefaultSuggest(){
        ArrayList<Product> list1 = productModel.getProductByCategory(1);
        ArrayList<Product> list2 = productModel.getProductByCategory(2);
        ArrayList<Product> results = new ArrayList<>();
        for (Product product : list1){
            results.add(product);
        }

        for (Product product : list2){
            results.add(product);
        }

        return results;
    }

    private boolean isHasWishlist(){
        int idUser = MyHelper.getUserIdPreference(mContext);
        if (idUser == 0){
            return false;
        }else {
            ArrayList<Product> wishList = productModel.getListWishlist(idUser);
            if (wishList == null || wishList.size() == 0){
                return false;
            }else {
                return true;
            }
        }
    }

    private boolean isHasOrders(){
        int idUser = MyHelper.getUserIdPreference(mContext);
        if (idUser == 0){
            return false;
        }else {
            ArrayList<Order> listOrder = orderModel.getOrderByUser(idUser, -1);
            if (listOrder == null || listOrder.size() == 0){
                return false;
            }else {
                return true;
            }
        }
    }
}
