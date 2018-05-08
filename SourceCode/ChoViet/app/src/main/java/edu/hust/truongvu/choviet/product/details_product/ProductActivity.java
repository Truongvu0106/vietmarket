package edu.hust.truongvu.choviet.product.details_product;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.cart.CartActivity;
import edu.hust.truongvu.choviet.entity.Product;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.search.SearchActivity;
import edu.hust.truongvu.choviet.utils.Constants;

public class ProductActivity extends AppCompatActivity implements View.OnClickListener, ProductFragment.ItemCartListener{

    private View toolbar;
    ProductPresenterImp productPresenterImp;
    private View layoutSearch, btnCart, layoutNumberItemCart;
    private TextView tvNumberItemCart;
    private Product product;
    private View layoutErr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        toolbar = findViewById(R.id.toolbar_product);
        layoutSearch = findViewById(R.id.layout_search);
        btnCart = findViewById(R.id.img_cart);
        layoutErr = findViewById(R.id.layout_err);
        layoutNumberItemCart = findViewById(R.id.layout_number_item_cart);
        tvNumberItemCart = findViewById(R.id.tv_number_item_cart);

        productPresenterImp = new ProductPresenterImp(this);

        btnCart.setOnClickListener(this);
        layoutSearch.setOnClickListener(this);

        product = (Product) getIntent().getSerializableExtra(Constants.MyTag.INTENT_PRODUCT);
        if (product == null){
            layoutErr.setVisibility(View.VISIBLE);
        }else {
            layoutErr.setVisibility(View.GONE);
            loadFragment(ProductFragment.getInstance(product));
        }
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container_product, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.layout_search:
                startActivity(new Intent(ProductActivity.this, SearchActivity.class));
                break;
            case R.id.img_cart:
                startActivity(new Intent(ProductActivity.this, CartActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public void passNumberItem(int number) {
        MyHelper.setViewCart(layoutNumberItemCart, tvNumberItemCart, number);
    }
}
