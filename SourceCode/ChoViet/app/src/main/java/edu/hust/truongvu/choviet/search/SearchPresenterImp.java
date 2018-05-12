package edu.hust.truongvu.choviet.search;

import android.content.Context;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.model.entity.PopularSearch;
import edu.hust.truongvu.choviet.model.entity.Product;
import edu.hust.truongvu.choviet.model.PopularSearchModel;
import edu.hust.truongvu.choviet.model.ProductModel;
import edu.hust.truongvu.choviet.model.RecentSearchModel;

/**
 * Created by truon on 5/6/2018.
 */

public class SearchPresenterImp implements SearchPresenter{
    private SearchView searchView;
    private ProductModel productModel;
    private RecentSearchModel recentSearchModel;
    private PopularSearchModel popularSearchModel;
    private Context mContext;
    public SearchPresenterImp(Context context, SearchView searchView){
        this.mContext = context;
        this.searchView = searchView;
        recentSearchModel = new RecentSearchModel();
        productModel = new ProductModel(mContext);
        popularSearchModel = new PopularSearchModel(mContext);
    }

    @Override
    public void initListRecentSearch() {
        recentSearchModel.openDatabase(mContext);
        ArrayList<String> data = recentSearchModel.getListRecentSearch();
        if (data == null || data.size() == 0){
            searchView.loadRecentSearchFalse();
        }else {
            searchView.loadRecentSearchsSuccessful(data);
        }
        recentSearchModel.closeDatabse();
    }

    @Override
    public void initAutoCompleteSearch() {
        ArrayList<PopularSearch> data = popularSearchModel.getAllPopularSearch();
        if (data == null || data.size() == 0){
            searchView.loadPopularSearchFalse();
        }else {
            searchView.loadPopularSearchSuccessful(data);
        }
    }

    @Override
    public void onSearch(String txt) {
        ArrayList<Product> data = productModel.searchProductByName(txt);
        if (data == null || data.size() == 0){
            searchView.searchNotFound();
        }else {
            searchView.searchFound(data);
        }
    }

    @Override
    public void addRecentSearch(String txt) {
        recentSearchModel.openDatabase(mContext);
        if (recentSearchModel.addRecentSearch(txt)){
            searchView.addRecentSearchSuccessful();
        }else {
            searchView.addRecentSearchFalse();
        }
        recentSearchModel.closeDatabse();
    }
}
