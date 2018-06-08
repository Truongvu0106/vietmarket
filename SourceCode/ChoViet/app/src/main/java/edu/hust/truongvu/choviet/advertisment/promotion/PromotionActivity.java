package edu.hust.truongvu.choviet.advertisment.promotion;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.helper.customview.MyToolbarExtra;
import edu.hust.truongvu.choviet.model.entity.Promotion;

public class PromotionActivity extends AppCompatActivity implements PromotionView{
    private RecyclerView recyclerView;
    private PromotionAdapter adapter;
    private PromotionPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion);
        recyclerView = findViewById(R.id.list_promotion);
        recyclerView.setFocusable(false);
        new MyToolbarExtra(this, getString(R.string.hot_deal_today), 0, new MyToolbarExtra.OnExtraToolbarListener() {
            @Override
            public void onMoreClick() {

            }

            @Override
            public void onBackClick() {
                onBackPressed();
            }
        });
        presenter = new PromotionPresenterImp(this, this);
        presenter.initListPresenter();
    }

    @Override
    public void loadPromotionSuccessful(ArrayList<Promotion> data) {
        adapter = new PromotionAdapter(this, data, new PromotionAdapter.PromotionListener() {
            @Override
            public void onLongClick(Promotion promotion) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Promotion", promotion.getCode());
                clipboard.setPrimaryClip(clip);
                MyHelper.showToast(PromotionActivity.this, getString(R.string.copy_to_clipoard), FancyToast.SUCCESS);
            }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(PromotionActivity.this, 2, GridLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void loadPromotionFalse() {

    }
}
