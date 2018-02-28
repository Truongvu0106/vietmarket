package edu.hust.truongvu.choviet.cart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.entity.Product;
import edu.hust.truongvu.choviet.payment.PaymentActivity;

public class CartActivity extends AppCompatActivity implements CartView{

    private RecyclerView mListItem;
    private View btnPay;
    private CartPresenterImp cartPresenterImp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        mListItem = findViewById(R.id.list_item_cart);
        cartPresenterImp = new CartPresenterImp(this);
        btnPay = findViewById(R.id.btn_pay);
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartActivity.this, PaymentActivity.class));
            }
        });

        cartPresenterImp.initListItemCart();
    }

    @Override
    public void loadCartItem(ArrayList<Product> listProduct) {
        CartAdapter adapter = new CartAdapter(this, listProduct, new CartAdapter.CartItemListener() {
            @Override
            public void onClick(Product product) {

            }

            @Override
            public void onLike(Product product) {

            }

            @Override
            public void onDelete(Product product) {

            }

            @Override
            public void onSpinnerSelect(Product product, int numberSelect) {

            }
        });
        mListItem.setLayoutManager(new LinearLayoutManager(this));
        mListItem.setAdapter(adapter);
    }
}
