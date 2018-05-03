package edu.hust.truongvu.choviet.home;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.entity.Brand;
import edu.hust.truongvu.choviet.entity.PopularSearch;
import edu.hust.truongvu.choviet.entity.Shop;
import edu.hust.truongvu.choviet.model.BrandModel;
import edu.hust.truongvu.choviet.model.ProductModel;
import edu.hust.truongvu.choviet.model.ShopModel;

/**
 * Created by truon on 2/21/2018.
 */

public class HomePresenterImp implements HomePresenter {

    private HomeView homeView;
    private Context mContext;

    public HomePresenterImp(Context context, HomeView homeView){
        this.homeView = homeView;
        this.mContext = context;
    }

    @Override
    public void initBanner() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("banner 1", R.drawable.banner_one);
        hashMap.put("banner 2", R.drawable.banner_two);
        hashMap.put("banner 3", R.drawable.banner_three);
        hashMap.put("banner 4", R.drawable.banner_four);
        homeView.loadBanner(hashMap);
    }

    @Override
    public void initListBrand() {
        BrandModel brandModel = new BrandModel(mContext);
        ArrayList<Brand> listBrand = brandModel.getListBrand();
        homeView.loadBrand(listBrand);
    }

    @Override
    public void initListSearch() {
        ArrayList<PopularSearch> list = new ArrayList<>();
        list.add(new PopularSearch(0, "giày dép", R.drawable.giaydep));
        list.add(new PopularSearch(0, "quần áo", R.drawable.giaydep));
        list.add(new PopularSearch(0, "điện thoại", R.drawable.giaydep));
        list.add(new PopularSearch(0, "tai nghe", R.drawable.giaydep));
        list.add(new PopularSearch(0, "iphone", R.drawable.giaydep));
        list.add(new PopularSearch(0, "áo khoác", R.drawable.giaydep));
        list.add(new PopularSearch(0, "tivi", R.drawable.giaydep));
        list.add(new PopularSearch(0, "laptop", R.drawable.giaydep));
        homeView.loadListPopularSearch(list);
    }

    @Override
    public void initListStore() {
        ShopModel model = new ShopModel(mContext);
        ArrayList<Shop> listShop = model.getAllShop();

        homeView.loadListHighlightStore(listShop);
    }

    @Override
    public void initListProduct() {
        ProductModel model = new ProductModel(mContext);
        homeView.loadListHighlightProduct(model.getAllProduct());
    }

    @Override
    public void initListSuggest() {
        ProductModel model = new ProductModel(mContext);
        homeView.loadListSuggest(model.getAllProduct());
    }
}
