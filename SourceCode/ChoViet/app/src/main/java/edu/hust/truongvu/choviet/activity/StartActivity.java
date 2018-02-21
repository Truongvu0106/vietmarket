package edu.hust.truongvu.choviet.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.signin.SigninFragment;
import edu.hust.truongvu.choviet.helper.Utils;

public class StartActivity extends AppCompatActivity {

    private static FragmentManager fragmentManager;
    ImageView close;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start);
        close = (ImageView) findViewById(R.id.close_activity);
        fragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null){
            fragmentManager.beginTransaction()
                    .replace(R.id.start_container, new SigninFragment(), Utils.SIGNIN_FRAGMENT).commit();
        }


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void replaceSigninFragment(){
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.left_enter, R.anim.right_out)
                .replace(R.id.start_container, new SigninFragment(), Utils.SIGNIN_FRAGMENT).commit();
    }

    @Override
    public void onBackPressed() {
        Fragment signup = fragmentManager.findFragmentByTag(Utils.SIGNUP_FRAGMENT);
        Fragment forgotPass = fragmentManager.findFragmentByTag(Utils.FORGOTPASS_FRAGMENT);
        if (signup != null){
            replaceSigninFragment();
        }else if (forgotPass != null){
            replaceSigninFragment();
        }else {
            super.onBackPressed();
        }

    }
}
