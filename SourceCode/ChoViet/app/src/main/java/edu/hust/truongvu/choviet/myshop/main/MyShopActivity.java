package edu.hust.truongvu.choviet.myshop.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.github.clans.fab.FloatingActionButton;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.myshop.add_product.AddProductActivity;

public class MyShopActivity extends AppCompatActivity implements View.OnClickListener{

    private FloatingActionButton btnAddNewProduct, btnEditInfor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_shop);
        initInfor();
    }

    private void initInfor(){
        btnAddNewProduct = findViewById(R.id.btn_add_new_product);
        btnEditInfor = findViewById(R.id.btn_edit_information);

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
