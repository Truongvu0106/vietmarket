package edu.hust.truongvu.choviet.user.info_user;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.helper.Constants;
import edu.hust.truongvu.choviet.helper.customview.MyToolbarExtra;

public class InfoUserActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tvUseName, tvPass, tvGender, tvBirthDay;
    private EditText edtFullName, edtPhone;
    private RecyclerView recyclerView;
    private View btnAddAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_user);
        new MyToolbarExtra(this, getString(R.string.user_information), R.drawable.ic_check, new MyToolbarExtra.OnExtraToolbarListener() {
            @Override
            public void onMoreClick() {

            }

            @Override
            public void onBackClick() {
                onBackPressed();
            }
        });
        initView();
    }

    private void initView(){
        tvUseName = findViewById(R.id.tv_username);
        tvPass = findViewById(R.id.tv_pass);
        tvGender = findViewById(R.id.tv_gender);
        tvBirthDay = findViewById(R.id.tv_birthday);
        edtFullName = findViewById(R.id.edt_full_name);
        edtPhone = findViewById(R.id.edt_phone);
        recyclerView = findViewById(R.id.list_address);
        btnAddAddress = findViewById(R.id.btn_add_new_addres);

        tvPass.setOnClickListener(this);
        tvGender.setOnClickListener(this);
        tvBirthDay.setOnClickListener(this);
        btnAddAddress.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_pass:
                changePassClick();
                break;
            case R.id.tv_gender:
                changeGenderClick();
                break;
            case R.id.tv_birthday:
                changeBirthday();
                break;
            case R.id.btn_add_new_addres:
                break;
            default:
                break;
        }
    }

    private void changePassClick(){
        ChangePasswordDialog passwordDialog = new ChangePasswordDialog(this, new ChangePasswordDialog.ChangePasswordListener() {
            @Override
            public void onChange(String newPass) {

            }
        });
        passwordDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        passwordDialog.show();
    }

    private void changeGenderClick(){
        ChangeGenderDialog changeGenderDialog = new ChangeGenderDialog(this, new ChangeGenderDialog.ChangeGengerListener() {
            @Override
            public void onChange(int gender) {
                if (gender == Constants.User.MALE){
                    tvGender.setText(getString(R.string.male));
                }else {
                    tvGender.setText(getString(R.string.female));
                }
            }
        });
        changeGenderDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        changeGenderDialog.show();
    }

    private void changeBirthday(){
        final Calendar c = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        tvBirthDay.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);
        datePickerDialog.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
