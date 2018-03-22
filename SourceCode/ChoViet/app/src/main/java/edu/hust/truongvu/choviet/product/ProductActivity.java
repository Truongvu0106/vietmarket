package edu.hust.truongvu.choviet.product;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.utils.Constants;

public class ProductActivity extends AppCompatActivity {

    Toolbar toolbar;
    ProductPresenterImp productPresenterImp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        toolbar = (Toolbar) findViewById(R.id.toolbar_product);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        productPresenterImp = new ProductPresenterImp();

        int id = getIntent().getIntExtra(Constants.MyTag.PRODUCT_ID, 0);
        loadFragment(ProductFragment.getInstance(productPresenterImp.getProductById(id)));
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container_product, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
