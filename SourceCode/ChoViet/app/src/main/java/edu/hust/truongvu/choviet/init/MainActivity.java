package edu.hust.truongvu.choviet.init;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import org.json.JSONException;
import org.json.JSONObject;

import edu.hust.truongvu.choviet.MyApplication;
import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.cart.CartActivity;
import edu.hust.truongvu.choviet.cart.CartPresenter;
import edu.hust.truongvu.choviet.cart.CartPresenterImp;
import edu.hust.truongvu.choviet.helper.ConnectivityReceiver;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.helper.customview.MyLayoutError;
import edu.hust.truongvu.choviet.search.SearchActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MainView, ConnectivityReceiver.ConnectivityReceiverListener{

    public static String USER_NAME = "";
    public static String IMAGE = "";
    public static Uri URI_IMAGE = null;
    private View toolbar;
    private View search, cart, layoutNumberItemCart;
    private TextView tvNumberItemCart;
    private MainPresenter mainPresenterImp;
    private CartPresenter cartPresenterImp;
    private AccessToken accessToken;
    private AccessTokenTracker accessTokenTracker;
    View container, layoutErr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        new MyLayoutError(this, true, getString(R.string.not_connect_internet), R.drawable.no_wifi,
                new MyLayoutError.ErrorListener() {
                    @Override
                    public void onTryAgain() {
                        checkConnection();
                    }
                });
        toolbar =  findViewById(R.id.toolbar_main);
        container = findViewById(R.id.frame_container);
        layoutErr = findViewById(R.id.layout_err);

        search = findViewById(R.id.layout_search);
        cart = findViewById(R.id.img_cart);
        layoutNumberItemCart = findViewById(R.id.layout_number_item_cart);
        tvNumberItemCart = findViewById(R.id.tv_number_item_cart);

        checkConnection();

        search.setOnClickListener(this);
        cart.setOnClickListener(this);

        mainPresenterImp = new MainPresenterImp(this);
        cartPresenterImp = new CartPresenterImp();
//        mainPresenterImp.loadInfoFacebook();
//        GoogleSignInAccount account = (GoogleSignInAccount) getIntent().getSerializableExtra("GOOGLE");
//        if (account != null){
//            mainPresenterImp.loadInfoGoogle(account);
//        }
//        Toast.makeText(this, "Welcome " + MyHelper.getUserNamePreference(this), Toast.LENGTH_SHORT).show();

    }

//    private String getUserNamePreference(Context context){
//        SharedPreferences userPreference = context.getSharedPreferences("mylogin", MODE_PRIVATE);
//        String username = userPreference.getString("username", "");
//        return username;
//    }


    @Override
    protected void onStart() {
        super.onStart();
        MyHelper.setViewCart(layoutNumberItemCart, tvNumberItemCart, cartPresenterImp.getNumberItemCart(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApplication.getInstance().setConnectivityListener(this);
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

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        setView(isConnected);
    }

    private void checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        setView(isConnected);
    }

    private void setView(boolean isConnected){
        if (isConnected){
            container.setVisibility(View.VISIBLE);
            layoutErr.setVisibility(View.GONE);
            loadFragment(MainFragment.newInstance());
        }else {
            container.setVisibility(View.GONE);
            layoutErr.setVisibility(View.VISIBLE);
        }
    }
}
