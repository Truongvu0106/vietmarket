package edu.hust.truongvu.choviet.order.address;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.helper.customview.MyToolbarExtra;

public class GuessInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_info);
        new MyToolbarExtra(this, getString(R.string.enter_your_information), R.drawable.ic_check, new MyToolbarExtra.OnExtraToolbarListener() {
            @Override
            public void onMoreClick() {

            }

            @Override
            public void onBackClick() {
                onBackPressed();
            }
        });
    }
}
