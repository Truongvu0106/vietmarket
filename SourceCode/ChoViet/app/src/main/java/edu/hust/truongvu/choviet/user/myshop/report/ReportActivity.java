package edu.hust.truongvu.choviet.user.myshop.report;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.helper.customview.MyToolbarExtra;

public class ReportActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ReportPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        new MyToolbarExtra(this, "", 0, new MyToolbarExtra.OnExtraToolbarListener() {
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

        ArrayList<Fragment> listFragment = new ArrayList<>();
        listFragment.add(ShopReportFragment.getInstance());
        listFragment.add(OverviewReportFragment.getInstance());

        ArrayList<String> listTitle = new ArrayList<>();
        listTitle.add(getString(R.string.shop_report));
        listTitle.add(getString(R.string.overview_report));
        adapter = new ReportPagerAdapter(getSupportFragmentManager(), listFragment, listTitle);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
