package edu.hust.truongvu.choviet.myshop.list_order;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.customview.MyToolbarExtra;

public class MyListOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list_order);
        new MyToolbarExtra(this);
    }
}
