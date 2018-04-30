package edu.hust.truongvu.choviet.myshop.add_product;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.entity.Brand;
import edu.hust.truongvu.choviet.entity.ChildCategory;
import edu.hust.truongvu.choviet.entity.MyImage;
import edu.hust.truongvu.choviet.entity.Product;
import edu.hust.truongvu.choviet.helper.UploadImageHelper;
import edu.hust.truongvu.choviet.model.BrandModel;
import edu.hust.truongvu.choviet.model.CategoryModel;
import edu.hust.truongvu.choviet.model.ProductModel;

/**
 * Created by truon on 4/28/2018.
 */

public class AddProductPresenterImp implements AddProductPresenter, UploadImageHelper.UploadImageListener {
    private AddProductView view;
    private Context mContext;
    private UploadImageHelper uploadImageHelper;
    private BrandModel brandModel;
    private CategoryModel categoryModel;
    private ProductModel productModel;
    public AddProductPresenterImp(Context context, AddProductView view){
        this.mContext = context;
        this.view = view;
        uploadImageHelper = new UploadImageHelper(context, this);
        brandModel = new BrandModel();
        categoryModel = new CategoryModel();
        productModel = new ProductModel();
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
    public void initListCategory() {
        ArrayList<ChildCategory> data = categoryModel.getListChildCategory();
        if (data == null || data.size() == 0){
            view.loadListCategorySuccessful(data);
        }else {
            view.loadListCategoryFalse();
        }
    }

    @Override
    public void initListBrand() {
        ArrayList<Brand> data = brandModel.getListBrand();
        if (data == null || data.size() == 0){
            view.loadListBrandSuccessful(data);
        }else {
            view.loadListBrandFalse();
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
