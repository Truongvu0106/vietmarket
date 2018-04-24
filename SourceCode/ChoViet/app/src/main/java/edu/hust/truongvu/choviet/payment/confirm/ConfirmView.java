package edu.hust.truongvu.choviet.payment.confirm;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.entity.Product;

/**
 * Created by truon on 4/24/2018.
 */

public interface ConfirmView {
    void loadView(ArrayList<Product> list);
}
