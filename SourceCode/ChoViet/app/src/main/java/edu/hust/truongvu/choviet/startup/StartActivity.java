package edu.hust.truongvu.choviet.startup;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.startup.signin.SigninFragment;
import edu.hust.truongvu.choviet.utils.Constants;

public class StartActivity extends AppCompatActivity {

    private static FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start);
        fragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null){
            fragmentManager.beginTransaction()
                    .replace(R.id.start_container, new SigninFragment(), Constants.MyTag.SIGNIN_FRAGMENT).commit();
        }

    }

    private void replaceSigninFragment(){
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.left_enter, R.anim.right_out)
                .replace(R.id.start_container, new SigninFragment(), Constants.MyTag.SIGNIN_FRAGMENT).commit();
    }

    @Override
    public void onBackPressed() {
        Fragment signup = fragmentManager.findFragmentByTag(Constants.MyTag.SIGNUP_FRAGMENT);
        Fragment forgotPass = fragmentManager.findFragmentByTag(Constants.MyTag.FORGOTPASS_FRAGMENT);
        if (signup != null){
            replaceSigninFragment();
        }else if (forgotPass != null){
            replaceSigninFragment();
        }else {
            super.onBackPressed();
        }

    }
}
