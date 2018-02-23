package edu.hust.truongvu.choviet.home;

import java.util.ArrayList;
import java.util.HashMap;

import edu.hust.truongvu.choviet.entity.Brand;
import edu.hust.truongvu.choviet.entity.PopularSearch;
import edu.hust.truongvu.choviet.entity.Product;
import edu.hust.truongvu.choviet.entity.Store;

/**
 * Created by truon on 2/21/2018.
 */

public interface HomeView {
    void loadBanner(HashMap<String, Integer> hashMap);
    void loadBrand(ArrayList<Brand> listBrand);
    void loadListPopularSearch(ArrayList<PopularSearch> listSearch);
    void loadListHighlightStore(ArrayList<Store> listStore);
    void loadListHighlightProduct(ArrayList<Product> listProduct);
    void loadListSuggest(ArrayList<Product> listProduct);
}
