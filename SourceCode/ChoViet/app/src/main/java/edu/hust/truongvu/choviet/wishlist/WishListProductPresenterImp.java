package edu.hust.truongvu.choviet.wishlist;

import android.content.Context;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.entity.Product;
import edu.hust.truongvu.choviet.model.ProductModel;

/**
 * Created by truon on 5/6/2018.
 */

public class WishListProductPresenterImp implements WishListProductPresenter {
    private Context mContext;
    private WishListProductView view;
    private ProductModel productModel;

    public WishListProductPresenterImp(Context context, WishListProductView view){
        this.mContext = context;
        this.view = view;
        productModel = new ProductModel(mContext);
    }

    @Override
    public void initListWishList(int idUser) {
        ArrayList<Product> data = productModel.getListWishlist(idUser);
        if (data == null || data.size() == 0){
            view.loadListFalse();
        }else {
            view.loadListSuccessful(data);
        }
    }
}
