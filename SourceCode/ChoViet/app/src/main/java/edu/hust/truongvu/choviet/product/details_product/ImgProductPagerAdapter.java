package edu.hust.truongvu.choviet.product.details_product;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.product.details_product.ImgProductFragment;

/**
 * Created by truon on 2/26/2018.
 */

public class ImgProductPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<String> listImage;
    public ImgProductPagerAdapter(FragmentManager fm, ArrayList<String> list) {
        super(fm);
        this.listImage = list;
    }

    @Override
    public Fragment getItem(int position) {
        return ImgProductFragment.getInstance(listImage.get(position));
    }

    @Override
    public int getCount() {
        return listImage.size();
    }
}
