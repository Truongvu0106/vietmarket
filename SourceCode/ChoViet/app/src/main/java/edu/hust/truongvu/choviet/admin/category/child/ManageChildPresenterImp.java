package edu.hust.truongvu.choviet.admin.category.child;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.helper.Constants;
import edu.hust.truongvu.choviet.model.CategoryModel;
import edu.hust.truongvu.choviet.model.entity.ChildCategory;
import edu.hust.truongvu.choviet.model.entity.MyImage;
import edu.hust.truongvu.choviet.services.UploadImageListener;
import edu.hust.truongvu.choviet.services.UploadImageService;

/**
 * Created by truon on 6/6/2018.
 */

public class ManageChildPresenterImp implements ManageChildPresenter, UploadImageListener{
    public static final String UPLOAD_IMG_URL = Constants.MY_PATH + "uploadImageCategory.php";

    private Context mContext;
    private ManageChildView view;
    private CategoryModel categoryModel;
    private UploadImageService uploadImageService;

    public ManageChildPresenterImp(Context context, ManageChildView view){
        this.mContext = context;
        this.view = view;
        categoryModel = new CategoryModel(mContext);
        uploadImageService = new UploadImageService(mContext, this, UPLOAD_IMG_URL);
    }


    @Override
    public void initListChildCategory(int id) {
        ArrayList<ChildCategory> data = categoryModel.getListChildCategoryByParent(id);
        if (data.isEmpty()){
            view.loadListChildFalse();
        }else {
            view.loadListChildSuccessful(data);

        }
    }

    @Override
    public void addChild(ChildCategory childCategory) {
        if (childCategory == null){
            view.addChildFalse();
            return;
        }

        if (categoryModel.addChildCategory(childCategory)){
            view.addChildSuccessful();
        }else {
            view.addChildFalse();
        }
    }

    @Override
    public void updateChild(ChildCategory childCategory, boolean isChangedImage) {
        if (childCategory == null){
            view.updateChildFalse();
            return;
        }

        if (categoryModel.updateChildCategory(childCategory, isChangedImage)){
            view.updateChildSuccessful();
        }else {
            view.updateChildFalse();
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
