package edu.hust.truongvu.choviet.user.myshop;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import com.github.clans.fab.FloatingActionButton;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.helper.Constants;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.model.entity.Shop;
import edu.hust.truongvu.choviet.user.myshop.add_product.AddProductActivity;
import edu.hust.truongvu.choviet.user.myshop.info_shop.InforShopActivity;
import edu.hust.truongvu.choviet.user.myshop.list_order.ShopListOrderActivity;
import edu.hust.truongvu.choviet.user.myshop.list_product.ShopListProductActivity;
import edu.hust.truongvu.choviet.user.myshop.registershop.RegisterShopActivity;
import edu.hust.truongvu.choviet.user.myshop.report.ReportActivity;

public class MyShopActivity extends AppCompatActivity implements View.OnClickListener, MyShopView{
    public static int ID_SHOP = 0;
    public static final String TAG_SHOP = "tag_shop";
    private FloatingActionButton btnAddNewProduct, btnEditInfor;
    private HorizontalInfiniteCycleViewPager viewPager;
    private ShopOptionPagerAdapter adapter;
    private View btnRegisterShop, layoutInfor, layoutErr;
    private MyShopPresenter myShopPresenter;
    private ImageView imgAvatar, imgCover;
    private TextView tvName, tvSlogan, tvNumberProduct, tvRate, tvAddress, tvPhone, tvWebsite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_shop);
        myShopPresenter = new MyShopPresenterImp(this, this);
        initView();
        myShopPresenter.initShopInfor(MyHelper.getUserIdPreference(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        myShopPresenter.initShopInfor(MyHelper.getUserIdPreference(this));
    }

    private void initView(){
        layoutInfor = findViewById(R.id.layout_infor);
        layoutErr = findViewById(R.id.layout_err);
        btnAddNewProduct = findViewById(R.id.btn_add_new_product);
        btnEditInfor = findViewById(R.id.btn_edit_information);
        btnRegisterShop = findViewById(R.id.btn_register_now);
        imgAvatar = findViewById(R.id.img_avater_shop);
        imgCover = findViewById(R.id.img_cover_shop);
        tvName = findViewById(R.id.tv_name_store);
        tvSlogan = findViewById(R.id.tv_slogan);
        tvNumberProduct = findViewById(R.id.number_products) ;
        tvRate = findViewById(R.id.rate_shop);
        tvAddress = findViewById(R.id.tv_address);
        tvPhone = findViewById(R.id.tv_phone);
        tvWebsite = findViewById(R.id.tv_website);
        viewPager = findViewById(R.id.pager_options);
        adapter = new ShopOptionPagerAdapter(this, new ShopOptionPagerAdapter.ShopOpitonPagerListener() {
            @Override
            public void onClick(int id) {
                switch (id){
                    case 0:
                        mStartActivity(ShopListProductActivity.class);
                        break;
                    case 1:
                        mStartActivity(ShopListOrderActivity.class);
                        break;
                    case 2:
                        mStartActivity(ReportActivity.class);
                        break;
                    default:
                        break;
                }
            }
        });
        viewPager.setAdapter(adapter);

        btnAddNewProduct.setOnClickListener(this);
        btnEditInfor.setOnClickListener(this);
        btnRegisterShop.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_add_new_product:
                mStartActivity(AddProductActivity.class);
                break;
            case R.id.btn_edit_information:
                mStartActivity(InforShopActivity.class);
                break;
            case R.id.btn_register_now:
                mStartActivity(RegisterShopActivity.class);
                break;
            default:
                break;
        }
    }

    private void mStartActivity(Class<? extends Activity> cls){
        Intent intent = new Intent(MyShopActivity.this, cls);
        intent.putExtra(TAG_SHOP, ID_SHOP);
        startActivity(intent);
    }

    @Override
    public void loadShopSuccessful(Shop shop) {
        ID_SHOP = shop.getId();
        layoutInfor.setVisibility(View.VISIBLE);
        layoutErr.setVisibility(View.GONE);
        MyHelper.setImagePicasso(this, imgAvatar, Constants.Path.MY_PATH + shop.getImgAvatar());
        MyHelper.setImagePicasso(this, imgCover, Constants.Path.MY_PATH + shop.getImgCover());
        tvName.setText(shop.getName());
        tvSlogan.setText(shop.getSlogan());
        tvNumberProduct.setText(12 + "");
        tvRate.setText(4.5 + "");
        tvAddress.setText(shop.getAddress());
        tvPhone.setText(shop.getPhone());
        tvWebsite.setText(shop.getWebsite());
    }

    @Override
    public void loadShopFalse() {
        layoutInfor.setVisibility(View.GONE);
        layoutErr.setVisibility(View.VISIBLE);
    }
}
