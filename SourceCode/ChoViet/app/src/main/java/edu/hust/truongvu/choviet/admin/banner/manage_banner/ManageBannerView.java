package edu.hust.truongvu.choviet.admin.banner.manage_banner;

/**
 * Created by truon on 6/6/2018.
 */

public interface ManageBannerView {
    void addBannerSuccessful();
    void addBannerFalse();

    void uploadNewImageSuccessful();
    void uploadNewImageFalse();

    void updateBannerSuccessful();
    void updateBannerFalse();
}
