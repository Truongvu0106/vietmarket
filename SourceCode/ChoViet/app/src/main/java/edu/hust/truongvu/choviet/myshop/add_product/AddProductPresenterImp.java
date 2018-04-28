package edu.hust.truongvu.choviet.myshop.add_product;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.entity.MyImage;
import edu.hust.truongvu.choviet.helper.UploadImageHelper;

/**
 * Created by truon on 4/28/2018.
 */

public class AddProductPresenterImp implements AddProductPresenter, UploadImageHelper.UploadImageListener {
    private AddProductView view;
    private Context mContext;
    private UploadImageHelper uploadImageHelper;
    public AddProductPresenterImp(Context context, AddProductView view){
        this.mContext = context;
        this.view = view;
        uploadImageHelper = new UploadImageHelper(context, this);
    }

    @Override
    public void upLoadImage(ArrayList<MyImage> data) {
        if (data == null || data.size() == 0){
            Log.e("truong", "data image null");
        }else {
            try {
                for (MyImage myImage : data){
                    uploadImageHelper.uploadImageVolley(myImage);
                }
            }catch (Exception e){
                view.uploadImageFalse();
            }

        }
    }

    @Override
    public void onResults(boolean isSuccessful, String message) {
        if (isSuccessful){
            view.uploadImageSuccessful();
            Log.e("success", message);
        }else {
            view.uploadImageFalse();
            Log.e("false", message);
        }

    }
}
