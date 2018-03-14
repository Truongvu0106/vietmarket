package edu.hust.truongvu.choviet.main;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.FacebookSdk;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import org.json.JSONException;
import org.json.JSONObject;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.cart.CartActivity;
import edu.hust.truongvu.choviet.search.SearchActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MainView{

    public static String USER_NAME = "";
    public static String IMAGE = "";
    public static Uri URI_IMAGE = null;
    private Toolbar toolbar;
    private View search, cart;
    private MainPresenterImp mainPresenterImp;
    private AccessToken accessToken;
    private AccessTokenTracker accessTokenTracker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        search = findViewById(R.id.layout_search);
        cart = findViewById(R.id.img_cart);
        loadFragment(MainFragment.newInstance());

        search.setOnClickListener(this);
        cart.setOnClickListener(this);

        mainPresenterImp = new MainPresenterImp(this);
        mainPresenterImp.loadInfoFacebook();
        GoogleSignInAccount account = (GoogleSignInAccount) getIntent().getSerializableExtra("GOOGLE");
        if (account != null){
            mainPresenterImp.loadInfoGoogle(account);
        }

    }



    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.layout_search:
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
                break;
            case R.id.img_cart:
                startActivity(new Intent(MainActivity.this, CartActivity.class));
                break;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenterImp.stopTrackingFacebook();
    }

    @Override
    public void initWithFacebook(JSONObject jsonObject) {
        if (jsonObject == null){
            return;
        }
        try {
            String name = jsonObject.getString("name");
            USER_NAME = name;
            if (jsonObject.has("picture")){
                IMAGE = jsonObject.getJSONObject("picture").getJSONObject("data").getString("url");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initWithGoogle(GoogleSignInAccount account) {
        if (account != null){
            USER_NAME = account.getDisplayName();
            URI_IMAGE = account.getPhotoUrl();
        }
    }
}