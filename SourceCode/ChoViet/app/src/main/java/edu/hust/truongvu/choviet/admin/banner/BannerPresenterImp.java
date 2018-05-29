package edu.hust.truongvu.choviet.admin.banner;

import android.content.Context;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.model.BannerModel;
import edu.hust.truongvu.choviet.model.entity.Banner;

/**
 * Created by truon on 5/29/2018.
 */

public class BannerPresenterImp implements BannerPresenter{
    private Context mContext;
    private BannerView bannerView;
    private BannerModel bannerModel;

    public BannerPresenterImp(Context context, BannerView bannerView){
        this.mContext = context;
        this.bannerView = bannerView;
        bannerModel = new BannerModel(mContext);
    }

    @Override
    public void initListBanner() {
        ArrayList<Banner> listBanners = bannerModel.getAllBanner();
        if (listBanners.isEmpty()){
            bannerView.loadListBannerFalse();
        }else {
            bannerView.loadListBannerSuccessful(listBanners);
        }
    }
}
