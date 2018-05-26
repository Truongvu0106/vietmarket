package edu.hust.truongvu.choviet.order.confirm;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.model.entity.PayMethod;
import edu.hust.truongvu.choviet.model.entity.Product;
import edu.hust.truongvu.choviet.model.entity.Transport;

/**
 * Created by truon on 4/24/2018.
 */

public interface ConfirmView {
    void loadViewSuccess(Transport transport, PayMethod payMethod, String name, String phone,
                         String address, ArrayList<Product> list);
    void loadViewFalse();

    void addOrderSuccessful();
    void addOrderFalse();

    void updateStockSuccessful();
    void updateStockFalse();
}
