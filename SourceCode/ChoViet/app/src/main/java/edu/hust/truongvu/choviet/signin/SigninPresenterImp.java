package edu.hust.truongvu.choviet.signin;

import android.content.Context;

import edu.hust.truongvu.choviet.R;

/**
 * Created by truon on 2/21/2018.
 */

public class SigninPresenterImp implements SigninPresenter {

    private Context context;
    private SigninView signinView;
    public SigninPresenterImp(Context context, SigninView signinView){
        this.signinView = signinView;
        this.context = context;
    }

    @Override
    public void signin(String username, String password) {
        if (username.matches("1") && password.matches("1")){
            signinView.onSuccess();
        }else if (username.equals("") || password.equals("") ||
                username.length() == 0 || password.length() == 0){
            signinView.onError(context.getString(R.string.please_enter_signin));
        }else {
            signinView.onError(context.getString(R.string.wrong_signin));
        }
    }
}
