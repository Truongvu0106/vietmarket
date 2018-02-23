package edu.hust.truongvu.choviet.home;

import java.util.ArrayList;
import java.util.HashMap;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.entity.Brand;
import edu.hust.truongvu.choviet.entity.PopularSearch;
import edu.hust.truongvu.choviet.entity.Product;
import edu.hust.truongvu.choviet.entity.Store;

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
        ArrayList<Brand> listBrand = new ArrayList<>();
        listBrand.add(new Brand(0, "Canon", R.drawable.canon));
        listBrand.add(new Brand(0, "Nikon", R.drawable.nikon));
        listBrand.add(new Brand(0, "Hp", R.drawable.hp));
        listBrand.add(new Brand(0, "Asus", R.drawable.asus));
        listBrand.add(new Brand(0, "Canon", R.drawable.canon));
        listBrand.add(new Brand(0, "Nikon", R.drawable.nikon));
        listBrand.add(new Brand(0, "Hp", R.drawable.hp));
        listBrand.add(new Brand(0, "Asus", R.drawable.asus));
        listBrand.add(new Brand(0, "Canon", R.drawable.canon));
        listBrand.add(new Brand(0, "Nikon", R.drawable.nikon));
        listBrand.add(new Brand(0, "Hp", R.drawable.hp));
        listBrand.add(new Brand(0, "Asus", R.drawable.asus));
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
        ArrayList<Store> listStore = new ArrayList<>();
        homeView.loadListHighlightStore(listStore);
    }

    @Override
    public void initListProduct() {
        ArrayList<Product> listProduct = new ArrayList<>();
        listProduct.add(new Product(0, "giày dép", R.drawable.giaydep, 20000, 25, false, 4));
        listProduct.add(new Product(0, "quần áo", R.drawable.giaydep, 20000, 25, false, 4));
        listProduct.add(new Product(0, "áo khoác", R.drawable.giaydep, 20000, 25, false, 4));
        listProduct.add(new Product(0, "máy tính", R.drawable.giaydep, 20000, 25, false, 4));
        listProduct.add(new Product(0, "điện thoại", R.drawable.giaydep, 20000, 25, false, 4));
        listProduct.add(new Product(0, "laptop", R.drawable.giaydep, 20000, 25, false, 4));
        listProduct.add(new Product(0, "máy ảnh", R.drawable.giaydep, 20000, 25, false, 4));
        listProduct.add(new Product(0, "tai nghe", R.drawable.giaydep, 20000, 25, false, 4));
        listProduct.add(new Product(0, "chuột", R.drawable.giaydep, 20000, 25, false, 4));
        listProduct.add(new Product(0, "bàn phím", R.drawable.giaydep, 20000, 25, false, 4));
        listProduct.add(new Product(0, "xe máy", R.drawable.giaydep, 20000, 25, false, 4));
        homeView.loadListHighlightProduct(listProduct);
    }

    @Override
    public void initListSuggest() {
        ArrayList<Product> listProduct = new ArrayList<>();
        homeView.loadListSuggest(listProduct);
    }
}
