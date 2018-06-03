package edu.hust.truongvu.choviet.user.myorder;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by truon on 6/3/2018.
 */

public class OrderPagerAdapter extends FragmentStatePagerAdapter{
    private ArrayList<Fragment> mFragmentList = new ArrayList<>();
    private ArrayList<String> mFragmentTitleList = new ArrayList<>();

    public OrderPagerAdapter(FragmentManager fm, ArrayList<Fragment> listFragment, ArrayList<String> listTitle) {
        super(fm);
        mFragmentList = listFragment;
        mFragmentTitleList = listTitle;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
}
