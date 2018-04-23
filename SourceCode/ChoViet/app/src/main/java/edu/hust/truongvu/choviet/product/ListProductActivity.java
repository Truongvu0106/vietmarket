package edu.hust.truongvu.choviet.product;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.adapter.ProductAdapter;
import edu.hust.truongvu.choviet.entity.Product;

public class ListProductActivity extends AppCompatActivity implements ListProductView{
    private View btnFilter, btnSort;
    private RecyclerView recyclerView;
    private ListProductPresenterImp presenterImp;
    private ProductAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);
        btnFilter = findViewById(R.id.btn_filter);
        btnSort = findViewById(R.id.btn_sort);
        recyclerView = findViewById(R.id.list_product);

        presenterImp = new ListProductPresenterImp(this);
        presenterImp.initList();
    }

    @Override
    public void loadList(ArrayList<Product> listProduct) {
        adapter = new ProductAdapter(ListProductActivity.this, listProduct, new ProductAdapter.ProductListener() {
            @Override
            public void onProductResult(Product product) {

            }

            @Override
            public void onLikeClick() {

            }
        });

        recyclerView.setLayoutManager(new GridLayoutManager(ListProductActivity.this, 2, GridLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }
}
