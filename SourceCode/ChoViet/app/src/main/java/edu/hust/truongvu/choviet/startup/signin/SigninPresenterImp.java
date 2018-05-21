package edu.hust.truongvu.choviet.startup.signin;

import android.content.Context;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.model.UserModel;

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
            signinView.onSuccess();
        }else {
            signinView.onError(context.getString(R.string.wrong_signin));
        }
    }
}
