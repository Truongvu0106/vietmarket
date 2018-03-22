package edu.hust.truongvu.choviet.cart;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.entity.Product;

/**
 * Created by truon on 2/27/2018.
 */

public class CartPresenterImp implements CartPresenter {
    private CartView cartView;

    public CartPresenterImp(CartView cartView){
        this.cartView = cartView;
    }
    @Override
    public void initListItemCart() {
        ArrayList<Product> list = new ArrayList<>();

        cartView.loadCartItem(list);
    }
}
