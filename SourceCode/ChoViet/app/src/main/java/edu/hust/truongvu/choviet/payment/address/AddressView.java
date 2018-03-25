package edu.hust.truongvu.choviet.payment.address;

import java.util.ArrayList;

/**
 * Created by truon on 3/25/2018.
 */

public interface AddressView {
    void loadListAddress(ArrayList<String> list);
    void insertSuccessful();
    void insertFalse();
}
