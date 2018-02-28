package edu.hust.truongvu.choviet.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.cart.CartActivity;
import edu.hust.truongvu.choviet.fragment.MainFragment;
import edu.hust.truongvu.choviet.search.SearchActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Toolbar toolbar;
    private View search, cart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        search = findViewById(R.id.layout_search);
        cart = findViewById(R.id.img_cart);
        loadFragment(MainFragment.newInstance());

        search.setOnClickListener(this);
        cart.setOnClickListener(this);
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
}
