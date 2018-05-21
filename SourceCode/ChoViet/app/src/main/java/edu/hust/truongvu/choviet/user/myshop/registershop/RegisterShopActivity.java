package edu.hust.truongvu.choviet.user.myshop.registershop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.helper.customview.MyToolbarExtra;

public class RegisterShopActivity extends AppCompatActivity {

    private EditText edtName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_shop);
        new MyToolbarExtra(this, getString(R.string.register_shop), 0, new MyToolbarExtra.OnExtraToolbarListener() {
            @Override
            public void onMoreClick() {

            }

            @Override
            public void onBackClick() {

            }
        });
    }
}
