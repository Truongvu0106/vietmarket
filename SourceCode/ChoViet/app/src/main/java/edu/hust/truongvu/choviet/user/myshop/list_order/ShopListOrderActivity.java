package edu.hust.truongvu.choviet.user.myshop.list_order;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.helper.customview.DeleteItemDialog;
import edu.hust.truongvu.choviet.helper.customview.MyToolbarExtra;
import edu.hust.truongvu.choviet.model.entity.OrderDetails;
import edu.hust.truongvu.choviet.user.myshop.MyShopActivity;
import edu.hust.truongvu.choviet.user.myshop.list_product.ShopListProductActivity;

public class ShopListOrderActivity extends AppCompatActivity implements ShopListOrderView{
    private RecyclerView recyclerView;
    private OrderDetailsAdapter adapter;
    private ShopListOrderPresenter presenter;
    private int idShop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list_order);
        idShop = getIntent().getIntExtra(MyShopActivity.TAG_SHOP, 0);
        new MyToolbarExtra(this, getString(R.string.list_order), 0, new MyToolbarExtra.OnExtraToolbarListener() {
            @Override
            public void onMoreClick() {
            }

            @Override
            public void onBackClick() {
                onBackPressed();
            }
        });
        recyclerView = findViewById(R.id.list_order);
        presenter = new ShopListOrderPresenterImp(this, this);
        presenter.initListOrder(idShop);

    }

    @Override
    public void loadListOrderSuccessful(final ArrayList<OrderDetails> data) {
        adapter = new OrderDetailsAdapter(this, data, new OrderDetailsAdapter.OrderDetailsListener() {
            @Override
            public void onUpdate(int id, int status) {
                presenter.updateStatus(id, status);
            }

            @Override
            public void onDelete(final OrderDetails orderDetails) {
                DeleteItemDialog deleteItemDialog = new DeleteItemDialog(ShopListOrderActivity.this, orderDetails.getId(), new DeleteItemDialog.DeleteItemCartListener() {
                    @Override
                    public void onDelete(int id) {
                        data.remove(orderDetails);
                        adapter.notifyDataSetChanged();
                    }
                });
                deleteItemDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                deleteItemDialog.show();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void loadListOrderFalse() {

    }

    @Override
    public void updateSuccessful() {
        MyHelper.showToast(this, getString(R.string.update_successful), FancyToast.SUCCESS);
    }

    @Override
    public void updateFalse() {
        MyHelper.showToast(this, getString(R.string.update_false), FancyToast.ERROR);

    }

    @Override
    public void deleteSuccessful() {

    }

    @Override
    public void deleteFalse() {

    }
}
