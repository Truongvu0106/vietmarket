package edu.hust.truongvu.choviet.shop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.entity.Product;
import edu.hust.truongvu.choviet.adapter.ProductAdapter;
import edu.hust.truongvu.choviet.entity.Shop;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.product.ProductActivity;
import edu.hust.truongvu.choviet.utils.Constants;

public class ShopActivity extends AppCompatActivity implements ShopView{

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private ShopPresenterImp shopPresenterImp;
    Shop shop;
    private ImageView avatarShop;
    private TextView tvName, tvSlogan, tvNumProduct, tvRate, tvAddress, tvPhone, tvWebsite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        recyclerView = findViewById(R.id.list_product);
        initView();
        shop = (Shop) getIntent().getSerializableExtra(Constants.MyTag.INTENT_SHOP);
        shopPresenterImp = new ShopPresenterImp(this, this);
        shopPresenterImp.initInforShop(shop);
    }

    private void initView(){
        avatarShop = findViewById(R.id.img_avatar_shop);
        tvName = findViewById(R.id.tv_name_store);
        tvSlogan = findViewById(R.id.tv_slogan);
        tvNumProduct = findViewById(R.id.number_products);
        tvRate = findViewById(R.id.rate_shop);
        tvAddress = findViewById(R.id.tv_address);
        tvPhone = findViewById(R.id.tv_phone);
        tvWebsite = findViewById(R.id.tv_website);
    }

    @Override
    public void loadInforShopSuccessful(Shop shop) {
        shopPresenterImp.initListProduct(shop.getId());
        MyHelper.setImagePicasso(ShopActivity.this, avatarShop, Constants.Path.MY_PATH + shop.getImgAvatar());
        tvName.setText(shop.getName());
        tvSlogan.setText(shop.getSlogan());
        tvNumProduct.setText("0");
        tvRate.setText(shop.getRate() + "");
        tvAddress.setText(shop.getAddress());
        tvPhone.setText(shop.getPhone());
        tvWebsite.setText(shop.getWebsite());
    }

    @Override
    public void loadInforShopFalse() {
        avatarShop.setImageResource(R.drawable.error);
        tvName.setText("???");
        tvSlogan.setText("???");
        tvNumProduct.setText("???");
        tvRate.setText("???");
        tvAddress.setText("???");
        tvPhone.setText("???");
        tvWebsite.setText("???");
    }

    @Override
    public void loadListProductByShopSuccessful(ArrayList<Product> listProduct) {
        adapter = new ProductAdapter(this, listProduct, new ProductAdapter.ProductListener() {
            @Override
            public void onProductResult(Product product) {
                Intent intent = new Intent(ShopActivity.this, ProductActivity.class);
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
        recyclerView.setLayoutManager(new GridLayoutManager(ShopActivity.this, 2, GridLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void loadListProductByShopFalse() {

    }
}
