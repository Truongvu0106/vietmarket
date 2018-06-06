package edu.hust.truongvu.choviet.admin.banner.manage_banner;

import edu.hust.truongvu.choviet.model.entity.Banner;
import edu.hust.truongvu.choviet.model.entity.MyImage;

/**
 * Created by truon on 6/6/2018.
 */

public interface ManageBannerPresenter {
    void addBanner(Banner banner);
    void updateBanner(Banner banner, boolean isChangedImage);
    void uploadImage(MyImage myImage);
}
