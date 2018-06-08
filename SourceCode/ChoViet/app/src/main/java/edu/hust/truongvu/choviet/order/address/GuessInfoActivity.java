package edu.hust.truongvu.choviet.order.address;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.shashank.sony.fancytoastlib.FancyToast;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.helper.customview.MyToolbarExtra;
import edu.hust.truongvu.choviet.order.PaymentActivity;

public class GuessInfoActivity extends AppCompatActivity {

    public static final String GUESS_NAME = "guess_name";
    public static final String GUESS_PHONE = "guess_phone";
    public static final String GUESS_ADDRESS = "guess_address";
    private EditText edtName, edtPhone, edtAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_info);
        new MyToolbarExtra(this, getString(R.string.enter_your_information), R.drawable.ic_check, new MyToolbarExtra.OnExtraToolbarListener() {
            @Override
            public void onMoreClick() {
                onSubmit();
            }

            @Override
            public void onBackClick() {
                onBackPressed();
            }
        });

        initAddress();
    }

    private void initAddress(){
        edtName = findViewById(R.id.edt_name);
        edtPhone = findViewById(R.id.edt_phone);
        edtAddress = findViewById(R.id.edt_address);
    }

    private void onSubmit(){
        String name = edtName.getText().toString();
        String phone = edtPhone.getText().toString();
        String address = edtAddress.getText().toString();

        if (name.matches("") || phone.matches("") || address.matches("")){
            MyHelper.showToast(this, getString(R.string.please_enter_all), FancyToast.WARNING);
        }else {
            Intent intent = new Intent(GuessInfoActivity.this, PaymentActivity.class);
            intent.putExtra(GUESS_NAME, name);
            intent.putExtra(GUESS_PHONE, phone);
            intent.putExtra(GUESS_ADDRESS, address);
            startActivity(intent);
        }
    }
}
