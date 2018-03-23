package edu.hust.truongvu.choviet.signup;

import android.content.Context;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.customview.MyToast;
import edu.hust.truongvu.choviet.entity.User;
import edu.hust.truongvu.choviet.helper.Utils;
import edu.hust.truongvu.choviet.utils.Constants;

/**
 * Created by truon on 2/21/2018.
 */

public class SignupPresenterImp implements SignupPresenter{
    private Context context;
    private SignupView signupView;
    public SignupPresenterImp(Context context, SignupView signupView){
        this.context = context;
        this.signupView = signupView;
    }


    @Override
    public void signup(String fullname, String email, String password, String retype) {
        // Pattern match for email id
        Pattern p = Pattern.compile(Utils.REG_EX);
        Matcher m = p.matcher(email);
        if (fullname.trim().matches("")||email.trim().matches("")||password.trim().matches("")||retype.trim().matches("")
                ||fullname.length()==0||email.length()==0||password.length()==0||retype.length()==0){
            signupView.onError(context.getString(R.string.please_enter_all));
        }else if (!password.equals(retype)){
            signupView.onError(context.getString(R.string.pass_not_match));
        }else if (!m.find()){
            signupView.onError(context.getString(R.string.invalid_email));
        }else {
            SignupModel signupModel = new SignupModel(context);
            User user = new User(0, fullname, email, password, "", null, "", -1, "", -1,
                    Constants.UserType.TYPE_CUSTOMER);
            if (signupModel.registerUser(user)){
                signupView.onSuccess();
            }else {
                signupView.onError(context.getString(R.string.signup_false));
            }
        }
    }
}
