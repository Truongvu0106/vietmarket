package edu.hust.truongvu.choviet.order.address;

import java.util.ArrayList;

/**
 * Created by truon on 3/25/2018.
 */

public interface AddressView {
    void loadListAddress(ArrayList<String> list);
    void loadUserInfo(String username, String phone);
    void insertSuccessful();
    void insertFalse();
}
