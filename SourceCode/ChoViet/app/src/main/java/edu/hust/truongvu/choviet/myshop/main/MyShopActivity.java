package edu.hust.truongvu.choviet.myshop.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import com.github.clans.fab.FloatingActionButton;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.adapter.ShopOptionPagerAdapter;
import edu.hust.truongvu.choviet.myshop.add_product.AddProductActivity;
import edu.hust.truongvu.choviet.myshop.list_order.ShopListOrderActivity;
import edu.hust.truongvu.choviet.myshop.list_product.ShopListProductActivity;
import edu.hust.truongvu.choviet.myshop.report.ReportActivity;

public class MyShopActivity extends AppCompatActivity implements View.OnClickListener{

    private FloatingActionButton btnAddNewProduct, btnEditInfor;
    private HorizontalInfiniteCycleViewPager viewPager;
    private ShopOptionPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_shop);
        initView();
    }

    private void initView(){
        btnAddNewProduct = findViewById(R.id.btn_add_new_product);
        btnEditInfor = findViewById(R.id.btn_edit_information);
        viewPager = findViewById(R.id.pager_options);
        adapter = new ShopOptionPagerAdapter(this, new ShopOptionPagerAdapter.ShopOpitonPagerListener() {
            @Override
            public void onClick(int id) {
                switch (id){
                    case 0:
                        startActivity(new Intent(MyShopActivity.this, ShopListProductActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MyShopActivity.this, ShopListOrderActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MyShopActivity.this, ReportActivity.class));
                        break;
                    default:
                        break;
                }
            }
        });
        viewPager.setAdapter(adapter);

        btnAddNewProduct.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_add_new_product:
                startActivity(new Intent(MyShopActivity.this, AddProductActivity.class));
                break;
            default:
                break;
        }
    }
}
