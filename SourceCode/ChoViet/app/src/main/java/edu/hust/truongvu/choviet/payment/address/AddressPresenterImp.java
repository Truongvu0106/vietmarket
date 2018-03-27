package edu.hust.truongvu.choviet.payment.address;

import android.content.Context;
import android.util.Log;

import edu.hust.truongvu.choviet.entity.User;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.profile.UserModel;

/**
 * Created by truon on 3/25/2018.
 */

public class AddressPresenterImp implements AddressPresenter {
    private Context context;
    private AddressView addressView;
    private UserModel userModel;
    public AddressPresenterImp(Context context, AddressView addressView){
        this.context = context;
        this.addressView = addressView;
        userModel = new UserModel(context);

    }

    @Override
    public void initUser() {
        User user = MyHelper.getCurrentUser(context);
        String username = user.getUsername();
        String phone = user.getPhone();
        addressView.loadUserInfo(username, phone);
    }

    @Override
    public void initListAddress() {
        User user = MyHelper.getCurrentUser(context);
        String address = user.getAddress();
        addressView.loadListAddress(MyHelper.getListSubString(address));
    }

    @Override
    public void insertAddress(String address) {
        User user = MyHelper.getCurrentUser(context);
        String updateAddress = user.getAddress() + "@" + address;
        User newUser = new User(user.getId(), user.getFullname(), user.getUsername(),
                user.getPassword(), updateAddress, user.getBirthday(), user.getPhone(),
                user.getGender(), user.getAvatar(), user.getIdTypeUser(), user.getTypeLogin());
        if (userModel.updateUser(newUser)){
            addressView.insertSuccessful();
        }else {
            addressView.insertFalse();
        }
    }
}
