package edu.hust.truongvu.choviet.activity;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.fragment.CartFragment;
import edu.hust.truongvu.choviet.fragment.CategoryFragment;
import edu.hust.truongvu.choviet.fragment.HomeFragment;
import edu.hust.truongvu.choviet.fragment.NotificationFragment;
import edu.hust.truongvu.choviet.fragment.ProfileFragment;

public class MainActivity extends AppCompatActivity{
    EditText edtMaLoaiCha;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFragment(new HomeFragment());
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.home:
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.category:
                    fragment = new CategoryFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.cart:
                    fragment = new CartFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.notification:
                    fragment = new NotificationFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.profile:
                    fragment = new ProfileFragment();
                    loadFragment(fragment);
                    return true;
            }

            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
