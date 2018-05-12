package edu.hust.truongvu.choviet.product.list_product;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.model.entity.PriceFilter;
import edu.hust.truongvu.choviet.model.entity.Product;

/**
 * Created by truon on 3/8/2018.
 */

public interface ListProductPresenter {
    void initListProductByCategory(boolean isLastest, int idCategory);
    void initListProductByBrand(boolean isLastest, int idBrand);
    void initListProductByShop(boolean isLastest, int idShop);
    void initListProductByData(ArrayList<Product> data);
    void initListProductByOptions(boolean isLastest, int sortByPrice, int idBrand, int idCategory,
                                  PriceFilter priceFilter, ArrayList<Product> data);
}
