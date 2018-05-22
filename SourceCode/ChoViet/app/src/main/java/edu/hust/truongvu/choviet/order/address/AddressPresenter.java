package edu.hust.truongvu.choviet.order.address;

import edu.hust.truongvu.choviet.model.entity.User;

/**
 * Created by truon on 3/25/2018.
 */

public interface AddressPresenter {
    void initUser(User user);
    void initGuess(String name, String phone);
    void initListAddress(User user);
    void initAddressGuess(String address);
    void insertAddress(String address);
}
