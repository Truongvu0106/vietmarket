package edu.hust.truongvu.choviet.user.myshop.add_product;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.entity.Brand;
import edu.hust.truongvu.choviet.entity.ChildCategory;

/**
 * Created by truon on 4/28/2018.
 */

public interface AddProductView {
    void uploadImageSuccessful();
    void uploadImageFalse();

    void addProductSuccessful();
    void addProductFalse();

    void loadListCategorySuccessful(ArrayList<ChildCategory> data);
    void loadListCategoryFalse();

    void loadListBrandSuccessful(ArrayList<Brand> data);
    void loadListBrandFalse();
}
