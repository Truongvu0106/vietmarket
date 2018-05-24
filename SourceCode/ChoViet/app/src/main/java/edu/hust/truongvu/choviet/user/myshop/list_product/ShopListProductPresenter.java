package edu.hust.truongvu.choviet.user.myshop.list_product;

import edu.hust.truongvu.choviet.model.entity.Product;

/**
 * Created by truon on 5/9/2018.
 */

public interface ShopListProductPresenter {
    void initListProduct(int idShop);
    void deleteProduct(Product product);
}
