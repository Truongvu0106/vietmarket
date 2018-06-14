package edu.hust.truongvu.choviet.user.myshop.list_product;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.helper.customview.MyToolbarExtra;
import edu.hust.truongvu.choviet.model.entity.Product;
import edu.hust.truongvu.choviet.user.myshop.MyShopActivity;
import edu.hust.truongvu.choviet.user.myshop.MyShopPresenter;
import edu.hust.truongvu.choviet.user.myshop.add_product.AddProductActivity;
import edu.hust.truongvu.choviet.user.myshop.update_product.UpdateProductActivity;

public class ShopListProductActivity extends AppCompatActivity implements ShopListProductView, View.OnClickListener{
    public static String TAG_PRODUCT = "tag_product";
    private RecyclerView recyclerView;
    private View btnAdd;
    private ShopListProductAdapter adapter;
    private ShopListProductPresenter shopListProductPresenter;
    private int idShop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list_product);
        idShop = getIntent().getIntExtra(MyShopActivity.TAG_SHOP, 0);
        new MyToolbarExtra(this, getString(R.string.list_product), R.drawable.more, new MyToolbarExtra.OnExtraToolbarListener() {
            @Override
            public void onMoreClick() {
            }

            @Override
            public void onBackClick() {
                onBackPressed();
            }
        });

        initView();
        shopListProductPresenter = new ShopListProductPresenterImp(this, this);
        shopListProductPresenter.initListProduct(idShop);

    }

    @Override
    protected void onResume() {
        super.onResume();
        shopListProductPresenter.initListProduct(idShop);
    }

    private void initView(){
        recyclerView = findViewById(R.id.list_product);
        btnAdd = findViewById(R.id.btn_add);

        btnAdd.setOnClickListener(this);
    }

    @Override
    public void loadListSuccessful(ArrayList<Product> data) {
        adapter = new ShopListProductAdapter(this, data, new ShopListProductAdapter.ShopProductListener() {
            @Override
            public void setDiscount(final Product product) {
                AddDiscountDialog discountDialog = new AddDiscountDialog(ShopListProductActivity.this, product.getDiscount(), new AddDiscountDialog.AddDiscountListener() {
                    @Override
                    public void onApply(int number) {
                        shopListProductPresenter.setDiscount(product.getId(), number);
                    }
                });
                discountDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                discountDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE| WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
                discountDialog.show();
            }

            @Override
            public void onDelete(Product product) {

            }

            @Override
            public void onUpdate(Product product) {
                Intent intent = new Intent(ShopListProductActivity.this, UpdateProductActivity.class);
                intent.putExtra(TAG_PRODUCT, product);
                startActivity(intent);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void loadListFalse() {

    }

    @Override
    public void setDiscountSuccessful() {
        MyHelper.showToast(this, getString(R.string.update_successful), FancyToast.SUCCESS);
        shopListProductPresenter.initListProduct(idShop);
    }

    @Override
    public void setDiscountFalse() {
        MyHelper.showToast(this, getString(R.string.update_false), FancyToast.ERROR);

    }

    @Override
    public void deleteSuccessful() {

    }

    @Override
    public void deleteFalse() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_add:
                Intent intent = new Intent(ShopListProductActivity.this, AddProductActivity.class);
                intent.putExtra(MyShopActivity.TAG_SHOP, idShop);
                startActivity(new Intent(this, AddProductActivity.class));
                break;
            default:
                break;
        }
    }
}
