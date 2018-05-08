package edu.hust.truongvu.choviet.user.wishlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.product.list_product.ProductAdapter;
import edu.hust.truongvu.choviet.customview.MyToolbarExtra;
import edu.hust.truongvu.choviet.entity.Product;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.product.details_product.ProductActivity;
import edu.hust.truongvu.choviet.utils.Constants;

public class WishListProductActivity extends AppCompatActivity implements WishListProductView{

    private RecyclerView recyclerView;
    private View layoutErr;
    private ProductAdapter adapter;
    private int idUser;
    private WishListProductPresenterImp presenterImp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list_product);
        new MyToolbarExtra(this, getString(R.string.your_wishlist), 0, new MyToolbarExtra.OnExtraToolbarListener() {
            @Override
            public void onMoreClick() {

            }

            @Override
            public void onBackClick() {
                onBackPressed();
            }
        });
        recyclerView = findViewById(R.id.list_product);
        layoutErr = findViewById(R.id.layout_err);

        idUser = MyHelper.getUserIdPreference(this);
        presenterImp = new WishListProductPresenterImp(this, this);
        presenterImp.initListWishList(idUser);
    }

    @Override
    public void loadListSuccessful(final ArrayList<Product> data) {
        adapter = new ProductAdapter(WishListProductActivity.this, data, new ProductAdapter.ProductListener() {
            @Override
            public void onProductResult(Product product) {
                Intent intent = new Intent(WishListProductActivity.this, ProductActivity.class);
                intent.putExtra(Constants.MyTag.INTENT_PRODUCT, product);
                startActivity(intent);
            }

            @Override
            public void onLikeClick(int idUser, Product product) {

            }

            @Override
            public void onUnlikeClick(int idUser, Product product) {
                data.remove(product);
                adapter.notifyDataSetChanged();
            }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void loadListFalse() {
        recyclerView.setVisibility(View.GONE);
        layoutErr.setVisibility(View.VISIBLE);
    }
}
