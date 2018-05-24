package edu.hust.truongvu.choviet.user.myshop.update_product;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.model.entity.MyImage;
import edu.hust.truongvu.choviet.model.entity.Product;

/**
 * Created by truon on 5/24/2018.
 */

public interface UpdateProductPresenter {
    void initProduct(Product product);
    void initListBrand();
    void initListCategory();

    void updateProduct(Product product);
    void uploadImage(ArrayList<MyImage> data);
}
