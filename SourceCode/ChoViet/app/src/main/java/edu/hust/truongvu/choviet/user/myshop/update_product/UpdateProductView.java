package edu.hust.truongvu.choviet.user.myshop.update_product;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.model.entity.Brand;
import edu.hust.truongvu.choviet.model.entity.ChildCategory;
import edu.hust.truongvu.choviet.model.entity.Product;

/**
 * Created by truon on 5/24/2018.
 */

public interface UpdateProductView {
    void loadProductSuccessful(Product product);
    void loadProductFalse();

    void initListBrandSuccessful(ArrayList<Brand> data);
    void initListBrandFalse();

    void initListCategorySuccessful(ArrayList<ChildCategory> data);
    void initListCategoryFalse();

    void uploadImageSuccessful();
    void uploadImageFalse();

    void updateSuccessful();
    void updateFalse();
}
