package edu.hust.truongvu.choviet.user.myshop.report;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.helper.customview.MyToolbarExtra;

public class ReportActivity extends AppCompatActivity implements ReportView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        new MyToolbarExtra(this, "", 0, new MyToolbarExtra.OnExtraToolbarListener() {
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
