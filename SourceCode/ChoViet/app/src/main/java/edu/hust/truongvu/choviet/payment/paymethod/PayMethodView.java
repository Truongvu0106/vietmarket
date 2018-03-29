package edu.hust.truongvu.choviet.payment.paymethod;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.entity.PayMethod;

/**
 * Created by truon on 3/28/2018.
 */

public interface PayMethodView {
    void loadListPayment(ArrayList<PayMethod> list);
}
