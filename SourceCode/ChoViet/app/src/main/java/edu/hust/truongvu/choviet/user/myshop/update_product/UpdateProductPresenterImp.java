package edu.hust.truongvu.choviet.user.myshop.update_product;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.helper.Constants;
import edu.hust.truongvu.choviet.model.BrandModel;
import edu.hust.truongvu.choviet.model.CategoryModel;
import edu.hust.truongvu.choviet.model.ProductModel;
import edu.hust.truongvu.choviet.model.entity.Brand;
import edu.hust.truongvu.choviet.model.entity.ChildCategory;
import edu.hust.truongvu.choviet.model.entity.MyImage;
import edu.hust.truongvu.choviet.model.entity.Product;
import edu.hust.truongvu.choviet.services.UploadImageListener;
import edu.hust.truongvu.choviet.services.UploadImageService;

/**
 * Created by truon on 5/24/2018.
 */

public class UpdateProductPresenterImp implements UpdateProductPresenter, UploadImageListener{
    public static final String UPLOAD_IMG_URL = Constants.MY_PATH + "uploadImageProduct.php";
    private UpdateProductView updateProductView;
    private Context mContext;
    private ProductModel productModel;
    private CategoryModel categoryModel;
    private BrandModel brandModel;
    private UploadImageService uploadImageService;

    public UpdateProductPresenterImp(Context context, UpdateProductView view){
        this.mContext = context;
        this.updateProductView = view;
        productModel = new ProductModel(mContext);
        categoryModel = new CategoryModel(mContext);
        brandModel = new BrandModel(mContext);
        uploadImageService = new UploadImageService(mContext, this, UPLOAD_IMG_URL);
    }

    @Override
    public void initProduct(Product product) {
        if (product == null){
            updateProductView.loadProductFalse();
        }else {
            updateProductView.loadProductSuccessful(product);
        }
    }

    @Override
    public void initListBrand() {
        ArrayList<Brand> data = brandModel.getListBrand();
        if (data == null || data.size() == 0){
            updateProductView.initListBrandFalse();
        }else {
            updateProductView.initListBrandSuccessful(data);
        }
    }

    @Override
    public void initListCategory() {
        ArrayList<ChildCategory> data = categoryModel.getListChildCategory();
        if (data == null || data.size() == 0){
            updateProductView.initListCategoryFalse();
        }else {
            updateProductView.initListCategorySuccessful(data);
        }
    }

    @Override
    public void updateProduct(Product product) {
        if (productModel.updateProduct(product)){
            updateProductView.updateSuccessful();
        }else {
            updateProductView.updateFalse();
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
                updateProductView.uploadImageFalse();
            }

        }
    }

    @Override
    public void onResults(boolean isSuccessful, String message) {
        if (isSuccessful){
            updateProductView.uploadImageSuccessful();
            Log.e("success", message);
        }else {
            updateProductView.uploadImageFalse();
            Log.e("false", message);
        }
    }
}
