package edu.hust.truongvu.choviet.product;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.entity.Product;
import edu.hust.truongvu.choviet.entity.ProductRate;
import edu.hust.truongvu.choviet.entity.User;

/**
 * Created by truon on 2/26/2018.
 */

public interface ProductView {
    void loadListImage(ArrayList<String> list);
    void loadListRate(ArrayList<ProductRate> listRate);
    void setEnableRate(boolean isRated);
    void loadListProduct(ArrayList<Product> listProduct);
    void loadListSuggest(ArrayList<Product> listSuggest);

}
