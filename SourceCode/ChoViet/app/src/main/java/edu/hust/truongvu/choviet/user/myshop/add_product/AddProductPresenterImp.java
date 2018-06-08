package edu.hust.truongvu.choviet.user.myshop.add_product;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.helper.Constants;
import edu.hust.truongvu.choviet.model.entity.Brand;
import edu.hust.truongvu.choviet.model.entity.ChildCategory;
import edu.hust.truongvu.choviet.model.entity.MyImage;
import edu.hust.truongvu.choviet.model.entity.Product;
import edu.hust.truongvu.choviet.services.UploadImageListener;
import edu.hust.truongvu.choviet.services.UploadImageService;
import edu.hust.truongvu.choviet.model.BrandModel;
import edu.hust.truongvu.choviet.model.CategoryModel;
import edu.hust.truongvu.choviet.model.ProductModel;

/**
 * Created by truon on 4/28/2018.
 */

public class AddProductPresenterImp implements AddProductPresenter, UploadImageListener {
    public static final String UPLOAD_IMG_URL = Constants.MY_PATH + "uploadImageProduct.php";
    private AddProductView view;
    private Context mContext;
    private UploadImageService uploadImageService;
    private BrandModel brandModel;
    private CategoryModel categoryModel;
    private ProductModel productModel;
    public AddProductPresenterImp(Context context, AddProductView view){
        this.mContext = context;
        this.view = view;
        uploadImageService = new UploadImageService(context, this, UPLOAD_IMG_URL);
        brandModel = new BrandModel(context);
        categoryModel = new CategoryModel(context);
        productModel = new ProductModel(context);
    }

    @Override
    public void upLoadImage(ArrayList<MyImage> data) {
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
    public void initListCategory() {
        ArrayList<ChildCategory> data = categoryModel.getListChildCategory();
        if (data == null || data.size() == 0){
            view.loadListCategoryFalse();
        }else {
            view.loadListCategorySuccessful(data);
        }
    }

    @Override
    public void initListBrand() {
        ArrayList<Brand> data = brandModel.getListBrand();
        if (data == null || data.size() == 0){
            view.loadListBrandFalse();
        }else {
            view.loadListBrandSuccessful(data);
        }
    }

    @Override
    public void addProduct(Product product) {
        if (productModel.addProduct(product)){
            view.addProductSuccessful();
        }else {
            view.addProductFalse();
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
