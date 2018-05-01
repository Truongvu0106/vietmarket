package edu.hust.truongvu.choviet.product;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridLayout;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.adapter.ProductAdapter;
import edu.hust.truongvu.choviet.dialog.FilterProductDialog;
import edu.hust.truongvu.choviet.dialog.SortProductDialog;
import edu.hust.truongvu.choviet.entity.Product;
import edu.hust.truongvu.choviet.utils.Constants;

public class ListProductByTypeActivity extends AppCompatActivity implements ListProductByTypeView, View.OnClickListener {
    private View btnFilter, btnSort, layoutErr;
    private RecyclerView recyclerView;
    private ListProductByTypePresenterImp presenterImp;
    private ArrayList<Product> mData = new ArrayList<>();
    private ProductAdapter adapter;
    private int options;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product_by_type);
        presenterImp = new ListProductByTypePresenterImp(this);
        options = getIntent().getIntExtra(Constants.MyTag.INTENT_TYPE_LOAD_PRODUCT, 0);

        initView();
    }

    private void initView(){
        btnFilter = findViewById(R.id.btn_filter);
        btnSort = findViewById(R.id.btn_sort);
        recyclerView = findViewById(R.id.list_product);
        layoutErr = findViewById(R.id.layout_err);
        if (options != 0){
            switch (options){
                case 1:
                    int idCategory = getIntent().getIntExtra(Constants.MyTag.ID_CATEGORY, 0);
                    presenterImp.initListProductByCategory(idCategory);
                    break;
                case 2:
                    int idBrand = getIntent().getIntExtra(Constants.MyTag.ID_BRAND, 0);
                    presenterImp.initListProductByBrand(idBrand);
                    break;
                case 3:
                    int idShop = getIntent().getIntExtra(Constants.MyTag.ID_SHOP, 0);
                    presenterImp.initListProductByShop(idShop);
                    break;
                default:
                    presenterImp.initListProductByShop(-1);
                    break;
            }
        }

        btnFilter.setOnClickListener(this);
        btnSort.setOnClickListener(this);
    }


    @Override
    public void loadListSuccessful(ArrayList<Product> listProduct) {
        try {
            layoutErr.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            mData = listProduct;
            adapter = new ProductAdapter(this, mData, new ProductAdapter.ProductListener() {
                @Override
                public void onProductResult(Product product) {
                    Intent intent = new Intent(ListProductByTypeActivity.this, ProductActivity.class);
                    intent.putExtra(Constants.MyTag.INTENT_PRODUCT, product);
                    startActivity(intent);
                }

                @Override
                public void onLikeClick() {

                }
            });
            recyclerView.setLayoutManager(new GridLayoutManager(ListProductByTypeActivity.this, 2, GridLayout.VERTICAL, false));
            recyclerView.setAdapter(adapter);
        }catch (Exception e){
            recyclerView.setVisibility(View.GONE);
            layoutErr.setVisibility(View.VISIBLE);
            e.printStackTrace();
        }
    }

    @Override
    public void loadListFalse() {
        recyclerView.setVisibility(View.GONE);
        layoutErr.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_filter:
                filter();
                break;
            case R.id.btn_sort:
                sort();
                break;
        }
    }

    private void sort(){
        SortProductDialog dialog = new SortProductDialog(ListProductByTypeActivity.this, new SortProductDialog.SortListener() {
            @Override
            public void onSortTimeOldToNew() {

            }

            @Override
            public void onSortTimeNewToOld() {

            }

            @Override
            public void onSortMoneyCheapToExps() {

            }

            @Override
            public void onSortMoneyExpsToCheap() {

            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private void filter(){
        FilterProductDialog dialog = new FilterProductDialog(ListProductByTypeActivity.this, new FilterProductDialog.FilterListener() {
            @Override
            public void onApplyFilter(int category, int brand, int price) {

            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}
