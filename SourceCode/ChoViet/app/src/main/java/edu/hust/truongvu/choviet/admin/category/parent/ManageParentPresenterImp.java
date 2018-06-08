package edu.hust.truongvu.choviet.admin.category.parent;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.helper.Constants;
import edu.hust.truongvu.choviet.model.CategoryModel;
import edu.hust.truongvu.choviet.model.entity.ChildCategory;
import edu.hust.truongvu.choviet.model.entity.MyImage;
import edu.hust.truongvu.choviet.model.entity.ParentCategory;
import edu.hust.truongvu.choviet.services.UploadImageListener;
import edu.hust.truongvu.choviet.services.UploadImageService;

/**
 * Created by truon on 6/6/2018.
 */

public class ManageParentPresenterImp implements ManageParentPresenter, UploadImageListener{
    public static final String UPLOAD_IMG_URL = Constants.MY_PATH + "uploadImageCategory.php";

    private Context mContext;
    private ManageParentView view;
    private CategoryModel categoryModel;
    private UploadImageService uploadImageService;

    public ManageParentPresenterImp(Context context, ManageParentView view){
        this.mContext = context;
        this.view = view;
        categoryModel = new CategoryModel(mContext);
        uploadImageService = new UploadImageService(mContext, this, UPLOAD_IMG_URL);
    }

    @Override
    public void initListParentCategory() {
        ArrayList<ParentCategory> data = categoryModel.getListParentCategory();
        if (data.isEmpty()){
            view.loadListParentFalse();
        }else {
            view.loadListParentSuccessful(data);
        }
    }

    @Override
    public void addParent(ParentCategory parentCategory) {
        if (parentCategory == null){
            view.addParentFalse();
            return;
        }

        if (categoryModel.addParentCategory(parentCategory)){
            view.addParentSuccessful();
        }else {
            view.addParentFalse();
        }
    }

    @Override
    public void updateParent(ParentCategory parentCategory, boolean isChangedImage) {
        if (parentCategory == null){
            view.updateParentFalse();
            return;
        }

        if (categoryModel.updateParentCategory(parentCategory, isChangedImage)){
            view.updateParentSuccessful();
        }else {
            view.updateParentFalse();
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
