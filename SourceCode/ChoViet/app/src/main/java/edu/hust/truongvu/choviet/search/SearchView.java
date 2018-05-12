package edu.hust.truongvu.choviet.search;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.model.entity.PopularSearch;
import edu.hust.truongvu.choviet.model.entity.Product;

/**
 * Created by truon on 5/6/2018.
 */

public interface SearchView {
    void loadRecentSearchsSuccessful(ArrayList<String> data);
    void loadRecentSearchFalse();

    void searchFound(ArrayList<Product> products);
    void searchNotFound();

    void addRecentSearchSuccessful();
    void addRecentSearchFalse();

    void loadPopularSearchSuccessful(ArrayList<PopularSearch> data);
    void loadPopularSearchFalse();
}
