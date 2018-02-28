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
        list.add(new Product(0, "Điện thoại iphone", R.drawable.iphone, 20000, 25, false, 4));
        list.add(new Product(0, "Giầy Sneaker", R.drawable.giaydep, 20000, 25, false, 4));
        cartView.loadCartItem(list);
    }
}
