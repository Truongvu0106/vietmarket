package edu.hust.truongvu.choviet.admin.banner;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.model.entity.Banner;

/**
 * Created by truon on 5/29/2018.
 */

public interface BannerView {
    void loadListBannerSuccessful(ArrayList<Banner> data);
    void loadListBannerFalse();
}
