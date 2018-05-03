package edu.hust.truongvu.choviet.product;

import android.content.Context;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.entity.Product;
import edu.hust.truongvu.choviet.model.ProductModel;

/**
 * Created by truon on 3/8/2018.
 */

public class ListProductByTypePresenterImp implements ListProductByTypePresenter {
    private ListProductByTypeView listProductByTypeView;
    private ProductModel productModel;
    private Context mContext;

    public ListProductByTypePresenterImp(Context context, ListProductByTypeView listProductByTypeView){
        this.listProductByTypeView = listProductByTypeView;
        this.mContext = context;
        productModel = new ProductModel(mContext);
    }


    @Override
    public void initListProductByCategory(int idCategory) {
        ArrayList<Product> data = new ArrayList<>();
        data = productModel.getProductByCategory(idCategory);
        if (data == null || data.size() == 0){
            listProductByTypeView.loadListFalse();
        }else {
            listProductByTypeView.loadListSuccessful(data);
        }
    }

    @Override
    public void initListProductByBrand(int idBrand) {
        ArrayList<Product> data = new ArrayList<>();
        data = productModel.getProductByBrand(idBrand);
        if (data == null || data.size() == 0){
            listProductByTypeView.loadListFalse();
        }else {
            listProductByTypeView.loadListSuccessful(data);
        }
    }

    @Override
    public void initListProductByShop(int idShop) {
        ArrayList<Product> data = new ArrayList<>();
        data = productModel.getProductByIdShop(idShop);
        if (data == null || data.size() == 0){
            listProductByTypeView.loadListFalse();
        }else {
            listProductByTypeView.loadListSuccessful(data);
        }
    }
}
