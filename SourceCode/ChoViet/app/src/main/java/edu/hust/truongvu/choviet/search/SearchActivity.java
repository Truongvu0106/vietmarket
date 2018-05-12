package edu.hust.truongvu.choviet.search;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.product.list_product.ProductAdapter;
import edu.hust.truongvu.choviet.model.entity.PopularSearch;
import edu.hust.truongvu.choviet.model.entity.Product;
import edu.hust.truongvu.choviet.product.details_product.ProductActivity;
import edu.hust.truongvu.choviet.helper.Constants;

public class SearchActivity extends AppCompatActivity implements SearchView, View.OnClickListener{

    private RecyclerView recyclerListRecent, recyclerListProduct;
    private RecentSearchAdapter recentSearchAdapter;
    private ProductAdapter productAdapter;
    private CustomAutoCompleteAdapter customAutoCompleteAdapter;
    private View btnBack, btnSearch, layoutErr;
    private SearchPresenterImp searchPresenterImp;
    private AutoCompleteTextView edtSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
    }

    private void initView(){
        recyclerListRecent = findViewById(R.id.list_recent_search);
        recyclerListProduct = findViewById(R.id.list_search_result);
        layoutErr = findViewById(R.id.layout_err);
        btnSearch = findViewById(R.id.btn_search);
        btnBack = findViewById(R.id.btn_back);
        edtSearch = findViewById(R.id.edt_search);

        btnBack.setOnClickListener(this);
        btnSearch.setOnClickListener(this);

        searchPresenterImp = new SearchPresenterImp(this, this);
        searchPresenterImp.initListRecentSearch();
        searchPresenterImp.initAutoCompleteSearch();
    }

    @Override
    public void loadRecentSearchsSuccessful(ArrayList<String> data) {
        recentSearchAdapter = new RecentSearchAdapter(this, data, new RecentSearchAdapter.RecentSearchListener() {
            @Override
            public void onResults(String search) {
                searchPresenterImp.onSearch(search);
            }
        });
        recyclerListRecent.setLayoutManager(new LinearLayoutManager(this));
        recyclerListRecent.setAdapter(recentSearchAdapter);
    }

    @Override
    public void loadRecentSearchFalse() {

    }

    @Override
    public void searchFound(ArrayList<Product> products) {
        productAdapter = new ProductAdapter(this, products, new ProductAdapter.ProductListener() {
            @Override
            public void onProductResult(Product product) {
                Intent intent = new Intent(SearchActivity.this, ProductActivity.class);
                intent.putExtra(Constants.MyTag.INTENT_PRODUCT, product);
                startActivity(intent);
            }

            @Override
            public void onLikeClick(int idUser, Product product) {

            }

            @Override
            public void onUnlikeClick(int idUser, Product product) {

            }
        });
        recyclerListProduct.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));
        recyclerListProduct.setAdapter(productAdapter);
    }

    @Override
    public void searchNotFound() {
        recyclerListProduct.setVisibility(View.GONE);
        layoutErr.setVisibility(View.VISIBLE);
    }

    @Override
    public void addRecentSearchSuccessful() {
        Log.e("recent_search", "add successful");
    }

    @Override
    public void addRecentSearchFalse() {
        Log.e("recent_search", "add false");
    }

    @Override
    public void loadPopularSearchSuccessful(ArrayList<PopularSearch> data) {
        customAutoCompleteAdapter = new CustomAutoCompleteAdapter(this, data);
        edtSearch.setAdapter(customAutoCompleteAdapter);
    }

    @Override
    public void loadPopularSearchFalse() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_search:
                String search = edtSearch.getText().toString();
                if (search.matches("")){
                    Toast.makeText(this, getString(R.string.empty_content), Toast.LENGTH_SHORT).show();
                }else {
                    searchPresenterImp.onSearch(search);
                    searchPresenterImp.addRecentSearch(search);
                }
                break;
            case R.id.btn_back:
                onBackPressed();
                break;
            default:
                break;
        }
    }
}
