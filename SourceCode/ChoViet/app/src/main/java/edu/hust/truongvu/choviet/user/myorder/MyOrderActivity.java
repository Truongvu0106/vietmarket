package edu.hust.truongvu.choviet.user.myorder;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.helper.Constants;
import edu.hust.truongvu.choviet.helper.customview.MyToolbarExtra;
import edu.hust.truongvu.choviet.user.ProfileFragment;
import edu.hust.truongvu.choviet.user.myshop.MyShopActivity;
import edu.hust.truongvu.choviet.user.myshop.report.OverviewReportFragment;
import edu.hust.truongvu.choviet.user.myshop.report.ReportPagerAdapter;
import edu.hust.truongvu.choviet.user.myshop.report.ShopReportFragment;

public class MyOrderActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ReportPagerAdapter adapter;
    private int status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        new MyToolbarExtra(this, getString(R.string.list_order), 0, new MyToolbarExtra.OnExtraToolbarListener() {
            @Override
            public void onMoreClick() {

            }

            @Override
            public void onBackClick() {
                onBackPressed();
            }
        });
        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.viewpager);
        status = getIntent().getIntExtra(ProfileFragment.OPTION_TAG, 0);

        ArrayList<Fragment> listFragment = new ArrayList<>();
        listFragment.add(OrderStatusFragment.getInstance(Constants.OrderStatus.WAITING.getIdStatus()));
        listFragment.add(OrderStatusFragment.getInstance(Constants.OrderStatus.SHIPPING.getIdStatus()));
        listFragment.add(OrderStatusFragment.getInstance(Constants.OrderStatus.RECEIVED.getIdStatus()));
        listFragment.add(OrderStatusFragment.getInstance(Constants.OrderStatus.CANCEL.getIdStatus()));

        ArrayList<String> listTitle = new ArrayList<>();
        listTitle.add(getString(R.string.waiting));
        listTitle.add(getString(R.string.shipping));
        listTitle.add(getString(R.string.received));
        listTitle.add(getString(R.string.cancel));
        adapter = new ReportPagerAdapter(getSupportFragmentManager(), listFragment, listTitle);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        TabLayout.Tab tab = tabLayout.getTabAt(status);
        tab.select();
    }
}
