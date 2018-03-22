package edu.hust.truongvu.choviet.product;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.entity.Product;
import edu.hust.truongvu.choviet.entity.ProductRate;

/**
 * Created by truon on 2/26/2018.
 */

public interface ProductView {
    void loadListImage(ArrayList<String> list);
    void loadListRate(ArrayList<ProductRate> listRate);
    void loadListProduct(ArrayList<Product> listProduct);
    void loadListSuggest(ArrayList<Product> listSuggest);
}
