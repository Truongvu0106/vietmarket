package edu.hust.truongvu.choviet.home;

import java.util.ArrayList;
import java.util.HashMap;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.entity.Brand;
import edu.hust.truongvu.choviet.entity.PopularSearch;
import edu.hust.truongvu.choviet.entity.Product;
import edu.hust.truongvu.choviet.entity.Shop;
import edu.hust.truongvu.choviet.product.ProductModel;
import edu.hust.truongvu.choviet.shop.ShopModel;

/**
 * Created by truon on 2/21/2018.
 */

public class HomePresenterImp implements HomePresenter {

    private HomeView homeView;

    public HomePresenterImp(HomeView homeView){
        this.homeView = homeView;
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
        BrandModel brandModel = new BrandModel();
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
        ShopModel model = new ShopModel();
        ArrayList<Shop> listShop = model.getAllShop();

        homeView.loadListHighlightStore(listShop);
    }

    @Override
    public void initListProduct() {
        ProductModel model = new ProductModel();
        homeView.loadListHighlightProduct(model.getAllProduct());
    }

    @Override
    public void initListSuggest() {
        ProductModel model = new ProductModel();
        homeView.loadListSuggest(model.getAllProduct());
    }
}
