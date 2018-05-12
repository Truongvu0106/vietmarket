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

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.model.entity.Product;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.order.PaymentActivity;

public class CartActivity extends AppCompatActivity implements CartView, View.OnClickListener{

    private RecyclerView mListItem;
    private View btnPay, layoutNumberItemCart;
    private TextView tvTotal, tvNumberItemCart;

    private long totalMoney = 0;
    private CartPresenterImp cartPresenterImp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        mListItem = findViewById(R.id.list_item_cart);
        tvTotal = findViewById(R.id.tv_total_money);
        layoutNumberItemCart = findViewById(R.id.layout_number_item_cart);
        tvNumberItemCart = findViewById(R.id.tv_number_item_cart);
        btnPay = findViewById(R.id.btn_pay);

        cartPresenterImp = new CartPresenterImp(this);
        btnPay.setOnClickListener(this);
        cartPresenterImp.initListItemCart(this);
        cartPresenterImp.calculateTotal(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        MyHelper.setViewCart(layoutNumberItemCart, tvNumberItemCart, cartPresenterImp.getNumberItemCart(this));
    }

    @Override
    public void loadCartItemSuccessful(ArrayList<Product> listProduct) {
        CartAdapter adapter = new CartAdapter(this, listProduct, new CartAdapter.CartItemListener() {
            @Override
            public void onClick(Product product) {

            }

            @Override
            public void onLike(Product product) {

            }

            @Override
            public void onDelete(Product product) {
                DeleteItemCartDialog deleteItemCartDialog = new DeleteItemCartDialog(CartActivity.this, product.getId(), new DeleteItemCartDialog.DeleteItemCartListener() {
                    @Override
                    public void onDelete(int id_product) {
                        cartPresenterImp.deleteItemCart(CartActivity.this, id_product);
                        MyHelper.setViewCart(layoutNumberItemCart, tvNumberItemCart, cartPresenterImp.getNumberItemCart(CartActivity.this));
                    }
                });
                deleteItemCartDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                deleteItemCartDialog.show();
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
        Toast.makeText(this, getString(R.string.item_has_been_removed), Toast.LENGTH_SHORT).show();
        cartPresenterImp.initListItemCart(this);
    }

    @Override
    public void deleteFalse() {
        Toast.makeText(this, getString(R.string.remove_false), Toast.LENGTH_SHORT).show();
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
                if (totalMoney == 0){
                    Toast.makeText(this, getString(R.string.cart_empty), Toast.LENGTH_SHORT).show();
                }else {
                    startActivity(new Intent(CartActivity.this, PaymentActivity.class));
                }
                break;
            default:
                break;
        }
    }
}
