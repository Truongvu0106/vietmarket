package edu.hust.truongvu.choviet.myshop.list_product;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.customview.MyToolbarExtra;

public class ShopListProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list_product);
        new MyToolbarExtra(this, "", 0, new MyToolbarExtra.OnExtraToolbarListener() {
            @Override
            public void onMoreClick() {
            }

            @Override
            public void onBackClick() {
                onBackPressed();
            }
        });
    }
}