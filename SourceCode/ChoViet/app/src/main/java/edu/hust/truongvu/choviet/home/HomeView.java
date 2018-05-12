package edu.hust.truongvu.choviet.home;

import java.util.ArrayList;
import java.util.HashMap;

import edu.hust.truongvu.choviet.model.entity.Brand;
import edu.hust.truongvu.choviet.model.entity.PopularSearch;
import edu.hust.truongvu.choviet.model.entity.Product;
import edu.hust.truongvu.choviet.model.entity.Shop;

/**
 * Created by truon on 2/21/2018.
 */

public interface HomeView {
    void loadBannerSuccessful(HashMap<String, Integer> hashMap);
    void loadBannerFalse();

    void loadListBrandSuccessful(ArrayList<Brand> listBrand);
    void loadListBrandFalse();

    void loadListPopularSearchSuccessful(ArrayList<PopularSearch> listSearch);
    void loadListPopularSearchFalse();

    void loadListHighlightShopSuccessful(ArrayList<Shop> listShop);
    void loadListHighlightShopFalse();

    void loadListHighlightProductSuccessful(ArrayList<Product> listProduct);
    void loadListHighlightProductFalse();

    void loadListLastestProductSuccessful(ArrayList<Product> data);
    void loadListLastestProductFalse();

    void loadListSuggestProductSuccessful(ArrayList<Product> listProduct);
    void loadListSuggestProductFalse();
}
