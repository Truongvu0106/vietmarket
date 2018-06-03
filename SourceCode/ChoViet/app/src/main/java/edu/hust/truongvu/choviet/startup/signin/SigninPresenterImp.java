package edu.hust.truongvu.choviet.startup.signin;

import android.content.Context;
import android.util.Log;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.model.UserModel;
import edu.hust.truongvu.choviet.model.entity.User;

/**
 * Created by truon on 2/21/2018.
 */

public class SigninPresenterImp implements SigninPresenter {

    private Context context;
    private SigninView signinView;
    private UserModel userModel;
    public SigninPresenterImp(Context context, SigninView signinView){
        this.signinView = signinView;
        this.context = context;
        userModel = new UserModel(context);
    }

    @Override
    public void signin(String username, String password) {
        if (username.trim().equals("") || password.trim().equals("") ||
                username.length() == 0 || password.length() == 0){
            signinView.onError(context.getString(R.string.please_enter_signin));
        }else if (userModel.checkLogin(username, password)){
            User user = userModel.getUserByUsername(username);
            if (user == null){
                signinView.onError(context.getString(R.string.wrong_signin));
            }else {
                signinView.onSuccess(user);
            }

        }else {
            signinView.onError(context.getString(R.string.wrong_signin));
        }
    }
}
