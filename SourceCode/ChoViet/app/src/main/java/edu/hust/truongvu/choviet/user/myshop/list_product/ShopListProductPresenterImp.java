package edu.hust.truongvu.choviet.user.myshop.list_product;

import android.content.Context;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.model.ProductModel;
import edu.hust.truongvu.choviet.model.entity.Product;

/**
 * Created by truon on 5/9/2018.
 */

public class ShopListProductPresenterImp implements ShopListProductPresenter {
    private Context mContext;
    private ProductModel productModel;
    private ShopListProductView shopListProductView;
    public ShopListProductPresenterImp(Context context, ShopListProductView view){
        this.mContext = context;
        this.shopListProductView = view;
        productModel = new ProductModel(mContext);
    }

    @Override
    public void initListProduct(int idShop) {
        ArrayList<Product> data = new ArrayList<>();
        data = productModel.getProductByIdShop(idShop);
        if (data == null || data.isEmpty()){
            shopListProductView.loadListFalse();
        }else {
            shopListProductView.loadListSuccessful(data);
        }
    }

    @Override
    public void deleteProduct(Product product) {

    }
}
