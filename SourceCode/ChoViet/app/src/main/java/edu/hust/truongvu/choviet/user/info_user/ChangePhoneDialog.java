package edu.hust.truongvu.choviet.user.info_user;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.shashank.sony.fancytoastlib.FancyToast;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.helper.MyHelper;

/**
 * Created by truon on 4/17/2018.
 */

public class ChangePhoneDialog extends AlertDialog{
    public interface ChangePhoneListener{
        void onChange(String phone);
    }

    private ChangePhoneListener mListener;
    private Context context;
    private EditText edtPhone;
    private View btnCancel, btnUpdate;

    public ChangePhoneDialog(Context context, ChangePhoneListener listener) {
        super(context);
        this.context = context;
        this.mListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_change_phone);
        edtPhone = findViewById(R.id.edt_phone);
        btnCancel = findViewById(R.id.btn_cancel);
        btnUpdate = findViewById(R.id.btn_update);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = edtPhone.getText().toString().trim();
                if (phone.matches("")){
                    MyHelper.showToast(getContext(), getContext().getString(R.string.please_enter_all), FancyToast.WARNING);
                    return;
                }

                mListener.onChange(phone);
                dismiss();
            }
        });
    }
}
