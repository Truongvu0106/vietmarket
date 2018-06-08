package edu.hust.truongvu.choviet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.shashank.sony.fancytoastlib.FancyToast;

import edu.hust.truongvu.choviet.helper.Constants;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.helper.customview.MyToast;
import edu.hust.truongvu.choviet.startup.SplashActivity;
import edu.hust.truongvu.choviet.startup.StartActivity;

public class ConfigActivity extends AppCompatActivity {

    private EditText edtHost;
    private Button btnApply;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        edtHost = findViewById(R.id.edt_host);
        btnApply = findViewById(R.id.btn_apply);

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = edtHost.getText().toString().trim();
                if (text.matches("")){
                    MyHelper.showToast(ConfigActivity.this, "Please enter", FancyToast.ERROR);
                    return;
                }
                String host = "http://" + text + ":8080/electronicshop/";
                Constants.MY_PATH = host;
                startActivity(new Intent(ConfigActivity.this, SplashActivity.class));
            }
        });
    }
}
