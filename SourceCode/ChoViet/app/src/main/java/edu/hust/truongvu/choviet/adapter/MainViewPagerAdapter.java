package edu.hust.truongvu.choviet.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import edu.hust.truongvu.choviet.category.CategoryFragment;
import edu.hust.truongvu.choviet.notification.NotificationFragment;
import edu.hust.truongvu.choviet.profile.ProfileFragment;
import edu.hust.truongvu.choviet.home.HomeFragment;
import edu.hust.truongvu.choviet.shop.ListShopFragment;

/**
 * Created by truon on 2/22/2018.
 */

public class MainViewPagerAdapter extends FragmentStatePagerAdapter {
    private static final int NUM_PAGES = 5;
    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return HomeFragment.newInstance();
            case 1:
                return CategoryFragment.newInstance();
            case 2:
                return ListShopFragment.getInstance();
            case 3:
                return NotificationFragment.newInstance();
            case 4:
                return ProfileFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}
