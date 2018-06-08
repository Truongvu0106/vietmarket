package edu.hust.truongvu.choviet.admin.banner.manage_banner;

import android.content.Context;
import android.util.Log;

import edu.hust.truongvu.choviet.helper.Constants;
import edu.hust.truongvu.choviet.model.BannerModel;
import edu.hust.truongvu.choviet.model.entity.Banner;
import edu.hust.truongvu.choviet.model.entity.MyImage;
import edu.hust.truongvu.choviet.services.UploadImageListener;
import edu.hust.truongvu.choviet.services.UploadImageService;

/**
 * Created by truon on 6/6/2018.
 */

public class ManageBannerPresenterImp implements ManageBannerPresenter, UploadImageListener{
    public static final String UPLOAD_IMG_URL = Constants.MY_PATH + "uploadImageBanner.php";

    private Context mContext;
    private ManageBannerView view;
    private BannerModel bannerModel;
    private UploadImageService uploadImageService;
    public ManageBannerPresenterImp(Context context, ManageBannerView view){
        this.mContext = context;
        this.view = view;
        bannerModel = new BannerModel(mContext);
        uploadImageService = new UploadImageService(mContext, this, UPLOAD_IMG_URL);
    }

    @Override
    public void addBanner(Banner banner) {
        if (banner == null){
            view.addBannerFalse();
            return;
        }

        if (bannerModel.addBanner(banner)){
            view.addBannerSuccessful();
        }else {
            view.addBannerFalse();
        }
    }

    @Override
    public void updateBanner(Banner banner, boolean isChangedImage) {
        if (banner == null){
            view.updateBannerFalse();
            return;
        }

        if (bannerModel.updateBanner(banner, isChangedImage)){
            view.updateBannerSuccessful();
        }else {
            view.updateBannerFalse();
        }
    }

    @Override
    public void uploadImage(MyImage myImage) {
        if (myImage == null){
            Log.e("truong", "data image null");
        }else {
            try {
                uploadImageService.uploadImageVolley(myImage);
            }catch (Exception e){
                view.uploadNewImageFalse();
            }

        }
    }

    @Override
    public void onResults(boolean isSuccessful, String message) {
        if (isSuccessful){
            view.uploadNewImageSuccessful();
        }else {
            view.uploadNewImageFalse();
        }
    }
}
