package edu.hust.truongvu.choviet.shop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.entity.Product;
import edu.hust.truongvu.choviet.product.ProductAdapter;

public class ShopActivity extends AppCompatActivity implements ShopView{

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private ShopPresenterImp shopPresenterImp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        recyclerView = findViewById(R.id.list_product);
        shopPresenterImp = new ShopPresenterImp(this);
        shopPresenterImp.initListProduct();
    }

    @Override
    public void loadListProduct(ArrayList<Product> listProduct) {
        adapter = new ProductAdapter(ShopActivity.this, listProduct, new ProductAdapter.ProductListener() {
            @Override
            public void onProductResult(Product product) {

            }

            @Override
            public void onLikeClick() {

            }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(ShopActivity.this, 2, GridLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }
}
