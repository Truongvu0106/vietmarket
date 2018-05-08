package edu.hust.truongvu.choviet.order.confirm;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.entity.PayMethod;
import edu.hust.truongvu.choviet.entity.Product;
import edu.hust.truongvu.choviet.entity.Transport;

/**
 * Created by truon on 4/24/2018.
 */

public interface ConfirmView {
    void loadViewSuccess(Transport transport, PayMethod payMethod, String address, ArrayList<Product> list);
    void loadViewFalse();

    void addOrderSuccessful();
    void addOrderFalse();
}
