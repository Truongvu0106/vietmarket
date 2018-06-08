package edu.hust.truongvu.choviet.user.myshop.registershop;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.helper.Constants;
import edu.hust.truongvu.choviet.model.ShopModel;
import edu.hust.truongvu.choviet.model.entity.MyImage;
import edu.hust.truongvu.choviet.model.entity.Shop;
import edu.hust.truongvu.choviet.services.UploadImageListener;
import edu.hust.truongvu.choviet.services.UploadImageService;

/**
 * Created by truon on 5/18/2018.
 */

public class RegisterPresenterImp implements RegisterPresenter, UploadImageListener{
    public static final String URL = Constants.MY_PATH + "uploadImageShop.php";
    private ShopModel shopModel;
    private RegisterView view;
    private Context mContext;
    private UploadImageService uploadImageService;

    public RegisterPresenterImp(Context context, RegisterView view){
        this.mContext = context;
        this.view = view;
        shopModel = new ShopModel(mContext);
        uploadImageService = new UploadImageService(context, this, URL);
    }

    @Override
    public void register(Shop shop) {
        if (shopModel.registerShop(shop)){
            view.resgisterSuccessful();
        }else {
            view.registerFalse();
        }
    }

    @Override
    public void uploadImage(ArrayList<MyImage> data) {
        if (data == null || data.size() == 0){
            Log.e("truong", "data image null");
        }else {
            try {
                for (MyImage myImage : data){
                    uploadImageService.uploadImageVolley(myImage);
                }
            }catch (Exception e){
                view.uploadImageFalse();
            }

        }
    }

    @Override
    public void onResults(boolean isSuccessful, String message) {
        if (isSuccessful){
            view.uploadImageSucessful();
            Log.e("success", message);
        }else {
            view.uploadImageFalse();
            Log.e("false", message);
        }
    }
}
