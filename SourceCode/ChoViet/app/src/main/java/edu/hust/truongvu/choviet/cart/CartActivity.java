package edu.hust.truongvu.choviet.cart;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.helper.customview.DeleteItemDialog;
import edu.hust.truongvu.choviet.model.entity.Product;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.model.entity.User;
import edu.hust.truongvu.choviet.order.PaymentActivity;
import edu.hust.truongvu.choviet.order.address.GuessInfoActivity;
import edu.hust.truongvu.choviet.search.SearchActivity;
import edu.hust.truongvu.choviet.startup.StartActivity;
import edu.hust.truongvu.choviet.user.ProfileCheckDialog;

public class CartActivity extends AppCompatActivity implements CartView, View.OnClickListener{

    private RecyclerView mListItem;
    private View btnPay, layoutNumberItemCart, btnBack, layoutSearch;
    private TextView tvTotal, tvNumberItemCart;

    private long totalMoney = 0;
    private CartPresenterImp cartPresenterImp;
    private ArrayList<Product> mListProduct = new ArrayList<>();
    CartAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        mListItem = findViewById(R.id.list_item_cart);
        tvTotal = findViewById(R.id.tv_total_money);
        layoutNumberItemCart = findViewById(R.id.layout_number_item_cart);
        tvNumberItemCart = findViewById(R.id.tv_number_item_cart);
        btnPay = findViewById(R.id.btn_pay);
        btnBack = findViewById(R.id.btn_back);
        layoutSearch = findViewById(R.id.layout_search);

        cartPresenterImp = new CartPresenterImp(this);
        btnPay.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        layoutSearch.setOnClickListener(this);
        cartPresenterImp.initListItemCart(this);
        cartPresenterImp.calculateTotal(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        MyHelper.setViewCart(layoutNumberItemCart, tvNumberItemCart, cartPresenterImp.getNumberItemCart(this));
    }

    @Override
    public void loadCartItemSuccessful(final ArrayList<Product> listProduct) {
        mListProduct = listProduct;
        adapter = new CartAdapter(this, mListProduct, new CartAdapter.CartItemListener() {
            @Override
            public void onClick(Product product) {

            }

            @Override
            public void onLike(int idUser, Product product) {

            }

            @Override
            public void onUnlike(int idUser, Product product) {

            }


            @Override
            public void onDelete(final Product product) {
                DeleteItemDialog deleteItemDialog = new DeleteItemDialog(CartActivity.this, product.getId(), new DeleteItemDialog.DeleteItemCartListener() {
                    @Override
                    public void onDelete(int id_product) {
                        cartPresenterImp.deleteItemCart(CartActivity.this, id_product);
                        mListProduct.remove(product);
                        adapter.notifyDataSetChanged();
                        MyHelper.setViewCart(layoutNumberItemCart, tvNumberItemCart, cartPresenterImp.getNumberItemCart(CartActivity.this));
                    }
                });
                deleteItemDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                deleteItemDialog.show();
            }

            @Override
            public void onSpinnerSelect(Product product, int numberSelect) {
//                Toast.makeText(CartActivity.this, "Select: " + numberSelect, Toast.LENGTH_SHORT).show();
                cartPresenterImp.updateNumberSelect(CartActivity.this, product.getId(), numberSelect);
                cartPresenterImp.calculateTotal(CartActivity.this);
            }
        });
        mListItem.setLayoutManager(new LinearLayoutManager(this));
        mListItem.setAdapter(adapter);
    }

    @Override
    public void loadCartItemFalse() {

    }


    @Override
    public void deleteSuccessful() {
        MyHelper.showToast(this, getString(R.string.item_has_been_removed), FancyToast.SUCCESS);
        cartPresenterImp.initListItemCart(this);
        cartPresenterImp.calculateTotal(this);
    }

    @Override
    public void deleteFalse() {
        MyHelper.showToast(this, getString(R.string.remove_false), FancyToast.ERROR);
    }

    @Override
    public void updateTotalMoney(long money) {
        totalMoney = money;
        tvTotal.setText(MyHelper.formatMoney(totalMoney));
    }

    @Override
    public void updateNumberSuccessful() {

    }

    @Override
    public void updateNumberFalse() {

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btn_pay:
                User user = MyHelper.getCurrentUser(CartActivity.this);
                if (totalMoney == 0 || mListProduct.size() == 0){
                    MyHelper.showToast(this, getString(R.string.cart_empty), FancyToast.ERROR);

                }else if (user == null){
                    ProfileCheckDialog dialog = new ProfileCheckDialog(CartActivity.this, new ProfileCheckDialog.ProfileCheckListener() {
                        @Override
                        public void login() {
                            startActivity(new Intent(CartActivity.this, StartActivity.class));
                        }

                        @Override
                        public void continueAsGuess() {
                            startActivity(new Intent(CartActivity.this, GuessInfoActivity.class));
                        }
                    });
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();
                }else {
                    startActivity(new Intent(CartActivity.this, PaymentActivity.class));
                }
                break;
            case R.id.btn_back:
                onBackPressed();
                break;
            case R.id.layout_search:
                startActivity(new Intent(CartActivity.this, SearchActivity.class));
                break;
            default:
                break;
        }
    }
}
