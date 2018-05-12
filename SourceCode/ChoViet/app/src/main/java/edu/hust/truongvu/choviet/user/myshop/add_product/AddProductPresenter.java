package edu.hust.truongvu.choviet.user.myshop.add_product;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.model.entity.MyImage;
import edu.hust.truongvu.choviet.model.entity.Product;

/**
 * Created by truon on 4/28/2018.
 */

public interface AddProductPresenter {
    void upLoadImage(ArrayList<MyImage> data);
    void initListCategory();
    void initListBrand();
    void addProduct(Product product);
}
