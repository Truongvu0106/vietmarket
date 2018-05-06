package edu.hust.truongvu.choviet.search;

import java.util.ArrayList;

/**
 * Created by truon on 5/6/2018.
 */

public interface SearchPresenter {
    void initListRecentSearch();
    void initAutoCompleteSearch();
    void onSearch(String txt);
    void addRecentSearch(String txt);
}
