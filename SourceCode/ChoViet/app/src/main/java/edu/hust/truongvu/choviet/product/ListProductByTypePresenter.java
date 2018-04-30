package edu.hust.truongvu.choviet.product;

/**
 * Created by truon on 3/8/2018.
 */

public interface ListProductByTypePresenter {
    void initListProductByCategory(int idCategory);
    void initListProductByBrand(int idBrand);
    void initListProductByShop(int idShop);
}
