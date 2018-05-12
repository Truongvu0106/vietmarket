package edu.hust.truongvu.choviet.init;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import edu.hust.truongvu.choviet.category.CategoryFragment;
import edu.hust.truongvu.choviet.user.ProfileFragment;
import edu.hust.truongvu.choviet.home.HomeFragment;
import edu.hust.truongvu.choviet.shop.list_shop.ListShopFragment;

/**
 * Created by truon on 2/22/2018.
 */

public class MainViewPagerAdapter extends FragmentStatePagerAdapter {
    private static final int NUM_PAGES = 4;
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
//            case 3:
//                return NotificationFragment.newInstance();
            case 3:
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
