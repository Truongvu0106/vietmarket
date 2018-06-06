package edu.hust.truongvu.choviet.product.list_product;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridLayout;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.cart.CartPresenterImp;
import edu.hust.truongvu.choviet.helper.customview.MyToolbarMain;
import edu.hust.truongvu.choviet.model.entity.PriceFilter;
import edu.hust.truongvu.choviet.model.entity.Product;
import edu.hust.truongvu.choviet.product.details_product.ProductActivity;
import edu.hust.truongvu.choviet.helper.Constants;

public class ListProductActivity extends AppCompatActivity implements ListProductView, View.OnClickListener {
    private View btnFilter, btnSort, layoutErr, btnClearFilter, layoutList;
    private RecyclerView recyclerView;
    private ListProductPresenterImp presenterImp;
    private ArrayList<Product> intentListProduct = new ArrayList<>();
    private ArrayList<Product> mData = new ArrayList<>();
    private ArrayList<Product> originalData = new ArrayList<>();
    private ProductAdapter adapter;
    private int options;

    private boolean isLastest = false;
    private int sortByPrice = Constants.MyTag.NONE_ORDER_BY_PRICE;
    private int idCategory = 0, idBrand = 0;
    private PriceFilter priceFilter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);
        presenterImp = new ListProductPresenterImp(this,this);
        options = getIntent().getIntExtra(Constants.MyTag.INTENT_TYPE_LOAD_PRODUCT, 0);
        intentListProduct = (ArrayList<Product>) getIntent().getSerializableExtra(Constants.MyTag.INTENT_LIST_PRODUCT);
        initView();
    }

    private void initView(){
        new MyToolbarMain(this, this, new CartPresenterImp().getNumberItemCart(this), new MyToolbarMain.MainToolbarListener() {
            @Override
            public void onBackClick() {
                onBackPressed();
            }
        });
        btnFilter = findViewById(R.id.btn_filter);
        btnSort = findViewById(R.id.btn_sort);
        btnClearFilter = findViewById(R.id.btn_clear);
        recyclerView = findViewById(R.id.list_product);
        layoutErr = findViewById(R.id.layout_err);
        layoutList = findViewById(R.id.layout_list);
        if (options != 0){
            switch (options){
                case 1:
                    int idCategory = getIntent().getIntExtra(Constants.MyTag.ID_CATEGORY, 0);
                    presenterImp.initListProductByCategory(false, idCategory);
                    originalData = presenterImp.getProductByCategory(idCategory);
                    break;
                case 2:
                    int idBrand = getIntent().getIntExtra(Constants.MyTag.ID_BRAND, 0);
                    presenterImp.initListProductByBrand(false, idBrand);
                    originalData = presenterImp.getProductByBrand(idBrand);
                    break;
                case 3:
                    int idShop = getIntent().getIntExtra(Constants.MyTag.ID_SHOP, 0);
                    presenterImp.initListProductByShop(false, idShop);
                    break;
                default:
                    presenterImp.initListProductByShop(false, -1);
                    break;
            }
        }else {
            presenterImp.initListProductByData(intentListProduct);
            originalData = intentListProduct;
        }

        btnClearFilter.setVisibility(View.GONE);

        btnFilter.setOnClickListener(this);
        btnSort.setOnClickListener(this);
        btnClearFilter.setOnClickListener(this);
    }


    @Override
    public void loadListSuccessful(boolean isLastest, ArrayList<Product> listProduct) {
        try {
            ;
            mData = listProduct;
            if (mData.size() == 0){
                layoutList.setVisibility(View.GONE);
                layoutErr.setVisibility(View.VISIBLE);
            }else {
                layoutErr.setVisibility(View.GONE);
                layoutList.setVisibility(View.VISIBLE);
                adapter = new ProductAdapter(this, mData, new ProductAdapter.ProductListener() {
                    @Override
                    public void onProductResult(Product product) {
                        Intent intent = new Intent(ListProductActivity.this, ProductActivity.class);
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
                recyclerView.setLayoutManager(new GridLayoutManager(ListProductActivity.this, 2, GridLayout.VERTICAL, isLastest));
                recyclerView.setAdapter(adapter);
            }

        }catch (Exception e){
            layoutList.setVisibility(View.GONE);
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
            case R.id.btn_clear:
                clearFilter();
                break;
        }
    }

    private void clearFilter(){
        ClearFilterDialog dialog = new ClearFilterDialog(this, new ClearFilterDialog.ClearFilterListener() {
            @Override
            public void onClear() {
//                if (options != 0){
                    presenterImp.initListProductByOptions(false, Constants.MyTag.NONE_ORDER_BY_PRICE,
                            0, 0, null, originalData);
//                }else {
//                    presenterImp.initListProductByData(originalData);
//                }

                btnClearFilter.setVisibility(View.GONE);
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private void sort(){
        SortProductDialog dialog = new SortProductDialog(ListProductActivity.this, new SortProductDialog.SortListener() {
            @Override
            public void onSortTimeOldToNew() {
                isLastest = false;
                presenterImp.initListProductByOptions(isLastest, sortByPrice, idBrand, idCategory, priceFilter, mData);

            }

            @Override
            public void onSortTimeNewToOld() {
                isLastest = true;
                presenterImp.initListProductByOptions(isLastest, sortByPrice, idBrand, idCategory, priceFilter, mData);

            }

            @Override
            public void onSortMoneyCheapToExps() {
                sortByPrice = Constants.MyTag.LOAD_PRODUCT_PRICE_ASC;
                presenterImp.initListProductByOptions(isLastest, sortByPrice, idBrand, idCategory, priceFilter, mData);
            }

            @Override
            public void onSortMoneyExpsToCheap() {
                sortByPrice = Constants.MyTag.LOAD_PRODUCT_PRICE_DESC;
                presenterImp.initListProductByOptions(isLastest, sortByPrice, idBrand, idCategory, priceFilter, mData);
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private void filter(){
        FilterProductDialog dialog = new FilterProductDialog(ListProductActivity.this, options, new FilterProductDialog.FilterListener() {
            @Override
            public void onApplyFilter(int category, int brand, PriceFilter priceFilter) {
                presenterImp.initListProductByOptions(isLastest, sortByPrice, brand, category, priceFilter, mData);
                if (category != 0 || brand != 0 || priceFilter != null){
                    btnClearFilter.setVisibility(View.VISIBLE);
                }
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}
