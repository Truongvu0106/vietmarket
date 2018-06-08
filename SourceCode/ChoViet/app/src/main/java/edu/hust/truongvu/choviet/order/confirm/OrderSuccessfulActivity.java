package edu.hust.truongvu.choviet.order.confirm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.admin.MainAdminActivity;
import edu.hust.truongvu.choviet.init.MainActivity;

public class OrderSuccessfulActivity extends AppCompatActivity {

    private View btnReturm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_successful);
        btnReturm = findViewById(R.id.btn_return);

        btnReturm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrderSuccessfulActivity.this, MainActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(OrderSuccessfulActivity.this, MainActivity.class));
    }
}
