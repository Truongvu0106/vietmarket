package edu.hust.truongvu.choviet.shop.details_shop;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.model.entity.Product;
import edu.hust.truongvu.choviet.model.entity.User;
import edu.hust.truongvu.choviet.product.list_product.ProductAdapter;
import edu.hust.truongvu.choviet.model.entity.Shop;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.product.details_product.ProductActivity;
import edu.hust.truongvu.choviet.helper.Constants;
import edu.hust.truongvu.choviet.startup.StartActivity;
import edu.hust.truongvu.choviet.user.ProfileCheckDialog;

public class ShopActivity extends AppCompatActivity implements ShopView, View.OnClickListener{

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private ShopPresenter shopPresenter;
    Shop shop;
    private ImageView avatarShop, coverShop;
    private TextView tvName, tvSlogan, tvNumProduct, tvRate, tvAddress, tvPhone, tvWebsite;
    private View btnFollow, layoutFollowed;
    private RatingBar mRatingbar;
    private View layoutRating, btnBack;
    boolean mIsFollowing = false;
    private float currentRate = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        recyclerView = findViewById(R.id.list_product);
        initView();
        shop = (Shop) getIntent().getSerializableExtra(Constants.MyTag.INTENT_SHOP);
        shopPresenter = new ShopPresenterImp(this, this);
        shopPresenter.initInforShop(shop);
    }

    private void initView(){
        avatarShop = findViewById(R.id.img_avatar_shop);
        coverShop = findViewById(R.id.img_cover_shop);
        tvName = findViewById(R.id.tv_name_store);
        tvSlogan = findViewById(R.id.tv_slogan);
        tvNumProduct = findViewById(R.id.number_products);
        tvRate = findViewById(R.id.rate_shop);
        tvAddress = findViewById(R.id.tv_address);
        tvPhone = findViewById(R.id.tv_phone);
        tvWebsite = findViewById(R.id.tv_website);
        btnFollow = findViewById(R.id.btn_follow);
        layoutFollowed = findViewById(R.id.layout_followed);
        mRatingbar = findViewById(R.id.rating_bar);
        layoutRating = findViewById(R.id.layout_rating);
        btnBack = findViewById(R.id.btn_back);
    }

    @Override
    public void loadInforShopSuccessful(Shop shop) {
        shopPresenter.initListProduct(shop.getId());
        MyHelper.setImagePicasso(ShopActivity.this, avatarShop, Constants.Path.MY_PATH + shop.getImgAvatar());
        MyHelper.setImagePicasso(ShopActivity.this, coverShop, Constants.Path.MY_PATH + shop.getImgCover());
        tvName.setText(shop.getName());
        tvSlogan.setText(shop.getSlogan());
        tvNumProduct.setText("0");
        tvRate.setText(shop.getRate() + "");
        tvAddress.setText(shop.getAddress());
        tvPhone.setText(shop.getPhone());
        tvWebsite.setText(shop.getWebsite());
        shopPresenter.checkFollowing(MyHelper.getUserIdPreference(this), shop.getId());
        btnFollow.setOnClickListener(this);
        layoutRating.setOnClickListener(this);
        btnBack.setOnClickListener(this);

        currentRate = shopPresenter.getRateByUserAndShop(MyHelper.getUserIdPreference(this), shop.getId());
        mRatingbar.setRating(currentRate);
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
    public void initFollow(boolean isFollowing) {
        mIsFollowing = isFollowing;
        if (mIsFollowing){
            layoutFollowed.setVisibility(View.VISIBLE);
        }else {
            layoutFollowed.setVisibility(View.GONE);
        }
    }

    @Override
    public void followSuccessful() {
        Toast.makeText(this, getString(R.string.added_to_your_following), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void followFalse() {
        Toast.makeText(this, getString(R.string.follow_false), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void unFollowSuccessful() {
        Toast.makeText(this, getString(R.string.remove_from_your_following), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void unFollowFalse() {
        Toast.makeText(this, getString(R.string.unfollow_false), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addUserRateSuccessful(float rate) {
        mRatingbar.setRating(rate);
        shopPresenter.updateTotalRate(true, shop.getId(), rate);
    }

    @Override
    public void addUserRateFalse() {

    }

    @Override
    public void updateUserRateSuccessful(float rate) {
        mRatingbar.setRating(rate);
        shopPresenter.updateTotalRate(false, shop.getId(), rate);
    }

    @Override
    public void updateUserRateFalse() {

    }

    @Override
    public void updateTotalRateSuccessful(float rate) {
        tvRate.setText(rate + "");
    }

    @Override
    public void updateTotalRateFalse() {

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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_follow:
                handleFollowAction();
                break;
            case R.id.layout_rating:
                rateShop();
                break;
            case R.id.btn_back:
                onBackPressed();
                break;
            default:
                break;
        }
    }

    private void rateShop(){
        int currentUserId = MyHelper.getUserIdPreference(this);
        if (currentUserId == 0){
            ProfileCheckDialog dialog = new ProfileCheckDialog(this, new ProfileCheckDialog.ProfileCheckListener() {
                @Override
                public void login() {
                    startActivity(new Intent(ShopActivity.this, StartActivity.class));
                }

                @Override
                public void continueAsGuess() {

                }
            });
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        }else {
            RateShopDialog dialog = new RateShopDialog(this, currentRate, new RateShopDialog.RateShopListener() {
                @Override
                public void onRate(float rate) {
                    if (currentRate == 0){
                        shopPresenter.addUserRate(MyHelper.getUserIdPreference(ShopActivity.this), shop.getId(), rate);
                    }else {
                        shopPresenter.updateUserRate(MyHelper.getUserIdPreference(ShopActivity.this), shop.getId(), rate);
                    }
                }
            });
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        }

    }

    private void handleFollowAction(){
        int currentUserId = MyHelper.getUserIdPreference(this);
        if (currentUserId != 0){
            if (shop != null){
                if (mIsFollowing){
                    layoutFollowed.setVisibility(View.GONE);
                    shopPresenter.unFollow(currentUserId, shop.getId());
                }else {
                    layoutFollowed.setVisibility(View.VISIBLE);
                    shopPresenter.follow(currentUserId, shop.getId());
                }
            }
        }else {
            ProfileCheckDialog dialog = new ProfileCheckDialog(this, new ProfileCheckDialog.ProfileCheckListener() {
                @Override
                public void login() {
                    startActivity(new Intent(ShopActivity.this, StartActivity.class));
                }

                @Override
                public void continueAsGuess() {

                }
            });
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        }


    }
}
