package edu.hust.truongvu.choviet.user.followingshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.helper.Constants;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.helper.customview.MyToolbarExtra;
import edu.hust.truongvu.choviet.model.entity.Shop;
import edu.hust.truongvu.choviet.shop.details_shop.ShopActivity;
import edu.hust.truongvu.choviet.shop.list_shop.ListShopAdapter;

public class FollowingShopActivity extends AppCompatActivity implements FollowingShopView{

    private RecyclerView recyclerView;
    private View layoutErr;
    private ListShopAdapter adapter;
    private FollowingShopPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_following_shop);
        new MyToolbarExtra(this, getString(R.string.following_shop), 0, new MyToolbarExtra.OnExtraToolbarListener() {
            @Override
            public void onMoreClick() {

            }

            @Override
            public void onBackClick() {
                onBackPressed();
            }
        });
        initView();
        presenter = new FollowingShopPresenterImp(this, this);
        presenter.initListShop(MyHelper.getUserIdPreference(this));
    }

    private void initView(){
        recyclerView = findViewById(R.id.list_shop);
        layoutErr = findViewById(R.id.layout_err);
    }

    @Override
    public void loadListShopSuccessful(final ArrayList<Shop> data) {
        layoutErr.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        adapter = new ListShopAdapter(this, data, new ListShopAdapter.ShopListener() {
            @Override
            public void onResults(Shop shop) {
                Intent intent = new Intent(FollowingShopActivity.this, ShopActivity.class);
                intent.putExtra(Constants.MyTag.INTENT_SHOP, shop);
                startActivity(intent);
            }

            @Override
            public void onFollow(Shop shop, int idUser) {

            }

            @Override
            public void onUnFollow(Shop shop, int idUser) {
                data.remove(shop);
                adapter.notifyDataSetChanged();
            }


        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void loadListShopFalse() {
        layoutErr.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }
}
