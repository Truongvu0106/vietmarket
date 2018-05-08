package edu.hust.truongvu.choviet.startup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import edu.hust.truongvu.choviet.R;

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
        final Intent intent = new Intent(SplashActivity.this, StartActivity.class);
        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(5000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.start();
    }
}
