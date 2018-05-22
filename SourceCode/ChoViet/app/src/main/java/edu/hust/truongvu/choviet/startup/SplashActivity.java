package edu.hust.truongvu.choviet.startup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.init.MainActivity;

public class SplashActivity extends AppCompatActivity {

    View logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        logo = findViewById(R.id.layout_logo);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.splash_anim);
        logo.startAnimation(animation);
        final Intent intent1 = new Intent(SplashActivity.this, StartActivity.class);
        final Intent intent2 = new Intent(SplashActivity.this, MainActivity.class);
        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(5000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    if (MyHelper.getCurrentUser(SplashActivity.this) == null){
                        startActivity(intent1);
                    }else {
                        startActivity(intent2);
                    }

                    finish();
                }
            }
        };
        timer.start();
    }
}
